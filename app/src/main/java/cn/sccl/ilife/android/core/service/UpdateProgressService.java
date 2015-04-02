package cn.sccl.ilife.android.core.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.ProgressBar;

import cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse;

/**
 * 一个可以更新progressbar的service。
 * Created by yishiyao on 2015/3/13.
 */
public abstract class UpdateProgressService extends CheckInternetStateService implements
        ListenedAsyncHttpCallbackResponse.OnstartCallbackListener,
        ListenedAsyncHttpCallbackResponse.OnCancelCallbackListener,
        ListenedAsyncHttpCallbackResponse.OnProgressCallbackListener,
        ListenedAsyncHttpCallbackResponse.OnFinishCallbackListener{
    private ProgressBar progressBar;
    public UpdateProgressService(ProgressBar progressBar, Context context) {
        super(context);
        this.progressBar = progressBar;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onProgress(int bytesWritten, int totalSize) {
        progressBar.setMax(totalSize);
        progressBar.setProgress(bytesWritten);
    }

    @Override
    public void onCancel() {
        onActionCancelled(progressBar);
    }

    @Override
    public void onFinish() {
        onActionCancelled(progressBar);
    }

    @Override
    public void cancelAllRequest(boolean mayInterruptIfRunning) {
        super.cancelAllRequest(mayInterruptIfRunning);
        onActionCancelled(progressBar);
    }

    @Override
    public void cancelRequest(boolean mayInterruptIfRunning) {
        super.cancelRequest(mayInterruptIfRunning);
        onActionCancelled(progressBar);
    }

    public abstract void onActionCancelled(ProgressBar progressBar);

}
