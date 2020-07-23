package com.zizhuling.common.exception;

/**
 * <p>
 * </P>
 *
 * @author hebiao Create on 2020/7/18 18:13
 * @version 1.0
 */
public class BusinessException extends AppException {
    private static final long serialVersionUID = -3232706417844019328L;

    public BusinessException() {
    }

    public BusinessException(int code) {
        super(code);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}

