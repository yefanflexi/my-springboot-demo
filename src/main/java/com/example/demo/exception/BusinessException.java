package com.example.demo.exception;

/**
 * 自定义业务异常
 *
 */
public class BusinessException extends RuntimeException{
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }
}
