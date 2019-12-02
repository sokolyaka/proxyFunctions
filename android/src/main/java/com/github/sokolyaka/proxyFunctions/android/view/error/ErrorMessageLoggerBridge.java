package com.github.sokolyaka.proxyFunctions.android.view.error;

import com.github.sokolyaka.proxyFunctions.core.function.SafeFunction;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorMessageLoggerBridge implements SafeFunction.Logger {
    private final IErrorMessageView errorMessageView;

    public ErrorMessageLoggerBridge(IErrorMessageView errorMessageView) {
        this.errorMessageView = errorMessageView;
    }

    @Override
    public void logError(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        errorMessageView.show(sw.toString());
    }
}
