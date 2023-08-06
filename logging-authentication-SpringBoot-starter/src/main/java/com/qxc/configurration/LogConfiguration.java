package com.qxc.configurration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 10:02
 * @Version 1.0
 * @PACKAGE com.qxc.configurration
 */
@Component
@PropertySource("classpath:aopstart.properties")
@ConfigurationProperties(prefix = "log")
public class LogConfiguration {
    @Value("${order:1000}")
    int order;

    public int getOrder() {
        return order;
    }

    public LogConfiguration setOrder(int order) {
        this.order = order;
        return this;
    }

    @Override
    public String toString() {
        return "LogConfiguration{" +
                "order=" + order +
                '}';
    }
}
