package com.github.sokolyaka.proxyFunctions.android.logger.error;

import android.util.Log;

import com.github.sokolyaka.proxyFunctions.core.function.SafeFunction;

public class LogCatLogger implements SafeFunction.Logger {
    private final String tag;

    public LogCatLogger(String tag) {
        this.tag = tag;
    }

    @Override
    public void logError(Throwable t) {
        Log.e(tag, "error", t);
    }
}
