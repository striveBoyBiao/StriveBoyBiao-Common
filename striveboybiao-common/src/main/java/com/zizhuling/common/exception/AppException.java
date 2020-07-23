package com.zizhuling.common.exception;

/**
 * <p>
 * </P>
 *
 * @author hebiao Create on 2020/7/18 18:14
 * @version 1.0
 */
public class AppException extends RuntimeException {
    private static final long serialVersionUID = -7611643172712984323L;
    int code = -1;

    public AppException() {
    }

    public AppException(int code) {
        this.code = code;
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(int code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
