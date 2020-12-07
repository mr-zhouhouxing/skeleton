package io.pandora.mall.exception;

/**
 * 自定义异常(CustomException)
 * @author John
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 8074335786977654867L;

    public CustomException() {
        super();
    }

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

}
