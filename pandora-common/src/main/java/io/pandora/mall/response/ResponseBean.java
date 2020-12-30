package io.pandora.mall.response;

import io.pandora.mall.enume.CodeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * ResponseBean 统一响应对象
 *
 * @author Mr_zhou by date 2019/5
 */
@Data
@ApiModel(value = "通用PI接口返回", description = "Common Api Response")
public class ResponseBean<T> implements Serializable {

    private String msg;
    private Object data;
    private Integer errcode;

    public ResponseBean(int errcode, String msg, Object data) {
        this.errcode = errcode;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseBean succeed(Object data){
        return succeed(CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getMsg(),data);
    }

    public static ResponseBean succeed(String msg,Object data){
        return succeed(CodeEnum.SUCCESS.getCode(),msg,data);
    }

    public static ResponseBean succeed(Integer code, String msg,Object data){
        return new ResponseBean(code,msg,data);
    }

    public static ResponseBean failMsg(String msg){
        return succeed(CodeEnum.ERROR.getCode(),msg,null);
    }

    public static ResponseBean failMsgAndData(String msg,Object data){
        return succeed(CodeEnum.ERROR.getCode(),msg,data);
    }

    public static ResponseBean failCodeAndMsg(Integer code,String msg){
        return succeed(code,msg,null);
    }

    public static ResponseBean failWith(Integer code, String msg,Object data){
        return new ResponseBean(code,msg,data);
    }
}
