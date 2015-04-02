package cn.sccl.ilife.android.core.httpclient.responsehandler.filehandler;

import android.content.Context;

import org.apache.http.Header;

import java.io.File;

import cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse;
import cn.sccl.ilife.android.core.httpclient.responsehandler.ILifeHttpResponseHandler;

/**
 * 下载文件（图片）的响应处理类，它将AsyncHttpCallbackResponseAdapter中的某些
 * 回调方法传导到自己身上。
 *
 * Created by yishiyao on 2015/3/12.
 */
public abstract class ILifeHttpFileResponseHandler extends ILifeHttpResponseHandler {
    public ILifeHttpFileResponseHandler(Context context) {
        super(context);
    }

    @Override
    public ListenedAsyncHttpCallbackResponse newInstanceOfListenedResponseHandler() {
        return new FileListenedAsyncHttpCallbackResponse(getResponseHandlerContext()) {

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                onILifeRequestSuccess(statusCode, headers, file);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                onILifeRequestFailed(statusCode, headers, throwable, file);
            }
        };
    }
    public void onILifeRequestSuccess(int statusCode, Header[] headers, File file){}
    public void onILifeRequestFailed(int statusCode, Header[] headers, Throwable throwable, File file){}
}
