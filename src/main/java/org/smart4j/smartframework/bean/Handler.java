package org.smart4j.smartframework.bean;

import java.lang.reflect.Method;

/**
 * Created by lenovo on 2016-12-29.
 */
public class Handler {
    private Class<?> controllerClass;
    private Method actionMethod;

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }
}
