package com.example.demo.error;

import com.example.demo.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理类
 */
@ControllerAdvice(basePackages = {"com.example.demo",})
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    //如果返回为json数据或其他对象，就添加该注解
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest request,Exception e){

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(request.getRequestURI());
        errorInfo.setCode(ErrorInfo.SUCCESS);
        return errorInfo;

    }
}
