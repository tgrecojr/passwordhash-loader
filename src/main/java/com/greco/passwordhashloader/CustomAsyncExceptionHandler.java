package com.greco.passwordhashloader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Slf4j
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final String EXCEPTION_MESSAGE_START = "Exception message: ";
    private static final String EXCPTION_METHOD_START = "Method name: ";
    private static final String EXCEPTION_PARAMS_START = "Param: ";

    @Override
    public void handleUncaughtException(final Throwable throwable, final Method method, final Object... obj) {
        log.debug(EXCEPTION_MESSAGE_START + throwable.getMessage());
        log.debug(EXCPTION_METHOD_START + method.getName());
        for (final Object param : obj) {
            log.debug(EXCEPTION_PARAMS_START + param);
        }
    }
}
