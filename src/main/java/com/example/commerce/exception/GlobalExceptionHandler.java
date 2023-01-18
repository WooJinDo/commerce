package com.example.commerce.exception;

import com.example.commerce.exception.model.MemberErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice   // 모든 controller 에서 발생하는 exception 처리
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public MemberErrorResponse handleMemberException(MemberException e, HttpServletRequest request) {
        log.error("{} is occurred.", e.getErrorCode());
        log.error(e.getErrorMessage());
        String msg = e.getErrorMessage();
        request.setAttribute("errorMessage", msg);

        return new MemberErrorResponse(e.getErrorCode(), e.getErrorMessage());
    }
}
