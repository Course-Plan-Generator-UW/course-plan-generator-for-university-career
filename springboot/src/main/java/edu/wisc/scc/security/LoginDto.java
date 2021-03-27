package edu.wisc.scc.security;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
    private String usertype;
    private String validateCode;
    private String validateCodeKey;
    //浏览器获取验证码图片
    // 1.浏览器生成一个长度不小于16的随机代码uuid  validateCodeKey
    // 2.浏览器向后端发送的是一个包含validateCodeKey的url
    // 3.浏览器生成一个验证码图片，把图片对应的验证码结果 和 validateCodeKey 进行存储
    // 4.浏览器输入验证码 发送验证码和validateCodeKey给服务器
    // 5.服务器根据validateCodeKey找到正确的验证码 和 输入的验证码进行比对 得到验证码是否正确的结果了
}
