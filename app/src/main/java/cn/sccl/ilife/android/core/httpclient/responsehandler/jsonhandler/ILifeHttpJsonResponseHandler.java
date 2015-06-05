package cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler;

import org.apache.http.Header;

import cn.sccl.ilife.android.core.httpclient.HttpClientUtils;
import cn.sccl.ilife.android.core.httpclient.responsehandler.ILifeHttpResponseHandler;


/**
 * 下载json、可监听的响应处理类。
 *
 * Created by yishiyao on 2015/3/12.
 */
public class ILifeHttpJsonResponseHandler<T> extends ILifeHttpResponseHandler {
    public enum JsonType {
        LIST_OBJECT_TYPE,
        OBJECT_TYPE;
    }
    private Class<T> clazz;
    private JsonType jsonType = JsonType.OBJECT_TYPE;
    private ILifeJsonResponseInterface<T> listener;

    public ILifeHttpJsonResponseHandler(Class<T> clazz, ILifeJsonResponseInterface<T> listener) {
        super();
        this.clazz = clazz;
        this.listener = listener;
        loadJsonType();
    }

    public ILifeHttpJsonResponseHandler(Class<T> clazz, JsonType jsonType, ILifeJsonResponseInterface<T> listener) {
        super();
        this.listener = listener;
        this.clazz = clazz;
        this.jsonType = jsonType;
        loadJsonType();
    }

    public void setILifeJsonResponse(ILifeJsonResponseInterface<T> listener) {
        this.listener = listener;
    }

    public void setParseType(JsonType type) {
        this.jsonType = type;
        loadJsonType();
    }

    public void loadJsonType() {
        switch (this.jsonType) {
            case LIST_OBJECT_TYPE:
                setListType(clazz);
                break;
            case OBJECT_TYPE:
                setType(clazz);
                break;
            default:
                setType(clazz);
                break;
        }
    }

    @Override
    public void onILifeHttpConnectingFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        listener.onILifeHttpConnectingFailure(statusCode, headers, responseBody, error);
    }

    @Override
    public JsonListenedAsyncHttpCallbackResponse newInstanceOfListenedResponseHandler() {
        return  new JsonListenedAsyncHttpCallbackResponse() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                T resultT = (T)HttpClientUtils.parseByte2JsonPojo(getType(), getListType(),
                        responseBody);
                listener.onILifeRequestSuccess(
                        statusCode,
                        headers,
                        responseBody,
                        resultT);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                onILifeHttpConnectingFailure(statusCode, headers, responseBody,
                        error);
            }
        };
    }

}
