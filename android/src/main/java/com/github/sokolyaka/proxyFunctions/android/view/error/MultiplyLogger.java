package com.github.sokolyaka.proxyFunctions.android.view.error;

import com.github.sokolyaka.proxyFunctions.core.function.SafeFunction;

public class MultiplyLogger implements SafeFunction.Logger {
    private final SafeFunction.Logger[] origins;

    public MultiplyLogger(SafeFunction.Logger... origins) {
        this.origins = origins;
    }

    @Override
    public void logError(Throwable t) {
        for (int i = 0; i < origins.length; i++) {
            origins[i].logError(t);
        }
    }
}
