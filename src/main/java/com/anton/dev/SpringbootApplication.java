package com.anton.dev;

import java.util.Arrays;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.anton.dev.servlet.AppServletListener;

//@SpringBootApplication
//public class SpringbootApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpringbootApplication.class, args);
//	}
//}

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class SpringbootApplication {
 
    public static void main(String[] args) 
    {
        ApplicationContext ctx = SpringApplication.run(SpringbootApplication.class, args);
 
        String[] beanNames = ctx.getBeanDefinitionNames();
         
        Arrays.sort(beanNames);
         
        for (String beanName : beanNames) {
            System.out.println("Bean: " + beanName);
        }
    }
    
    // http://stackoverflow.com/questions/32394862/how-to-register-servletcontextlistener-in-spring-boot/32400420#32400420
    // para registrar listener
    @Bean    
    public AppServletListener executorListener() {
       return new AppServletListener();
    }
    
    @PreDestroy
    public void destoy() {
    	System.out.println("Destroy.............");
    }
}