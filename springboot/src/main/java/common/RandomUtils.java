package common;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    private static final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

    /**
     * 生成一个随机数
     * @param digit 随机数的位数  digit<=9
     * @return
     */
    public static int genRandomIntNum(int digit){
        if(digit>9){
            return Integer.MAX_VALUE;
        }else {
            return (int) ((Math.random() * 9 + 1) * Math.pow(10, digit - 1));
        }
    }

    /**
     * 生成一个32位uuid
     * @return
     */
    public static String genUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


    /**
     * 随机生成四位图片验证码
     */
    public static String randomString(int length) {
        if (JavaUtils.isEmpty(BASE_CHECK_CODES)) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(length);
            if (length < 1) {
                length = 1;
            }

            int baseLength = BASE_CHECK_CODES.length();

            for(int i = 0; i < length; ++i) {
                int number = ThreadLocalRandom.current().nextInt(baseLength);
                sb.append(BASE_CHECK_CODES.charAt(number));
            }

            return sb.toString();
        }
    }



}
