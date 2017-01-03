package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

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
                Class<?> beanClass=beanEntry.getKey();
                Object beanInstance=beanEntry.getValue();
                Field[] beanFields=beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)){
                    for (Field beanField:beanFields){
                        if (beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass=beanField.getType();
                            Object beanFieldInstance=beanMap.get(beanFieldClass);
                            if (beanFieldInstance!=null){
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                                System.out.println("888888888888888"+beanInstance+beanField+beanFieldInstance+"999999999");
                            }
                        }
                    }
                }
            }
        }

    }
}
