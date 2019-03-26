package com.github.sokolyaka.proxyFunctions.core.reflection;

import com.github.sokolyaka.proxyFunctions.core.function.IWrappedFunction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public final class ProxyWrapper {
    private ProxyWrapper() {
    }

    public static <T> T wrapToProxy(T objToWrap, IWrappedFunction f) {
        return wrapToProxy(objToWrap, new FunctionProxyInvocationHandler(objToWrap, f));
    }

    public static <T> T wrapToProxy(T objToWrap, InvocationHandler handler) {
        return
                (T) Proxy.newProxyInstance(
                        objToWrap.getClass().getClassLoader(),
                        objToWrap.getClass().getInterfaces(),
                        handler);
    }
}
