package org.smart4j.smartframework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.smartframework.annotation.Aspect;
import org.smart4j.smartframework.annotation.Service;
import org.smart4j.smartframework.proxy.AspectProxy;
import org.smart4j.smartframework.proxy.Proxy;
import org.smart4j.smartframework.proxy.ProxyManager;
import org.smart4j.smartframework.proxy.TransactionProxy;

import javax.swing.text.StyledEditorKit;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by lenovo on 2017-02-22.
 */
public final class AopHelper {
    private static final Logger LOGGER= LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            Map<Class<?>,Set<Class<?>>> proxyMap=createProxyMap();
            Map<Class<?>,List<Proxy>> targetMap=createTargetMap(proxyMap);
            for (Map.Entry<Class<?>,List<Proxy>> targetEntry:targetMap.entrySet()) {
                Class<?> targetClass=targetEntry.getKey();
                List<Proxy> proxyList=targetEntry.getValue();
                Object proxy= ProxyManager.createProxy(targetClass,proxyList);
                BeanHelper.setBean(targetClass,proxy);
            }

        } catch (Exception e) {
            LOGGER.error("aop failure",e);
        }
    }

    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
        Set<Class<?>> targetClassSet=new HashSet<>();
        Class<? extends Annotation> annotation=aspect.value();
        if (annotation!=null&&!annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception{
        System.out.println("进aopHelper");
        Map<Class<?>,Set<Class<?>>> proxyMap=new HashMap<>();
        addAspectProxy(proxyMap);
        addTransactionProxy(proxyMap);
        return proxyMap;
    }

    private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
        Set<Class<?>> serviceClassSet=ClassHelper.getClassSetBySuper(Service.class);
        proxyMap.put(TransactionProxy.class,serviceClassSet);
    }

    private static void addAspectProxy(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
        Set<Class<?>> proxyClassSet=ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClass:proxyClassSet) {
            if (proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect=proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet=createTargetClassSet(aspect);
                proxyMap.put(proxyClass,targetClassSet);
            }
        }
    }

    private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
        Map<Class<?>,List<Proxy>> targetMap=new HashMap<>();
        for (Map.Entry<Class<?>,Set<Class<?>>> proxyEntry:proxyMap.entrySet()) {
            Class<?> proxyClass=proxyEntry.getKey();
            Set<Class<?>> targetClassSet=proxyEntry.getValue();
            for (Class<?> targetClass:targetClassSet) {
                Proxy proxy=(Proxy)proxyClass.newInstance();
                if (targetMap.containsKey(targetClass)){
                    targetMap.get(targetClass).add(proxy);
                }else {
                    List<Proxy> proxyList=new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass,proxyList);
                }
            }
        }
        return targetMap;
    }
}
