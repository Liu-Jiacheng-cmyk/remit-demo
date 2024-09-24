package org.example.common.exceptions;

//乐观锁冲突（并发更新失败）时抛出的自定义异常。
public class ConcurrentModificationException extends RuntimeException{
    public ConcurrentModificationException(String msg) {
        super(msg);
    }
}
