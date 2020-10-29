package com.interview.template.valid;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//@Aspect // indicate the component is used for aspect
//@Component
public class DelegateAspect {
/*
    @Around(value = "@annotation(anno)", argNames = "jp, anno") // aspect method who have the annotation @Delegate
    public Object handle(ProceedingJoinPoint joinPoint, Delegate delegate) throws Exception {
        Object obj = joinPoint.getThis(); // get the object

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod(); // get the origin method
        Class<?>[] parameterTypes = method.getParameterTypes();
        // Method target = obj.getClass().getMethod(delegate.value(), method.getParameterTypes()); // get the delegate method

        //return target.invoke(obj, joinPoint.getArgs()); // invoke the delegate method

        return new Object();
    }
*/
}
