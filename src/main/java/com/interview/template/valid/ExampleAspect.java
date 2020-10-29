package com.interview.template.valid;

import com.interview.template.dto.UserDTO;
import com.interview.template.exceptions.UsernameNotAllowed;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ExampleAspect {

    Logger log = LoggerFactory.getLogger(ExampleAspect.class);

    @Value("${reserve.words}")
    private String[] reservedWords;

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws  UsernameNotAllowed{

        Object[] args = joinPoint.getArgs();

        if(args!=null && args.length>0){

            if(args[0] instanceof UserDTO){

                UserDTO userDTO = (UserDTO)args[0];
                String username = userDTO.getUsername();

                for(String reservedWord : reservedWords){
                    if(reservedWord.equalsIgnoreCase(username)){
                        throw new UsernameNotAllowed("Username "+reservedWord+" not allowed");
                    }
                }
            }
        }

        Arrays.stream(args).forEach(str ->
        {
            if(str instanceof UserDTO){
                UserDTO userDTO = (UserDTO)str;

                log.info("email: "+userDTO.getEmail());
                log.info("password: "+userDTO.getPassword());
                log.info("username: "+userDTO.getUsername());
            }
        });

        long start = System.currentTimeMillis();

        Object proceed = null;

        try {
            proceed = joinPoint.proceed();
        } catch (Exception e){
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long executionTime = System.currentTimeMillis() - start;

        return proceed;
    }

}
