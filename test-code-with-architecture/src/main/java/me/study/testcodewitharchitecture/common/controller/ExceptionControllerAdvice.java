package me.study.testcodewitharchitecture.common.controller;

import lombok.RequiredArgsConstructor;
import me.study.testcodewitharchitecture.common.domain.exception.CertificationCodeNotMatchedException;
import me.study.testcodewitharchitecture.common.domain.exception.ResourceNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFoundException(ResourceNotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(CertificationCodeNotMatchedException.class)
    public String certificationCodeNotMatchedException(CertificationCodeNotMatchedException exception) {
        return exception.getMessage();
    }

}
