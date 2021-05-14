package com.github.sokolyaka.proxyfunctionsexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.sokolyaka.proxyFunctions.android.builder.FunctionCallStackBuilder;
import com.github.sokolyaka.proxyfunctionsexample.model.MainInteractor;
import com.github.sokolyaka.proxyfunctionsexample.presenter.IMainPresenter;
import com.github.sokolyaka.proxyfunctionsexample.presenter.MainPresenter;
import com.github.sokolyaka.proxyfunctionsexample.view.MainView;

import static com.github.sokolyaka.proxyFunctions.core.reflection.ProxyWrapper.wrapToProxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FunctionCallStackBuilder callStackBuilder = new FunctionCallStackBuilder().withContext(this);

        final IMainPresenter presenter =
                wrapToProxy(
                        new MainPresenter(
                                wrapToProxy(
                                        new MainView(findViewById(android.R.id.content)),
                                        callStackBuilder.buildForView()),
                                new MainInteractor()),
                        callStackBuilder.buildForPresenter());

        findViewById(R.id.btn_load_from_server).setOnClickListener(v -> presenter.loadContentFromServer());
        findViewById(R.id.btn_load_with_error).setOnClickListener(v -> presenter.loadContentWithError());
    }
}
