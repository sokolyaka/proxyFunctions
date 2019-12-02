package com.github.sokolyaka.proxyFunctions.android.builder;

import android.content.Context;

import com.github.sokolyaka.proxyFunctions.android.function.InMainThreadFunction;
import com.github.sokolyaka.proxyFunctions.android.logger.error.LogCatLogger;
import com.github.sokolyaka.proxyFunctions.android.view.error.ErrorMessageAlertDialog;
import com.github.sokolyaka.proxyFunctions.android.view.error.ErrorMessageLoggerBridge;
import com.github.sokolyaka.proxyFunctions.android.view.error.IErrorMessageView;
import com.github.sokolyaka.proxyFunctions.android.view.error.MultiplyLogger;
import com.github.sokolyaka.proxyFunctions.android.view.progress.FullscreenProgressBar;
import com.github.sokolyaka.proxyFunctions.android.view.progress.IProgressBarView;
import com.github.sokolyaka.proxyFunctions.core.function.AsyncFunction;
import com.github.sokolyaka.proxyFunctions.core.function.FunctionCall;
import com.github.sokolyaka.proxyFunctions.core.function.IWrappedFunction;
import com.github.sokolyaka.proxyFunctions.core.function.InvokeTrackableFunction;
import com.github.sokolyaka.proxyFunctions.core.function.SafeFunction;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.github.sokolyaka.proxyFunctions.core.reflection.ProxyWrapper.wrapToProxy;

public final class FunctionCallStackBuilder {

    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    private String logTag;
    private boolean isProgressBarNeeded = true;
    private IProgressBarView progressBarView;
    private IErrorMessageView errorMessageView;
    private Context context;
    private ExecutorService executorService;
    private boolean isLogCatLogger = true;

    public FunctionCallStackBuilder setLogTag(String logTag) {
        this.logTag = logTag;
        return this;
    }

    public FunctionCallStackBuilder setProgressBar(IProgressBarView progressBar) {
        this.progressBarView = progressBar;
        return this;
    }

    public FunctionCallStackBuilder setErrorMessageView(IErrorMessageView errorMessageView) {
        this.errorMessageView = errorMessageView;
        return this;
    }

    public FunctionCallStackBuilder setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }

    public FunctionCallStackBuilder setLogCatLogger(boolean isLogCatLogger) {
        this.isLogCatLogger = isLogCatLogger;
        return this;
    }

    public FunctionCallStackBuilder setProgressBarNeeded(boolean isProgressBarNeeded) {
        this.isProgressBarNeeded = isProgressBarNeeded;
        return this;
    }

    public FunctionCallStackBuilder withContext(Context context) {
        this.context = context;
        return this;
    }

    public IWrappedFunction buildForView() {
        return
                new InMainThreadFunction(
                        new SafeFunction(
                                new FunctionCall(),
                                getLogger()));

    }

    public IWrappedFunction buildForPresenter() {
        IWrappedFunction result = new SafeFunction(new FunctionCall(), getLogger());

        if (isProgressBarNeeded) {
            result =
                    new InvokeTrackableFunction(
                            result,
                            new InvokeTrackableFunction.InvokeCallback() {
                                private final IProgressBarView dialog =
                                        wrapToProxy(
                                                getProgressBar(),
                                                buildForView());

                                @Override
                                public void before() {
                                    dialog.show();
                                }

                                @Override
                                public void after() {
                                    dialog.dismiss();
                                }
                            });
        }
        return
                new AsyncFunction(
                        result,
                        getExecutorService());

    }

    private SafeFunction.Logger getLogger() {
        final SafeFunction.Logger result =
                new ErrorMessageLoggerBridge(
                        wrapToProxy(
                                getErrorMessageView(),
                                buildForView()));
        if (isLogCatLogger) {
            return new MultiplyLogger(
                    new LogCatLogger(getTag()),
                    result);
        }

        return result;
    }

    private ExecutorService getExecutorService() {
        if (executorService != null) {
            return executorService;
        }

        return cachedThreadPool;
    }


    private String getTag() {
        String tag;
        if (logTag != null) {
            tag = logTag;
        } else if (context != null) {
            tag = context.getClass().getSimpleName();
        } else {
            tag = "View#" + UUID.randomUUID().toString();
        }
        return tag;
    }

    private IErrorMessageView getErrorMessageView() {
        if (errorMessageView != null) {
            return errorMessageView;
        }

        if (context == null) {
            throw new IllegalStateException("Context required for default error message view view");
        }

        return new ErrorMessageAlertDialog(context);
    }

    private IProgressBarView getProgressBar() {
        if (progressBarView != null) {
            return progressBarView;
        }

        if (context == null) {
            throw new IllegalStateException("Context required for default progress bar view");
        }

        return new FullscreenProgressBar(context);
    }


}
