package com.github.sokolyaka.proxyFunctions.android.function;

import android.os.Handler;
import android.os.Looper;

import com.github.sokolyaka.proxyFunctions.core.function.IWrappedFunction;
import com.github.sokolyaka.proxyFunctions.core.function.IFunctionToInvoke;

public class InMainThreadFunction implements IWrappedFunction {
    private final IWrappedFunction origin;
    private final Handler handler;

    public InMainThreadFunction(IWrappedFunction origin) {
        this.origin = origin;
        handler = new Handler(Looper.myLooper());
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
