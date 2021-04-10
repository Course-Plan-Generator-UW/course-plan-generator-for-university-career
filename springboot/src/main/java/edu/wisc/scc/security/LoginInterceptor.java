package edu.wisc.scc.security;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * token:
 * userType:
 * userId:
 */
@Slf4j
@Component
//拦截器 能拦截http请求
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    JwtTokenConfig jwtTokenConfig;

    @Autowired
    private OnlineUserService onlineUserService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
            throws Exception {
        log.info("开始验证token");
        String uri = request.getRequestURI();
        log.info("uri={}",uri);
        R result = jwtTokenConfig.validateTokenAndSetUserInfoToRequest(request);
        if(result.getCode()==0){
            return true;
        }else{
            writeReturn(response,result);
            return false;
        }
    }

    /**
     * 返回结果写入响应
     */
    private void writeReturn(HttpServletResponse response, R result) throws IOException {
        log.info("返回结果={}", result);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        //response.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,OPTIONS");
        //response.setHeader("Access-Control-Max-Age", "3600");
        //response.setHeader("Access-Control-Allow-Headers", "accept,x-requested-with,Content-Type");
        //response.setHeader("Access-Control-Allow-Credentials", "true");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(JSON.toJSONString(result));
        }
    }

}
