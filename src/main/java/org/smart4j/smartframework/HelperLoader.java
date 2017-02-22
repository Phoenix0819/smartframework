package org.smart4j.smartframework;

import org.smart4j.smartframework.helper.*;
import org.smart4j.smartframework.util.ClassUtil;

/**
 * Created by lenovo on 2016-12-29.
 */
public final class HelperLoader {
    public static void init(){
        Class<?>[] classList={
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls:classList){
            ClassUtil.loadClass(cls.getName(),true);
        }
    }
}
