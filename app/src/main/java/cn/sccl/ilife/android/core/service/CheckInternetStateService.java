package cn.sccl.ilife.android.core.service;

import com.google.inject.Inject;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse;
import cn.sccl.ilife.android.core.networkdetect.OnNetworkChangeReceiver;
import cn.sccl.ilife.android.core.networkdetect.OnlineChecker;

/**
 * 一个可以查看手机是否可以加入互联网的Service。
 *
 * @author yishiyao
 *
 */
public class CheckInternetStateService extends ILifeService implements
        ListenedAsyncHttpCallbackResponse.OnstartCallbackListener{
    private OnlineChecker onlineChecker;

    @Inject
    public CheckInternetStateService(Context context) {
        super(context);
        onlineChecker = new OnlineChecker(context);
    }

    @Override
    public void onStart() {
        if(!onlineChecker.checkIfOnline()) {
            cancelRequest(true);
        }
    }
}
