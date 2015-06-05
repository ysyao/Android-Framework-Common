package cn.sccl.ilife.android.core.httpclient.responsehandler;

import org.apache.http.Header;

import cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse;

public interface ILifeResponseInterface {
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
}
