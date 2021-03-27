package common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity<T> {

    @TableId(value="id",type = IdType.UUID)
    String id;
    Date createTime;
    Date updateTime;
    @TableLogic(value = "1",delval = "0") //逻辑删除标志 1-有效  0-无效
    Integer status;

    /**
     * 插入前执行
     */
    public void preInsert(){
        Date date = new Date();
        this.createTime=date;
        this.updateTime=date;
        this.status=1;
    }

    /**
     * 更新前执行
     */
    public void preUpdate(){
        this.updateTime=new Date();
    }

}
