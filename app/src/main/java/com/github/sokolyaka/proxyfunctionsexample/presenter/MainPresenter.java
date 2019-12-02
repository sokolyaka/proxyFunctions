package com.github.sokolyaka.proxyfunctionsexample.presenter;

import com.github.sokolyaka.proxyfunctionsexample.model.MainInteractor;
import com.github.sokolyaka.proxyfunctionsexample.view.IMainView;

public class MainPresenter implements IMainPresenter {
    private final IMainView view;
    private final MainInteractor interactor;

    public MainPresenter(IMainView view, MainInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    /**
     * Load some content with time delay in 5 seconds.
     * While the loading a user will see a loader in UI,
     * because the presenter wrapped with
     * {@link com.github.sokolyaka.proxyFunctions.core.function.InvokeTrackableFunction}
     * and
     * {@link com.github.sokolyaka.proxyFunctions.android.view.progress.FullscreenProgressBar}
     * <p>
     * The loading will appear in IO thread because the presenter wrapped with
     * {@link com.github.sokolyaka.proxyFunctions.core.function.AsyncFunction},
     * so no {@link android.os.NetworkOnMainThreadException}
     * <p>
     * But the {@code view.showContentLoadedMessage()} call will be done in main thread,
     * because the {@code view} wrapped with {@link com.github.sokolyaka.proxyFunctions.android.function.InMainThreadFunction}
     */
    @Override
    public void loadContentFromServer() {
        view.hideContentLoadedMessage();
        interactor.loadContentWitDelay();
        view.showContentLoadedMessage();

    }


    /**
     * The {@code view.showContentLoadedMessage()} line is not reachable,
     * because the use case throw an exception witch will be catch in a wrapper
     * {@link com.github.sokolyaka.proxyFunctions.core.function.SafeFunction}
     * {@link com.github.sokolyaka.proxyFunctions.android.view.error.ErrorMessageLoggerBridge}
     * {@link com.github.sokolyaka.proxyFunctions.android.view.error.IErrorMessageView}
     */
    @Override
    public void loadContentWithError() {
        view.hideContentLoadedMessage();
        interactor.loadContentWithException();
        view.showContentLoadedMessage();
    }
}
