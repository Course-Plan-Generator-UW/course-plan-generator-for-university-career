package common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.util.Base64Utils;

public class JwtUtil {
    public static class JwtException extends Exception{
        public JwtException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    /**
     * 解析token
     */
    public static String verify(String token,String key) throws JwtException{
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return new String(Base64Utils.decodeFromUrlSafeString(jwt.getPayload()));
        }catch (Exception e){
            throw new JwtException(
                    "invalid token",e);
        }
    }

    public static void main(String[] args) {

    }

}
