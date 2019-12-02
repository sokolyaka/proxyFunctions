package com.github.sokolyaka.proxyfunctionsexample.view;

import android.view.View;

import com.github.sokolyaka.proxyfunctionsexample.R;

public class MainView implements IMainView {
    private final View view;

    public MainView(View view) {
        this.view = view;
    }

    @Override
    public void showContentLoadedMessage() {
        view.findViewById(R.id.tv_content).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContentLoadedMessage() {
        view.findViewById(R.id.tv_content).setVisibility(View.GONE);
    }
}
