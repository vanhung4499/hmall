package com.hnv99.hmall.common.web.exception;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hnv99.hmall.common.core.result.Result;
import com.hnv99.hmall.common.core.result.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.validation.ConstraintViolation;

import java.sql.SQLSyntaxErrorException;
import java.util.concurrent.CompletionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Global system exception handling
 * <p>
 * Adjusting the HTTP status code for exception handling, enriching the types of exceptions handled
 *
 * @author Gadfly
 * @since 2020-02-25 13:54
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public <T> Result<T> processException(BindException e) {
        log.error("BindException:{}", e.getMessage());
        String msg = e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("；"));
        return Result.failed(ResultCode.PARAM_ERROR, msg);
    }

    /**
     * Validation of RequestParam parameters
     *
     * @param e
     * @param <T>
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public <T> Result<T> processException(ConstraintViolationException e) {
        log.error("ConstraintViolationException:{}", e.getMessage());
        String msg = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("；"));
        return Result.failed(ResultCode.PARAM_ERROR, msg);
    }

    /**
     * Validation of RequestBody parameters
     *
     * @param e
     * @param <T>
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> Result<T> processException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException:{}", e.getMessage());
        String msg = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("；"));
        return Result.failed(ResultCode.PARAM_ERROR, msg);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public <T> Result<T> processException(NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return Result.failed(ResultCode.RESOURCE_NOT_FOUND);
    }

    /**
     * MissingServletRequestParameterException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public <T> Result<T> processException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return Result.failed(ResultCode.PARAM_IS_NULL);
    }

    /**
     * MethodArgumentTypeMismatchException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public <T> Result<T> processException(MethodArgumentTypeMismatchException e) {
        log.error(e.getMessage(), e);
        return Result.failed(ResultCode.PARAM_ERROR, "Type error");
    }

    /**
     * ServletException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServletException.class)
    public <T> Result<T> processException(ServletException e) {
        log.error(e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public <T> Result<T> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Illegal argument exception, reason: {}", e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonProcessingException.class)
    public <T> Result<T> handleJsonProcessingException(JsonProcessingException e) {
        log.error("Json conversion exception, reason: {}", e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    /**
     * HttpMessageNotReadableException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public <T> Result<T> processException(HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        String errorMessage = "Request body cannot be null";
        Throwable cause = e.getCause();
        if (cause != null) {
            errorMessage = convertMessage(cause);
        }
        return Result.failed(errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    public <T> Result<T> processException(TypeMismatchException e) {
        log.error(e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public <T> Result<T> processSQLSyntaxErrorException(SQLSyntaxErrorException e) {
        String errorMsg = e.getMessage();
        log.error(errorMsg);
        if (StrUtil.isNotBlank(errorMsg) && errorMsg.contains("denied to user")) {
            return Result.failed(ResultCode.FORBIDDEN_OPERATION);
        } else {
            return Result.failed(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CompletionException.class)
    public <T> Result<T> processException(CompletionException e) {
        if (e.getMessage().startsWith("feign.FeignException")) {
            return Result.failed("Microservice call exception");
        }
        return handleException(e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public <T> Result<T> processException(HttpRequestMethodNotSupportedException e) {
        log.info("Requested resource does not exist:{}", e.getMessage());
        return Result.failed(ResultCode.RESOURCE_NOT_FOUND);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BizException.class)
    public <T> Result<T> handleBizException(BizException e) {
        log.error("biz exception:{}", e.getMessage(), e);
        if (e.getResultCode() != null) {
            return Result.failed(e.getResultCode());
        }
        return Result.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public <T> Result<T> handleException(Exception e) {
        log.error("unknown exception:{}", e.getMessage(), e);
        String errorMsg = e.getMessage();
        if (StrUtil.isNotBlank(errorMsg) && errorMsg.contains("denied to user")) {
            return Result.failed(ResultCode.FORBIDDEN_OPERATION);
        }
        if (StrUtil.isBlank(errorMsg)) {
            errorMsg = "System exception";
        }
        return Result.failed(errorMsg);
    }

    /**
     * When there is a type error in the parameters, this is used for message conversion
     *
     * @param throwable exception
     * @return error message
     */
    private String convertMessage(Throwable throwable) {
        String error = throwable.toString();
        String regulation = "\\[\"(.*?)\"]+";
        Pattern pattern = Pattern.compile(regulation);
        Matcher matcher = pattern.matcher(error);
        String group = "";
        if (matcher.find()) {
            String matchString = matcher.group();
            matchString = matchString.replace("[", "").replace("]", "");
            matchString = matchString.replaceAll("\\\"", "") + " field type error";
            group += matchString;
        }
        return group;
    }

}

