package common;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 常用的一些方法
 */
public class JavaUtils {

    /**
     * 获取javabean的空值属性名
     */
    public static String[] getNullFileds(Object obj){
        final BeanWrapper src = new BeanWrapperImpl(obj);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for(PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            // 此处判断可根据需求修改
            if (isEmpty(srcValue)) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }



    /**
     * 判断某个值是否为空
     */
    public static boolean isEmpty(Object obj){
        if(null == obj){
            return true;
        }
        if(obj instanceof String){
            return "".equals(((String) obj).trim());
        }
        if(obj instanceof List){
                return ((List) obj).isEmpty();
        }
        if(obj instanceof ArrayList) {
            return ((ArrayList) obj).isEmpty();
        }
        if(obj instanceof Object[]){
            return ((Object[]) obj).length < 1;
        }
        return false;
    }

    /**
     * 判断是否不为空
     */
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }


}
