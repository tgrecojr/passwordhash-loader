package com.greco.passwordhashloader;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class SpringAsyncConfiguration implements AsyncConfigurer {

    @Value("${passwordhash.executor.threadprefix}")
    private  String threadPrefix;
    @Value("${passwordhash.executor.corepoolsize}")
    private int corePoolSize;
    @Value("${passwordhash.executor.maxpoolsize}")
    private int maxPoolSize;
    @Value("${passwordhash.executor.queuecapacity}")
    private int queueCapacity;



    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor myExecutor = new ThreadPoolTaskExecutor();
        myExecutor.setCorePoolSize(corePoolSize);
        myExecutor.setMaxPoolSize(maxPoolSize);
        myExecutor.setQueueCapacity(queueCapacity);
        myExecutor.setThreadNamePrefix(threadPrefix);
        myExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        myExecutor.setWaitForTasksToCompleteOnShutdown(true);
        myExecutor.initialize();
        return myExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }
}
