package common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import edu.wisc.scc.entity.StudentUser;

import java.util.Date;
import java.util.HashMap;

public class JwtGenerateUtil {

    public static String generateToken(int timeout,
                                       String userType,
                                       String userId,
                                       String tokenType,
                                       String key){
        Date expire = new Date(System.currentTimeMillis()+timeout*60*1000);
        Algorithm algorithm = Algorithm.HMAC256(key);
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return JWT.create().withHeader(header)
                .withClaim("userType",userType)
                .withClaim("userId",userId)
                .withClaim("tokenType",tokenType)
                .withExpiresAt(expire)
                .sign(algorithm);
    }


    /**
     * 生成用户登录token
     */
    public static String generateStudentLoginToken(int timeout, StudentUser studentUser, String tokenType, String key){
        Date expire = new Date(System.currentTimeMillis()+timeout*60*1000);
        Algorithm algorithm = Algorithm.HMAC256(key);
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return JWT.create().withHeader(header).withClaim("userType","student")
                .withClaim("userId",studentUser.getId())
                .withClaim("tokenType",tokenType)
                .withExpiresAt(expire)
                .sign(algorithm);
    }



}
