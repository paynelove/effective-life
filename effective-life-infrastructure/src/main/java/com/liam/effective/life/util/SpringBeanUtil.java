package com.liam.effective.life.util;

import com.alibaba.cola.exception.BizException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author ming
 */
@Slf4j
@Component
public class SpringBeanUtil implements ApplicationContextAware, EmbeddedValueResolverAware {

    private static ApplicationContext applicationContext;
    
    private static StringValueResolver stringValueResolver;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringBeanUtil.applicationContext == null) {
            SpringBeanUtil.applicationContext = applicationContext;
        }
    }
    
    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        SpringBeanUtil.stringValueResolver = stringValueResolver;
    }


    @NonNull
    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null){
            throw new BizException("Application Context has not been aware yet");
        }
        return applicationContext;
    }


    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }


    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz){
        return getApplicationContext().getBeansOfType(clazz);
    }


    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
    
    @SuppressWarnings("java:S108")
    public static <T> T getBeanOrNewInstance(Class<T> clazz){
        
        try {
            return getApplicationContext().getBean(clazz);
        } catch (NoSuchBeanDefinitionException ignored) {}
        
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.error("Make instance failed", e);
            throw new BizException(e.getMessage());
        }
    }
    
    /**
     * 动态读取配置
     */
    public static String getProperty(String name) {
        try {
            return stringValueResolver.resolveStringValue("${" + name + "}");
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
