package org.smart4j.smartframework.helper;

import org.smart4j.smartframework.annotation.Controller;
import org.smart4j.smartframework.annotation.Inject;
import org.smart4j.smartframework.annotation.Service;
import org.smart4j.smartframework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2016-12-28.
 */
public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;
    static {
        String basePackage=ConfigHelper.getAppBasePackage();
        CLASS_SET= ClassUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet=new HashSet<>();
        for (Class<?> cls:CLASS_SET) {
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet=new HashSet<>();
        for (Class<?> cls:CLASS_SET) {
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet=new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }

    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet=new HashSet<>();
        for (Class<?> cls:CLASS_SET) {
            if (superClass.isAssignableFrom(cls)&&!superClass.equals(cls)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    public static  Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet=new HashSet<>();
        for (Class<?> cls:  CLASS_SET) {
            if (cls.isAnnotationPresent(annotationClass)){
                classSet.add(cls);
            }
        }
        return classSet;
    }


}
