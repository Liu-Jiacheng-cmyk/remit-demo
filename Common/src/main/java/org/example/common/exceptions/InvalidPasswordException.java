package org.example.common.exceptions;

//密码校验失败时抛出的自定义异常。
public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String msg) {
        super(msg);
    }
}
