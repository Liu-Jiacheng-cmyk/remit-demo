package org.example.common.exceptions;

//用于转账流程中的任意异常情况抛出的自定义异常，用于捕获和处理网络、服务调用、消息队列等异常
public class TransferFailedException extends RuntimeException{
    public TransferFailedException(String msg){
        super(msg);
    }
}
