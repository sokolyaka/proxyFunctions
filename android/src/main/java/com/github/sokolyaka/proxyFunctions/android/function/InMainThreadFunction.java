package com.github.sokolyaka.proxyFunctions.android.function;

import android.os.Handler;
import android.os.Looper;

import com.github.sokolyaka.proxyFunctions.core.function.IFunctionToInvoke;
import com.github.sokolyaka.proxyFunctions.core.function.IWrappedFunction;

public class InMainThreadFunction implements IWrappedFunction {
    private final IWrappedFunction origin;
    private final Handler handler;

    public InMainThreadFunction(IWrappedFunction origin) {
        this(origin, new Handler(Looper.getMainLooper()));
    }

    public InMainThreadFunction(IWrappedFunction origin, Handler handler) {
        this.origin = origin;
        this.handler = handler;
    }

    @Override
    public void accept(final IFunctionToInvoke f) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            origin.accept(f);
        } else {
            this.handler.post(
                    new Runnable() {

                        @Override
                        public void run() {
                            origin.accept(f);
                        }
                    });
        }
    }
}
