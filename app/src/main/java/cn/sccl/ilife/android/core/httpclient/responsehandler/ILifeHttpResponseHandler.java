package cn.sccl.ilife.android.core.httpclient.responsehandler;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse;
import roboguice.RoboGuice;
import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import static cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse.OnstartCallbackListener;

/**
 * 响应handler类，基于AsyncHttpResponseHanlder。
 *
 * @author yishiyao
 *
 */
public abstract class ILifeHttpResponseHandler implements ResponseHandlerInterface {
    private Type type;
    private Type listType;
    private int bufferSize = 8192;
    private Context context;

    private AsyncHttpResponseHandler responseHandler;
    private ListenedAsyncHttpCallbackResponse listenedAsyncHttpCallbackResponse;

    public ILifeHttpResponseHandler() {
        listenedAsyncHttpCallbackResponse = newInstanceOfListenedResponseHandler();
        responseHandler = listenedAsyncHttpCallbackResponse.getAsyncHttpResponseHandler();
    }

    public ILifeHttpResponseHandler(Context context) {
        RoboGuice.getInjector(context).injectMembers(this);
        this.context = context;
        listenedAsyncHttpCallbackResponse = newInstanceOfListenedResponseHandler();
        responseHandler = listenedAsyncHttpCallbackResponse.getAsyncHttpResponseHandler();
    }


    /**
     * 获取环境
     * @return Context
     */
    public Context getResponseHandlerContext() {
        return context;
    }

    /**
     * 获取带有回调函数和AsyncHttpResponseHandler的适配器
     * @return AsyncHttpCallbackResponseAdapter
     */
    public ListenedAsyncHttpCallbackResponse getListenedHandler() {
        return listenedAsyncHttpCallbackResponse;
    }

    /**
     * 当http连接失败的时候调用此方法
     * @param statusCode        http status code
     * @param headers           http headers
     * @param responseBody      http response body
     * @param error             http error
     */
    public abstract void onILifeHttpConnectingFailure(int statusCode,
                                                      Header[] headers, byte[] responseBody, Throwable error);

    /**
     * 返回一个可监听的async http response handler，作用过程是这样，ILifeResponseHandler找到ListenedAsyncHttpCallbackResponse，
     * ListenedAsyncHttpCallbackResponse会实例化一个AsyncHttpResponseHandler，然后将其全部回调过程都独立出来放到ListenedAsyncHttpCallbackResponse
     * 当中，这种分离会让我们可以自由调用AsyncHttpResponseHandler的方法，同时可以独立监听AsyncHttpResponseHandler的回调。
     *
     * @return AsyncHttpCallbackResponseAdapter
     */
    public abstract ListenedAsyncHttpCallbackResponse newInstanceOfListenedResponseHandler();

    public ILifeHttpResponseHandler setBufferSize(int bufferSize) {
        if (bufferSize < 1)
            throw new IllegalArgumentException(
                    "Buffer size must be greater than zero"); //$NON-NLS-1$
        this.bufferSize = bufferSize;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Type getListType() {
        return listType;
    }

    public ILifeHttpResponseHandler setType(Type type) {
        this.type = type;
        return this;
    }

    public ILifeHttpResponseHandler setListType(Type listType) {
        this.listType = listType;
        return this;
    }

    //***********************监听asynchttpresponsehandler***************
    public void setOnStartCallbackListener(OnstartCallbackListener onStartCallbackListener){
        listenedAsyncHttpCallbackResponse.setOnStartCallbackListener(onStartCallbackListener);
    }
    public void setOnProgressCallbackListener(ListenedAsyncHttpCallbackResponse.OnProgressCallbackListener onProgressCallbackListener){
        listenedAsyncHttpCallbackResponse.setOnProgressCallbackListener(onProgressCallbackListener);
    }
    public void setOnFinishCallbackListener(ListenedAsyncHttpCallbackResponse.OnFinishCallbackListener onFinishCallbackListener){
        listenedAsyncHttpCallbackResponse.setOnFinishCallbackListener(onFinishCallbackListener);
    }
    public void setOnRetryCallbackListener(ListenedAsyncHttpCallbackResponse.OnRetryCallbackListener onRetryCallbackListener){
        listenedAsyncHttpCallbackResponse.setOnRetryCallbackListener(onRetryCallbackListener);
    }
    public void setOnCancelCallbackListener(ListenedAsyncHttpCallbackResponse.OnCancelCallbackListener onCancelCallbackListener){
        listenedAsyncHttpCallbackResponse.setOnCancelCallbackListener(onCancelCallbackListener);
    }

    // *****************************开启复制粘贴模式******************************

    public URI getRequestURI() {
        return responseHandler.getRequestURI();
    }

    public Header[] getRequestHeaders() {
        return responseHandler.getRequestHeaders();
    }

    public void setRequestURI(URI requestURI) {
        responseHandler.setRequestURI(requestURI);
    }

    public void setRequestHeaders(Header[] requestHeaders) {
        responseHandler.setRequestHeaders(requestHeaders);
    }

    public void setCharset(final String charset) {
        responseHandler.setCharset(charset);
    }

    public String getCharset() {
        return responseHandler.getCharset();
    }

    public void onPreProcessResponse(ResponseHandlerInterface instance,
                                     HttpResponse response) {
        responseHandler.onPreProcessResponse(instance, response);
    }

    public void onPostProcessResponse(ResponseHandlerInterface instance,
                                      HttpResponse response) {
        responseHandler.onPostProcessResponse(instance, response);
    }
//
//	public void onRetry(int retryNo) {
//		responseHandler.onRetry(retryNo);
//	}
//
//	public void onCancel() {
//		responseHandler.onCancel();
//	}

    //	public void onProgress(int bytesWritten, int totalSize) {
//		responseHandler.onProgress(bytesWritten, totalSize);
//	}
//
//	public void onStart() {
//		responseHandler.onStart();
//	}
//
//	public void onFinish() {
//		responseHandler.onFinish();
//	}
//
    final public void sendProgressMessage(int bytesWritten, int bytesTotal) {
        responseHandler.sendProgressMessage(bytesWritten, bytesTotal);
    }

    final public void sendSuccessMessage(int statusCode, Header[] headers,
                                         byte[] responseBytes) {
        responseHandler.sendSuccessMessage(statusCode, headers, responseBytes);
    }

    final public void sendFailureMessage(int statusCode, Header[] headers,
                                         byte[] responseBody, Throwable throwable) {
        responseHandler.sendFailureMessage(statusCode, headers, responseBody,
                throwable);
    }

    final public void sendStartMessage() {
        responseHandler.sendStartMessage();
    }

    final public void sendFinishMessage() {
        responseHandler.sendFinishMessage();
    }

    final public void sendRetryMessage(int retryNo) {
        responseHandler.sendRetryMessage(retryNo);
    }

    final public void sendCancelMessage() {
        responseHandler.sendCancelMessage();
    }

    public void sendResponseMessage(HttpResponse response) throws IOException {
        responseHandler.sendResponseMessage(response);
    }
    @Override
    public void setUseSynchronousMode(boolean sync) {
        responseHandler.setUseSynchronousMode(sync);
    }

    @Override
    public boolean getUseSynchronousMode() {
        return responseHandler.getUseSynchronousMode();
    }
}
