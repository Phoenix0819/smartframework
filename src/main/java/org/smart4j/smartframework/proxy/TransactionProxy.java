package org.smart4j.smartframework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.smartframework.annotation.Transaction;
import org.smart4j.smartframework.helper.DataBaseHelper;

import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * Created by lenovo on 2017-02-23.
 */
public class TransactionProxy implements Proxy {
    private static final Logger LOGGER= LoggerFactory.getLogger(TransactionProxy.class);
    private static final ThreadLocal<Boolean> FLAG_HOLDER=new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };
    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag=FLAG_HOLDER.get();
        Method method=proxyChain.getTargetMethod();
        if (!flag&&method.isAnnotationPresent(Transaction.class)){
            try {
                FLAG_HOLDER.set(true);
                DataBaseHelper.beginTransaction();
                LOGGER.debug("begin transaction");
                result=proxyChain.doProxyChain();
                DataBaseHelper.commitTransaction();
                LOGGER.debug("commit transaction");
            } catch (Exception e) {
                DataBaseHelper.rollbackTransaction();
                LOGGER.debug("rollback transaction");
                throw  e;
            }finally {
                FLAG_HOLDER.remove();
            }
        }else {
            result=proxyChain.doProxyChain();
        }
        return result;
    }
}
