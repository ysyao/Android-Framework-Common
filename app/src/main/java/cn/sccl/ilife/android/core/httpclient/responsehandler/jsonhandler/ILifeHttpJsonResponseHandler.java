package cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler;

import org.apache.http.Header;
import cn.sccl.ilife.android.core.httpclient.HttpClientUtils;
import cn.sccl.ilife.android.core.httpclient.ILifeRequestError;
import cn.sccl.ilife.android.core.httpclient.responsehandler.ILifeHttpResponseHandler;


/**
 * 下载json、可监听的响应处理类。
 *
 * Created by yishiyao on 2015/3/12.
 */
public abstract class ILifeHttpJsonResponseHandler<T> extends ILifeHttpResponseHandler {
    @Override
    public JsonListenedAsyncHttpCallbackResponse newInstanceOfListenedResponseHandler() {
        return  new JsonListenedAsyncHttpCallbackResponse() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                ILifeRequestError error = HttpClientUtils.parseByte2JsonPojo(
                        ILifeRequestError.class, null, responseBody);
                if (HttpClientUtils.isRequestError(error)) {
                    onILifeRequestFailed(statusCode, headers, responseBody, error);
                } else {
                    T resultT = (T)HttpClientUtils.parseByte2JsonPojo(getType(), getListType(),
                            responseBody);
                    onILifeRequestSuccess(
                            statusCode,
                            headers,
                            responseBody,
                            resultT);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                onILifeHttpConnectingFailure(statusCode, headers, responseBody,
                        error);
            }
        };
    }

    public abstract void onILifeRequestSuccess(int statusCode,
                                               Header[] headers, byte[] responseBody, T t);
    public abstract void onILifeRequestFailed(int statusCode, Header[] headers,
                                              byte[] responseBody, ILifeRequestError error);
}
