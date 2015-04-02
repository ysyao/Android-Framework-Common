package cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse;

/**
 * 一个下载文件json AsyncHttpResponseHandler类的适配器
 */
public abstract class JsonListenedAsyncHttpCallbackResponse extends ListenedAsyncHttpCallbackResponse {

    @Override
    protected AsyncHttpResponseHandler initAsyncHttpResponseHandler() {
        return new AsyncHttpResponseHandler() {
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
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JsonListenedAsyncHttpCallbackResponse.this.onSuccess(statusCode, headers, responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                JsonListenedAsyncHttpCallbackResponse.this.onFailure(statusCode, headers, responseBody, error);
            }
        };
    }

    public abstract void onSuccess(int statusCode, Header[] headers,
                          byte[] responseBody);
    public abstract void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error);

}
