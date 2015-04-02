package cn.sccl.ilife.android.core.httpclient;

import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;


/**
 * 一个可外部设置监听器的AsyncHttpResponseHandler，使得AsyncHttpResponseHandler每一个环节都可以外部手动设置代码。
 *
 * Created by yishiyao on 2015/3/12.
 */
public abstract class ListenedAsyncHttpCallbackResponse {
    public interface OnstartCallbackListener {
        void onStart();
    }
    public interface OnProgressCallbackListener{
        void onProgress(int bytesWritten, int totalSize);
    }
    public interface OnFinishCallbackListener {
        void onFinish();
    }
    public interface  OnRetryCallbackListener {
        void onRetry(int retryNo);
    }
    public interface OnCancelCallbackListener {
        void onCancel();
    }

    //onstart监听
    protected OnstartCallbackListener onStartCallbackListener;
    //onprogress监听
    protected OnProgressCallbackListener onProgressCallbackListener;
    //onfinish监听
    protected OnFinishCallbackListener onFinishCallbackListener;
    //onretry监听
    protected OnRetryCallbackListener onRetryCallbackListener;
    //oncancel监听
    protected OnCancelCallbackListener onCancelCallbackListener;

    public void setOnStartCallbackListener(OnstartCallbackListener onStartCallbackListener){
        this.onStartCallbackListener = onStartCallbackListener;
    }
    public void setOnProgressCallbackListener(OnProgressCallbackListener onProgressCallbackListener){
        this.onProgressCallbackListener = onProgressCallbackListener;
    }
    public void setOnFinishCallbackListener(OnFinishCallbackListener onFinishCallbackListener){
        this.onFinishCallbackListener = onFinishCallbackListener;
    }
    public void setOnRetryCallbackListener(OnRetryCallbackListener onRetryCallbackListener){
        this.onRetryCallbackListener = onRetryCallbackListener;
    }
    public void setOnCancelCallbackListener(OnCancelCallbackListener onCancelCallbackListener){
        this.onCancelCallbackListener = onCancelCallbackListener;
    }

    private AsyncHttpResponseHandler handler;
    private Context context;

    public ListenedAsyncHttpCallbackResponse() {
        this.handler = initAsyncHttpResponseHandler();
    }
    public ListenedAsyncHttpCallbackResponse(Context context) {
        this.context = context;
        this.handler = initAsyncHttpResponseHandler();
    }
    public AsyncHttpResponseHandler getAsyncHttpResponseHandler() {
        return handler;
    }
    public Context getAdapterContext() {
        return context;
    }

    protected  abstract AsyncHttpResponseHandler initAsyncHttpResponseHandler();


}
