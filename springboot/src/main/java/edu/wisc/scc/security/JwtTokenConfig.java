package edu.wisc.scc.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import common.JavaUtils;
import common.JwtGenerateUtil;
import common.JwtUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Data
@Slf4j
public class JwtTokenConfig {

    @Value("${jwt.token.key}")
    private String headerKey;

    @Value("${jwt.user.key}")
    private String jwtKey;

    @Value("${jwt.access.expire.timeout}")
    private int accessTimeout;

    @Value("${jwt.refresh.expire.timeout}")
    private int refreshTimeout;

    public static final String accessToken = "accessToken";
    public static final String refreshToken = "refreshToken";

    @Autowired
    private OnlineUserService onlineUserService;

    public JSONObject genLoginAndRefreshTokenInfo(String userType, String userId) {
        String newAccessToken = JwtGenerateUtil.generateToken(
                this.getAccessTimeout(),
                userType,
                userId,
                "accessToken",
                this.getJwtKey());
        String newRefreshToken = JwtGenerateUtil.generateToken(
                this.getRefreshTimeout(),
                userType,
                userId, "refreshToken",
                this.getJwtKey());
        JSONObject data = new JSONObject(3);
        data.put("userId", userId);
        data.put("accessToken", newAccessToken);
        data.put("refreshToken", newRefreshToken);
        return data;
    }

    public R validateTokenAndSetUserInfoToRequest(HttpServletRequest request) {
        R result;
        //从request header中获取token
        String accessToken = request.getHeader(this.getHeaderKey());

        log.info("accessToken={}", accessToken);
        if (JavaUtils.isEmpty(accessToken)) {//未携带accessToken
            result = R.failed("认证失败，非法请求").setCode(-3);
        } else {
            try {
                String loginUserInfo = JwtUtil.verify(accessToken,
                        this.getJwtKey());
                JSONObject loginUserInfoObj = JSON.parseObject(loginUserInfo);
                long exp = loginUserInfoObj.getLong("exp");//过期时间
                long now = System.currentTimeMillis() / 1000;//秒
                String tokenType = loginUserInfoObj.getString("tokenType");//accessToken refreshToken
                if (now > exp) {
                    result = R.failed("token已过期").setCode(-3);
                } else if (!JwtTokenConfig.accessToken.equals(tokenType)) {
                    result = R.failed("token类型错误，非法请求").setCode(-3);
                } else {
                    String userId = loginUserInfoObj.getString("userId");
                    if (!onlineUserService.access(userId, accessToken)) {
                        result = R.failed("token失效，非法请求").setCode(-3);
                    } else {
                        String userType = loginUserInfoObj.getString("userType");
                        request.setAttribute("userId", userId);
                        request.setAttribute("userType", userType);
                        return R.ok("validate success");
                    }
                }
            } catch (Exception e) {
                log.error("解密失败", e);
                result = R.failed("token校验失败").setCode(-3);
            }
        }
        return result;
    }


}
