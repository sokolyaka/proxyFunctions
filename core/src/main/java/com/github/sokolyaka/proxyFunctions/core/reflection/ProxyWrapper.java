package com.github.sokolyaka.proxyFunctions.core.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public final class ProxyWrapper {
    private ProxyWrapper() {
    }

    public static <T> T wrapToProxy(T t, InvocationHandler handler) {
        return
                (T) Proxy.newProxyInstance(
                        t.getClass().getClassLoader(),
                        t.getClass().getInterfaces(),
                        handler);
    }
}
