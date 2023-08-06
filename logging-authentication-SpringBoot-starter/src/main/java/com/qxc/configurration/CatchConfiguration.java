package com.qxc.configurration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 15:28
 * @Version 1.0
 * @PACKAGE com.qxc.configurration
 */
@Component
@PropertySource("classpath:aopstart.properties")
@ConfigurationProperties(prefix = "catch")
public class CatchConfiguration {
    @Value("${order:10}")
    int order;

    public int order() {
        return order;
    }

    public CatchConfiguration setOrder(int order) {
        this.order = order;
        return this;
    }

    @Override
    public String toString() {
        return "RequireConfiguration{" +
                "order=" + order +
                '}';
    }
}
