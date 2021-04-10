package edu.wisc.scc.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import common.*;
import edu.wisc.scc.dao.StudentUserDao;
import edu.wisc.scc.entity.StudentUser;
import edu.wisc.scc.service.StudentInterestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author njs
 * @description 企业用户表(T_SALARY_COMPANY_USER)表服务接口
 */
@Service
public class UserLoginService {

    @Autowired
    private ValidateCodeCache validateCodeCache;

    @Autowired
    private OnlineUserService onlineUserService;

    @Autowired
    private StudentInterestInfoService studentInterestInfoService;

    @Autowired
    private UserLoginDao userLoginDao;

    @Autowired
    private StudentUserDao studentUserDao;

    @Autowired
    private JwtTokenConfig jwtTokenConfig;


    /**
     * 企业员工登录获取token
     */
    public R login(LoginDto loginDto) {
        if (loginDto.getUsertype().equals("student")) {
            //通过，判断是否有对应企业 //todo 对密码加密
            StudentUser user = userLoginDao.getOneByUsernameAndPassword(
                    loginDto.getUsername(), MD5Utils.encrypt(loginDto.getPassword()));
            if (JavaUtils.isNotEmpty(user)) {
                //是企业用户，登陆成功
                JSONObject data = getToken(user.getId());
                onlineUserService.login(user.getId(), data.getString("accessToken"));
                return APIResult.ok(data);
            } else {
                //不存在该用户
                return APIResult.error(3, "用户不存在");
            }
        } else {
            return APIResult.error(3, "用户类型不存在");
        }
    }

    public R refreshToken(String refreshToken) {
        try {
            String payload = JwtUtil.verify(refreshToken, jwtTokenConfig.getJwtKey());
            JSONObject payloadJson = JSON.parseObject(payload);
            String tokenType = payloadJson.getString("tokenType");
            if (tokenType.equals("refreshToken")) {
                long exp = payloadJson.getLong("exp");
                long now = System.currentTimeMillis() / 1000;
                if (now < exp) {
                    //校验通过，生成两种token
                    String userId = payloadJson.getString("userId");
                    String userType = payloadJson.getString("userType");
                    if (JavaUtils.isEmpty(userId) || JavaUtils.isEmpty(userType)) {
                        return R.failed("无效的refreshToken");
                    }
                    if (!onlineUserService.isOnline(userId)) {
                        return R.failed("user is not online");
                    }
                    JSONObject data = getToken(userId);
                    onlineUserService.login(userId, data.getString("accessToken"));
                    return APIResult.ok(data);
                } else {
                    return R.failed("refreshToken已失效");
                }
            } else {
                return R.failed("无效的refreshToken");
            }
        } catch (Exception e) {
            return R.failed(e.getMessage());
        }
    }

    public R modifyPassword(ModifyPasswordDto modifyPasswordDto) {
        R result;
        StudentUser user = this.studentUserDao.selectById(modifyPasswordDto.getUserId());
        if (JavaUtils.isEmpty(user)) {
            result = R.failed("该用户不存在").setCode(1);
        } else {
            if (!modifyPasswordDto.getOldPassword().equals(MD5Utils.encrypt(user.getUserPwd()))) {
                result = APIResult.error(2, "旧密码不正确");
            } else {
                StudentUser userForUpdate = new StudentUser();
                userForUpdate.setId(user.getId());
                userForUpdate.setUserPwd(user.getUserPwd());
                userForUpdate.preUpdate();
                this.studentUserDao.updateById(userForUpdate);
                result = R.ok("修改成功");
            }
        }
        return result;
    }


    //    根据companyUser生成token
    private JSONObject getToken(String userId) {
        String userType = "student";
        JSONObject jsonObject = jwtTokenConfig.genLoginAndRefreshTokenInfo(userType, userId);
        return jsonObject;
    }


    @Transactional(rollbackFor = Exception.class)
    public void registUser(StudentUser user) {
        user.setId(RandomUtils.genUUID());
        user.preInsert();
        user.setUserPwd(MD5Utils.encrypt(user.getUserPwd()));
        studentUserDao.insert(user);
        studentInterestInfoService.initInterestsByStudentId(user.getId());
    }

    public boolean isUserNameExists(String userName) {
        return this.studentUserDao.selectCount(
                new QueryWrapper<StudentUser>().eq("user_name", userName)//build a query  condition: eq( user_name = userName)  mybatis
        ) > 0;
    }
}