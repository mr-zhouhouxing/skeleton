package io.pandora.mall.exception;

/**
 * 参数异常
 *
 * @author mr_zhou
 * @since 2019-05-06
 */
public class ParamJsonException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public ParamJsonException() {}

    public ParamJsonException(String message) {
        super(message);
        this.message = message;
    }


}
