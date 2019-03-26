package com.github.sokolyaka.proxyFunctions.core.function;

public class InvokeTrackableFunction implements IWrappedFunction {
    private final IWrappedFunction origin;
    private final InvokeCallback callback;

    public InvokeTrackableFunction(IWrappedFunction origin, InvokeCallback callback) {
        this.origin = origin;
        this.callback = callback;
    }

    @Override
    public void accept(IFunctionToInvoke f) {
        try {
            callback.before();
            origin.accept(f);
        } finally {
            callback.after();
        }
    }

    public interface InvokeCallback {
        void before();

        void after();
    }
}
