package com.github.sokolyaka.proxyFunctions.android.view.progress;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.github.sokolyaka.proxyFunctions.android.R;

import java.lang.ref.WeakReference;

public class FullscreenProgressBar implements IProgressBarView {

    private final WeakReference<Context> weakContext;
    private final int layoutRes;

    private Dialog dialog;

    public FullscreenProgressBar(Context context) {
        this(context, R.layout.progress_dialog);
    }

    public FullscreenProgressBar(Context context, int layoutRes) {
        weakContext = new WeakReference<>(context);
        this.layoutRes = layoutRes;
    }

    @Override
    public void show() {
        if (dialog != null) {
            return;
        }

        final Context context = weakContext.get();
        if (context == null) {
            return;
        }

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layoutRes);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.proccess_popup_drawable);
        dialog.show();
    }

    @Override
    public void dismiss() {
        if (dialog == null) {
            return;
        }

        dialog.dismiss();
        dialog = null;
    }
}
