package edu.wisc.scc.security;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import common.JavaUtils;
import common.RandImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("user")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserLoginController {

    @Value("${validate.code.enable}")
    private Boolean validateCodeEnable;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private ValidateCodeCache validateCodeCache;

    @Autowired
    private OnlineUserService onlineUserService;

    @Autowired
    private JwtTokenConfig jwtTokenConfig;

    /**
     * 获取验证码图片
     */
    @GetMapping("codeImg")
    public void codeImg(String randomKey, HttpServletResponse response) throws IOException {
        log.info("codeImg()请求参数={}",randomKey);
        String code = validateCodeCache.genValidateCode(randomKey);
        RandImageUtil.generate(response,code);
     }


    @PostMapping(value="login",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public R login(@RequestBody LoginDto loginDto) {
        log.info("login()请求入参={}",loginDto);
        if(validateCodeEnable) {
            R validateCodeResult = validateImageCode(loginDto.getValidateCode(), loginDto.getValidateCodeKey());
            if (validateCodeResult.getCode() != 0) {
                return validateCodeResult;
            }
        }
        R result = userLoginService.login(loginDto);
        log.info("login()返回结果={}",result);
        return result;
    }


    @GetMapping(value="logout")
    public R logout(String userId, HttpServletRequest request,HttpServletResponse response) {
        log.info("logout()请求入参={}",userId);
        if(JavaUtils.isEmpty(userId)){
            return R.failed("user Id cannot be null");
        }
        R result = jwtTokenConfig.validateTokenAndSetUserInfoToRequest(request);
        if(result.getCode()!=0){
            return result;
        }
        String userIdInToken = (String)request.getAttribute("userId");
        if(!userId.equals(userIdInToken)){
            return R.failed("invalid request");
        }
        onlineUserService.logout(userId);
        return R.ok("logout success");
    }


    @PostMapping(value="refreshToken",consumes = MediaType.APPLICATION_JSON_VALUE)
    public R refreshToken(@RequestBody JSONObject params) {
        log.info("refreshToken()入参={}",params);
        String refreshToken = params.getString("refreshToken");
        R r=null;
        if(JavaUtils.isEmpty(refreshToken)){
          r=  R.failed("refreshToken cannot be empty").setCode(-2);
        }else{
            r=  userLoginService.refreshToken(refreshToken);
        }
        log.info("刷新accessToken返回={}",r);
        return r;
    }

    @PostMapping(value="regist",consumes = MediaType.APPLICATION_JSON_VALUE)
    public R registUser(@RequestBody StudentUserRegistDto user){
        if(validateCodeEnable) {
            R validateCodeResult = validateImageCode(user.getValidateCode(), user.getValidateCodeKey());
            if (validateCodeResult.getCode() != 0) {
                return validateCodeResult;
            }
        }
        if(userLoginService.isUserNameExists(user.getUserName())){
            return R.failed("username("+user.getUserName()+") exists!");
        }
        userLoginService.registUser(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        return R.ok(jsonObject);
    }

    @PostMapping(value="modifyPassword",consumes = MediaType.APPLICATION_JSON_VALUE)
    public R modifyPassword(@RequestBody @Valid ModifyPasswordDto modifyPasswordDto){
        log.info("modifyPassword()请求入参modifyPasswordDto={},companyUserParentDto={}",modifyPasswordDto);
        R result = userLoginService.modifyPassword(modifyPasswordDto);
        log.info("modifyPassword()返回结果={}",result);
        return R.failed("还未实现");
    }

    private R validateImageCode(String validateCode,
                                String validateCodeKey){
        if(JavaUtils.isEmpty(validateCode)){
            return R.failed("must provide validate code");
        }else if(JavaUtils.isEmpty(validateCodeKey)){
            return R.failed("must provide validate code key");
        }else if(validateCodeCache.verifyCode(validateCodeKey,validateCode)){
            return R.ok("validate code is ok");
        }else{
            return R.failed("wrong validate code");
        }
    }

}
