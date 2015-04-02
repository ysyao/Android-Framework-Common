package cn.sccl.ilife.android.core.httpclient.responsehandler.filehandler;

import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.File;

import cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse;

/**
 * 一个下载文件（图片）、可监听的AsyncHttpResponseHandler。
 * Created by yishiyao on 2015/3/12.
 */
public abstract class FileListenedAsyncHttpCallbackResponse extends ListenedAsyncHttpCallbackResponse {

    public FileListenedAsyncHttpCallbackResponse(Context context) {
        super(context);
    }

    @Override
    protected AsyncHttpResponseHandler initAsyncHttpResponseHandler() {
        return new FileAsyncHttpResponseHandler(getAdapterContext()) {
            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                super.onProgress(bytesWritten, totalSize);
                if(onProgressCallbackListener != null) {
                    onProgressCallbackListener.onProgress(bytesWritten, totalSize);
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                if(onStartCallbackListener != null) {
                    onStartCallbackListener.onStart();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if(onFinishCallbackListener != null) {
                    onFinishCallbackListener.onFinish();
                }
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
                if(onRetryCallbackListener != null) {
                    onRetryCallbackListener.onRetry(retryNo);
                }
            }

            @Override
            public void onCancel() {
                super.onCancel();
                if(onCancelCallbackListener != null) {
                    onCancelCallbackListener.onCancel();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                FileListenedAsyncHttpCallbackResponse.this.onFailure(statusCode, headers, throwable, file);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                FileListenedAsyncHttpCallbackResponse.this.onSuccess(statusCode, headers, file);
            }
        };
    }

    public abstract void onSuccess(int statusCode, Header[] headers, File file);
    public abstract void onFailure(int statusCode, Header[] headers, Throwable throwable, File file);
}
