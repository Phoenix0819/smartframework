package org.smart4j.smartframework.annotation;

import java.lang.annotation.*;

/**
 * Created by lenovo on 2017-02-22.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
