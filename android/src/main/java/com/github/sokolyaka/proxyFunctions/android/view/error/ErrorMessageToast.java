package com.github.sokolyaka.proxyFunctions.android.view.error;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class ErrorMessageToast implements IErrorMessageView {
    private final WeakReference<Context> weakContext;

    public ErrorMessageToast(Context context) {
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
