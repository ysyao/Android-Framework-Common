package cn.sccl.ilife.android.sample.photoview;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.google.inject.Inject;

import cn.sccl.ilife.android.core.httpclient.ILifeHttpRequestHandler;
import cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse;
import cn.sccl.ilife.android.core.httpclient.responsehandler.ILifeHttpResponseHandler;
import cn.sccl.ilife.android.core.service.ProgressDialogService;
import cn.sccl.ilife.android.ui.ILifeActivity;

public class PhotoService extends ProgressDialogService implements ListenedAsyncHttpCallbackResponse.OnProgressCallbackListener {
    @Inject
    public PhotoService(Context context) {
        super(context);
    }

    public ILifeHttpRequestHandler getImage(String url, ILifeHttpResponseHandler responseHandler) {
        responseHandler.setOnProgressCallbackListener(this);
        return new ILifeHttpRequestHandler(getHttpClient().get(getServiceContext(),url,responseHandler));
    }

    @Override
    public void onProgress(int bytesWritten, int totalSize) {

    }
}
