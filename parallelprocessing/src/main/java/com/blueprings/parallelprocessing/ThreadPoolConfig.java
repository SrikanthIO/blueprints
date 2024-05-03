package com.blueprings.parallelprocessing;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class ThreadPoolConfig {

	  @Bean(name = "parallelTaskExecutor")
	    public Executor parallelThreadPoolTaskExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(10);
	        executor.setMaxPoolSize(25);
	        executor.setThreadNamePrefix("parallelTaskExecutor-");
	        executor.initialize();
	        return executor;
	    }

}