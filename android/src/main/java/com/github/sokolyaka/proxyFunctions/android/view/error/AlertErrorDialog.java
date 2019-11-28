package com.github.sokolyaka.proxyFunctions.android.view.error;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.lang.ref.WeakReference;

public class AlertErrorDialog implements IErrorMessage {

    private final WeakReference<Context> weakContext;
    private final CharSequence title;
    private final CharSequence positiveBtnText;

    public AlertErrorDialog(Context context) {
        this(context, "Error", "Ok");
    }

    public AlertErrorDialog(Context context, CharSequence title, CharSequence positiveBtnText) {
        weakContext = new WeakReference<>(context);
        this.title = title;
        this.positiveBtnText = positiveBtnText;
    }

    @Override
    public void show(String errorMessage) {
        final Context context = weakContext.get();
        if (context == null) {
            return;
        }

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(errorMessage)
                .setPositiveButton(positiveBtnText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
