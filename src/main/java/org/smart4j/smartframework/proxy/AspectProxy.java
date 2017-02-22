package org.smart4j.smartframework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.smartframework.util.PropsUtil;

import java.lang.reflect.Method;


/**
 * Created by lenovo on 2017-02-22.
 */
public abstract class AspectProxy implements Proxy {
    //private static final Logger logger= LoggerFactory.getLogger(AspectProxy.class);
    private static final Logger LOGGER= LoggerFactory.getLogger(AspectProxy.class);
    
    @Override
    public final Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result=null;
        Class<?> cls=proxyChain.getTargetClass();
        Method method=proxyChain.getTargetMethod();
        Object[] params=proxyChain.getMethodParams();
        
        begin();
        try {
            if (intercept(cls,method,params)){
                before(cls,method,params);
                result=proxyChain.doProxyChain();
                after(cls,method,params,result);
            }else {
                result=proxyChain.doProxyChain();
            }
        }catch (Exception e){
            LOGGER.error("proxy failure",e);
            error(cls,method,params,e);
            throw e;
        }finally {
            end();
        }
        return null;
    }

    public void end() {
    }

    public void error(Class<?> cls, Method method, Object[] params, Throwable e) {
    }

    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable{
    }

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable{
    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable{
        return true;
    }

    public void begin() {
    }
}
