package cn.sccl.ilife.android.sample.picturelist;

import android.content.Context;
import static cn.sccl.ilife.android.core.httpclient.ILifeConstants.SAMPLE_CODE_DOUBAN_BOOK;
import static cn.sccl.ilife.android.core.httpclient.ILifeConstants.SAMPLE_CODE_LOGIN;

import com.google.inject.Inject;
import com.loopj.android.http.RequestParams;

import cn.sccl.ilife.android.core.httpclient.ILifeHttpRequestHandler;
import cn.sccl.ilife.android.core.httpclient.responsehandler.ILifeHttpResponseHandler;
import cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler.ILifeHttpJsonResponseHandler;
import cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler.ILifeJsonResponseInterface;
import cn.sccl.ilife.android.core.service.ProgressDialogService;
import cn.sccl.ilife.android.sample.login.Account;

public class DouBanBookService extends ProgressDialogService {
    @Inject
    public DouBanBookService(Context context) {
        super(context);
    }

    public ILifeHttpRequestHandler getBooks( ILifeJsonResponseInterface<DouBanBookList> listener) {
//        responseHandler.setType(DouBanBookList.class);
//        makeProgressEnable(responseHandler);
        ILifeHttpJsonResponseHandler responseHandler =
                new ILifeHttpJsonResponseHandler<DouBanBookList>(DouBanBookList.class, listener);
        makeProgressEnable(responseHandler);

        RequestParams params = new RequestParams();
        params.add("q", "android");
        params.add("start", "0");
        params.add("count", "20");
//        return new ILifeHttpRequestHandler(client.get(getServiceContext(), SAMPLE_CODE_DOUBAN_BOOK, params, responseHandler));
        return sendRequest(SAMPLE_CODE_LOGIN, params, responseHandler);
    }
}
