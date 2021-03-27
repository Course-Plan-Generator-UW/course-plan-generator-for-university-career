package edu.wisc.scc.security;

import com.alibaba.fastjson.JSONObject;
import common.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class ValidateCodeCache {
    @Value("${validate.code.img.expire.timeout}")
    private Integer timeout;

    private Map<String, JSONObject> cacheCodeMap = Collections.synchronizedMap(new HashMap<>());

    public boolean verifyCode(String validateCodeKey, String validateCode) {
        JSONObject obj = cacheCodeMap.get(validateCodeKey);
        if(obj==null){
            return false;
        }
        Integer time = obj.getInteger("time");//存储秒
        if(System.currentTimeMillis()/1000 - time > timeout*60){
            cacheCodeMap.remove(validateCodeKey);
            return false;
        }
        String code = obj.getString("code");
        if(code.equalsIgnoreCase(validateCode)){
            cacheCodeMap.remove(validateCodeKey);
            return true;
        }
        return false;
    }

    public String genValidateCode(String randomKey) {
        String code = RandomUtils.randomString(4);
        JSONObject obj = new JSONObject(2);
        obj.put("code",code);
        obj.put("time",System.currentTimeMillis()/1000);
        cacheCodeMap.put(randomKey,obj);
        return code;
    }
}
