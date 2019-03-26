package com.github.sokolyaka.proxyFunctions.core.function;

public class SyncFunction implements IWrappedFunction {
    private final IWrappedFunction origin;
    private final Object syncObj;

    public SyncFunction(IWrappedFunction origin) {
        this(origin, new Object());
    }

    public SyncFunction(IWrappedFunction origin, Object syncObj) {
        this.origin = origin;
        this.syncObj = syncObj;
    }

    @Override
    public void accept(IFunctionToInvoke f) {
        synchronized (syncObj) {
            origin.accept(f);
        }
    }
}
