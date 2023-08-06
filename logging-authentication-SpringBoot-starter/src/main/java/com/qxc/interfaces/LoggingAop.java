package com.qxc.interfaces;

import com.qxc.configurration.LogConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 0:38
 * @Version 1.0
 * @PACKAGE com.qxc.interfaces
 */
@Aspect
@Component
@Slf4j
public class LoggingAop implements Ordered {

    @Resource(type = LogConfiguration.class, name = "logConfiguration")
    private LogConfiguration logConfiguration;

    @Pointcut("@annotation(com.qxc.interfaces.Log)")
    public void cutPoint() {
    }

    @Around("cutPoint()")
    public Object logAround(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        Object targetObj = joinPoint.getTarget();
        // 获取切入点方法的名字
        String methodName = joinPoint.getSignature().getName();
        log.info("[" + targetObj.getClass().getName() + "]" + "[" + methodName + "]");
        // 获取方法上的注解
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        // 获取Annotation的参数
        final Log annotation = method.getAnnotation(Log.class);
        // 如果开启时间统计
        final boolean requireTime = annotation.requireTime();
        final long start = System.currentTimeMillis();
        // 执行方法
        final Object object = joinPoint.proceed();
        final long end = System.currentTimeMillis();
        if (requireTime) {
            log.info("[" + annotation.name() + "]:[" + annotation.info() + "][run time:" + (end - start) / 1000.0 + " s]");
        }
        return object;
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return logConfiguration.getOrder();
    }
}
