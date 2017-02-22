package org.smart4j.smartframework.proxy;

/**
 * Created by lenovo on 2017-02-22.
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
