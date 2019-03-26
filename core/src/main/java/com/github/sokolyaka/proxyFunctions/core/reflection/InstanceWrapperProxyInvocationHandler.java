package com.github.sokolyaka.proxyFunctions.core.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InstanceWrapperProxyInvocationHandler implements InvocationHandler {
    private Object instanceToWrap;

    public InstanceWrapperProxyInvocationHandler() {
        this(null);
    }

    public InstanceWrapperProxyInvocationHandler(Object instanceToWrap) {
        this.instanceToWrap = instanceToWrap;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        try {
            if (instanceToWrap == null) {
                throw new IllegalStateException("wrapped instance not available");
            }
            if (objects == null) {
                return method.invoke(instanceToWrap);
            } else {
                return method.invoke(instanceToWrap, objects);
            }
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
