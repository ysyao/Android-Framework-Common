package cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler;

import org.apache.http.Header;

public interface ILifeJsonResponseInterface<T> {

    public abstract void onILifeRequestSuccess(int statusCode,
                                               Header[] headers, byte[] responseBody, T t);
//    public abstract void onILifeRequestFailed(int statusCode, Header[] headers,
//                                              byte[] responseBody, ILifeRequestError error);
    public void onILifeHttpConnectingFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error);
}
