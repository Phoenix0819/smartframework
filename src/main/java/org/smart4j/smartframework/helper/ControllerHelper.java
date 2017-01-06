package org.smart4j.smartframework.helper;

import org.smart4j.smartframework.annotation.Action;
import org.smart4j.smartframework.bean.Handler;
import org.smart4j.smartframework.bean.Request;
import org.smart4j.smartframework.util.ArrayUtil;
import org.smart4j.smartframework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by lenovo on 2016-12-29.
 */
public final class ControllerHelper {
    private static final Map<Request,Handler> ACTION_MAP=new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet=ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)){
            for (Class<?> controllerClass:controllerClassSet) {
                Method[] methods=controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)){
                    for (Method method:methods) {
                        if (method.isAnnotationPresent(Action.class)){
                            Action action=method.getAnnotation(Action.class);
                            String mapping=action.value();
                            if (mapping.matches("\\w+:/\\w*")){
                                String[] array=mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array)&&array.length==2){
                                    String requestMethod=array[0];
                                    String requestPath=array[1];
                                    Request request=new Request(requestMethod,requestPath);
                                    Handler handler=new Handler(controllerClass,method);
                                    ACTION_MAP.put(request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
     }

     public static Handler getHandler(String requestMethod,String requestPath){
        Request request=new Request(requestMethod,requestPath);
        return ACTION_MAP.get(request);
     }

}
