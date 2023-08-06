package com.qxc.interfaces;

import com.qxc.Utiles.BeanFactoryHelper;
import com.qxc.configurration.RequireConfiguration;
import com.qxc.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 15:28
 * @Version 1.0
 * @PACKAGE com.qxc.interfaces
 */
@Aspect
@Component
@Slf4j
public class RequireAop implements Ordered {

    private final HashMap<String, RequireInterface> canVisit = new HashMap<>();
    private final HashSet<String> canNotVisit = new HashSet<>();

    @Resource(type = RequireConfiguration.class)
    private RequireConfiguration requireConfiguration;

    @Pointcut("@annotation(com.qxc.interfaces.Require)")
    public void cutPoint() {
    }


    @Around("cutPoint()")
    public Object beforeRun(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final Method method = methodSignature.getMethod();
        final String name = method.getName();
        if (canNotVisit.contains(name)) {
        } else if (canVisit.containsKey(name)) {
            return canVisit.get(name).canVisit(joinPoint.getArgs()) ? joinPoint.proceed() : new Result().setMessage("wrong author");
        } else {
            if (canVisit(method)) {
                return canVisit.get(name).canVisit(joinPoint.getArgs()) ? joinPoint.proceed() : new Result().setMessage("wrong author");
            }
        }
        return new Result().setMessage("wrong author");
    }

    @Contract(pure = true)
    private boolean canVisit(@NotNull Method method) {
        final Require annotation = method.getAnnotation(Require.class);
        final Class<?> intercept = annotation.intercept();
        final String bName = annotation.staticMethod();
        // 查询是否为实现验证接口
        if (!RequireInterface.class.isAssignableFrom(intercept)) {
            canNotVisit.add(method.getName());
            return false;
        } else {
            // 如果注入了bean
            if (BeanFactoryHelper.containsBean(bName, intercept)) {
                canVisit.put(method.getName(), (RequireInterface) BeanFactoryHelper.getBean(bName));
                return true;
            } else {
                try {
                    final Constructor<?> constructor = intercept.getConstructor();
                    final RequireInterface instance = (RequireInterface) constructor.newInstance();
                    canVisit.put(method.getName(), instance);
                    return true;
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                         InvocationTargetException e) {
                    canNotVisit.add(method.getName());
                    return false;
                }
            }
        }
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
        return requireConfiguration.order();
    }
}
