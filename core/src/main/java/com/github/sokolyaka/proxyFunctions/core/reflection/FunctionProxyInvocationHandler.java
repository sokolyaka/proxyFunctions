package com.github.sokolyaka.proxyFunctions.core.reflection;

import com.github.sokolyaka.proxyFunctions.core.function.IWrappedFunction;
import com.github.sokolyaka.proxyFunctions.core.function.IFunctionToInvoke;
import com.github.sokolyaka.proxyFunctions.core.reflection.exceptions.UncheckedIllegalAccessException;
import com.github.sokolyaka.proxyFunctions.core.reflection.exceptions.UncheckedInvocationTargetException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FunctionProxyInvocationHandler implements InvocationHandler {
    private final IFunctionToInvoke origin;
    private final IWrappedFunction f;

    public FunctionProxyInvocationHandler(IFunctionToInvoke origin, IWrappedFunction f) {
        this.origin = origin;
        this.f = f;
    }

    @Override
    public Object invoke(Object o, final Method method, final Object[] objects) throws Throwable {
        f.accept(
                new IFunctionToInvoke() {
                    @Override
                    public void invoke() {
                        try {
                            if (objects == null) {
                                method.invoke(origin);
                            } else {
                                method.invoke(origin, objects);
                            }
                        } catch (IllegalAccessException e) {
                            throw new UncheckedIllegalAccessException(e);
                        } catch (InvocationTargetException e) {
                            throw new UncheckedInvocationTargetException(e);
                        }
                    }
                }
        );

        return null;
    }
}
