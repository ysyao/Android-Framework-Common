package cn.sccl.ilife.android.sample.picturelist;

import android.content.Context;
import static cn.sccl.ilife.android.core.httpclient.ILifeConstants.SAMPLE_CODE_DOUBAN_BOOK;

import com.google.inject.Inject;
import com.loopj.android.http.RequestParams;

import cn.sccl.ilife.android.core.httpclient.ILifeHttpRequestHandler;
import cn.sccl.ilife.android.core.httpclient.responsehandler.ILifeHttpResponseHandler;
import cn.sccl.ilife.android.core.service.ProgressDialogService;

public class DouBanBookService extends ProgressDialogService {
    @Inject
    public DouBanBookService(Context context) {
        super(context);
    }

    public ILifeHttpRequestHandler getBooks(ILifeHttpResponseHandler responseHandler) {
        responseHandler.setType(DouBanBookList.class);
//        makeProgressEnable(responseHandler);

        RequestParams params = new RequestParams();
        params.add("q", "android");
        params.add("start", "0");
        params.add("count", "20");
        return new ILifeHttpRequestHandler(client.get(getServiceContext(), SAMPLE_CODE_DOUBAN_BOOK, params, responseHandler));
    }
}
