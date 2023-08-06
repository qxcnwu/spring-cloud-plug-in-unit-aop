package com.qxc.interfaces;

import com.qxc.configurration.CatchConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 16:42
 * @Version 1.0
 * @PACKAGE com.qxc.interfaces
 */
@Aspect
@Component
@Slf4j
public class CatchThrowableAOP implements Ordered {

    @Resource(type = CatchConfiguration.class)
    private CatchConfiguration catchConfiguration;

    @Pointcut("@annotation(com.qxc.interfaces.CatchThrowable)")
    public void cutPoint() {}

    @AfterThrowing(pointcut = "cutPoint()",throwing = "ex")
    public void afterThrowing(JoinPoint point, @NotNull Throwable ex) {
        log.error(ex.getLocalizedMessage());
        log.error(ex.getMessage());
        log.error(ex.getCause().getMessage());
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
        return catchConfiguration.order();
    }
}
