package org.smart4j.smartframework.helper;

import org.smart4j.smartframework.annotation.Inject;
import org.smart4j.smartframework.util.ArrayUtil;
import org.smart4j.smartframework.util.CollectionUtil;
import org.smart4j.smartframework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by lenovo on 2016-12-29.
 */
public final class IocHelper {
    static {
        Map<Class<?>,Object> beanMap=BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            for (Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()) {
                Class<?> beanClass=beanEntry.getKey();//class org.smart4j.smartframework.pro.service.CustomerService,,class org.smart4j.smartframework.pro.controller.CustomerController
                Object beanInstance=beanEntry.getValue();//CustomerService@4891,CustomerController@4932
                Field[] beanFields=beanClass.getDeclaredFields();//{Field[0]@4925}{private org.smart4j.smartframework.pro.service.CustomerService org.smart4j.smartframework.pro.controller.CustomerController.customerService}
                if (ArrayUtil.isNotEmpty(beanFields)){
                    for (Field beanField:beanFields){
                        if (beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass=beanField.getType();
                            Object beanFieldInstance=beanMap.get(beanFieldClass);
                            if (beanFieldInstance!=null){
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }

    }
}
