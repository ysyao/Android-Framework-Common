package cn.sccl.ilife.android.core.httpclient;


public interface ResponseHandlerCallback {
	void onProgress(int bytesWritten, int totalSize);

    void onStart();

    void onFinish();

    void onRetry(int retryNo);

    void onCancel();

}
