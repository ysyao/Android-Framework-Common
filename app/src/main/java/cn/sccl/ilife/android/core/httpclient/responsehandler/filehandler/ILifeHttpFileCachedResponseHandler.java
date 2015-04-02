package cn.sccl.ilife.android.core.httpclient.responsehandler.filehandler;

import android.content.Context;

import org.apache.http.Header;

import java.io.File;

import cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse;

/**
 * 一个可以缓存文件的response handler
 * Created by yishiyao on 2015/3/17.
 */
public abstract class ILifeHttpFileCachedResponseHandler extends ILifeHttpFileResponseHandler
        implements ListenedAsyncHttpCallbackResponse.OnstartCallbackListener{

    public ILifeHttpFileCachedResponseHandler(Context context) {
        super(context);
        getListenedHandler().setOnStartCallbackListener(this);
    }

    @Override
    public void onILifeRequestSuccess(int statusCode, Header[] headers, File file) {
        super.onILifeRequestSuccess(statusCode, headers, file);
        //将下载的文件放入缓存

    }

    @Override
    public void onStart() {
        //查看在缓存中有没有这个即将获取的文件

    }
}
