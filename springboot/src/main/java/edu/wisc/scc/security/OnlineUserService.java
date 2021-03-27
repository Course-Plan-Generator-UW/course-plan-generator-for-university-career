package edu.wisc.scc.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class OnlineUserService {

    private static Map<String,String> allTokens=new HashMap<>();

    public void login(String userId,String token){
        allTokens.put(userId,token);
    }

    public void logout(String userId){
        allTokens.remove(userId);
    }

    public boolean access(String userId,String token){
        return token.equals(allTokens.get(userId));
    }

    public boolean isOnline(String userId) {
        return allTokens.containsKey(userId);
    }
}
