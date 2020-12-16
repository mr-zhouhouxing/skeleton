package io.pandora.mall.exception;

/**
 * @author zhouhouxing
 * @version 1.0
 * @Description token异常
 * @date 2019/5/3
 */
public class TokenException extends RuntimeException {

    private static final long serialVersionUID = 8074335786977654867L;

    public TokenException() {
        super();
    }

    public TokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }
}
