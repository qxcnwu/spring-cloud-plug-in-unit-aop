package com.qxc.Utiles;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 15:58
 * @Version 1.0
 * @PACKAGE com.qxc.Utiles
 */
@Component
public class BeanFactoryHelper implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    /**
     * 重写 BeanFactoryAware 接口的方法
     *
     * @param beanFactory ：参数赋值给本地属性之后即可使用 BeanFactory
     * @throws BeansException BeansException
     */
    @Override
    public void setBeanFactory(@NotNull BeanFactory beanFactory) throws BeansException {
        BeanFactoryHelper.beanFactory = beanFactory;
    }

    /**
     * 根据名称获取容器中的对象实例
     *
     * @param beanName ：注入的实例必须已经存在容器中，否则抛异常：NoSuchBeanDefinitionException
     * @return Object
     */
    public static @NotNull Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    /**
     * 根据 class 获取容器中的对象实例
     *
     * @param requiredType ：被注入的必须已经存在容器中，否则抛异常：NoSuchBeanDefinitionException
     * @param <T>          Class
     * @return 对象
     */
    public static <T> @NotNull T getBean(Class<T> requiredType) {
        return beanFactory.getBean(requiredType);
    }

    /**
     * 判断 spring 容器中是否包含指定名称的对象
     *
     * @param beanName bean名称
     * @return 是否存在
     */
    public static boolean containsBean(String beanName) {
        return beanFactory.containsBean(beanName);
    }

    /**
     * 判断类型和名称
     * @param beanName
     * @param aClass
     * @return
     */
    public static boolean containsBean(String beanName, Class<?> aClass) {
        if (!containsBean(beanName)) {
            return false;
        }
        return beanFactory.isTypeMatch(beanName, aClass);
    }
}