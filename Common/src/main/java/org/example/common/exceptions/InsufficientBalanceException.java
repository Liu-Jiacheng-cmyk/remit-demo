package org.example.common.exceptions;

//余额不足时抛出的自定义异常。
public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}
