package common;

import com.baomidou.mybatisplus.extension.api.R;

public class APIResult {
    public static R error(long code,String msg){
        return R.failed(msg).setCode(code);
    }

    public static R ok(Object data) {
        return R.ok(data);
    }
}
