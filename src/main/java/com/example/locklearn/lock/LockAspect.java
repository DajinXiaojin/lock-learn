package com.example.locklearn.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
public class LockAspect {

    private final Logger logger = LoggerFactory.getLogger(LockAspect.class);

    private static String parseSpEL(String spel, MethodSignature methodSignature, Object[] args){
        Method method = methodSignature.getMethod();
        //获取方法参数名字
        String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
        if(parameterNames != null && parameterNames.length > 0){
            SpelExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(spel);
            EvaluationContext context = new StandardEvaluationContext();
            for (int i=0; i < args.length; i++){
                context.setVariable(parameterNames[i], args[i]);
            }
            return expression.getValue(context).toString();
        }
        return null;
    }


    @Around("@annotation(myLock) ")
    public Object myLockAspect(ProceedingJoinPoint joinPoint, MyLock myLock) throws Throwable {
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        if(myLock == null){
            //获取类上的注解
            myLock = joinPoint.getTarget().getClass().getDeclaredAnnotation(MyLock.class);
        }
        String key = myLock.key();
        if (key.contains("#")){
            //获取绑定参数后的key
            key = parseSpEL(key, methodSignature, args);
        }

        //获取分布式锁....略
        try {
            //执行原方法；如果需要动态修改成参数，必须调用joinPoint.proceed(Object[] args)
            //如果不返回这个的话，目标对象实际返回值会被置为null
            return joinPoint.proceed();
        }catch (Exception e){
            logger.error("执行锁方法异常", e);
        }finally {
            //释放锁...略
        }
        return null;
    }


}
