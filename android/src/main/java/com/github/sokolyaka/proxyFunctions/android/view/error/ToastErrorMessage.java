package com.github.sokolyaka.proxyFunctions.android.view.error;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class ToastErrorMessage implements IErrorMessage {
    private final WeakReference<Context> weakContext;

    public ToastErrorMessage(Context context) {
        weakContext = new WeakReference<>(context);
    }

    @Override
    public void show(String errorMessage) {
        final Context context = weakContext.get();
        if (context == null) {
            return;
        }

        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
    }
}
