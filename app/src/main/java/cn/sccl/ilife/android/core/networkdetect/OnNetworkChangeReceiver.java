package cn.sccl.ilife.android.core.networkdetect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 一个侦测是否能够连入互联网的receiver
 * Created by yishiyao on 2015/3/16.
 */
public class OnNetworkChangeReceiver extends BroadcastReceiver {
    public interface NetWorkChangeListener {
        public void onNetWorkNotAvailable(OnlineChecker checker);
        public void onNetWorkAvailable(OnlineChecker checker);
    }

    private boolean isAvailable = true;
    private NetWorkChangeListener listener;
    public OnNetworkChangeReceiver(NetWorkChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        OnlineChecker checker = new OnlineChecker(context);
        if(!checker.checkIfOnline()) {
            isAvailable = false;
            listener.onNetWorkNotAvailable(checker);
        } else {
            if(!isAvailable) {
                isAvailable = true;
                listener.onNetWorkAvailable(checker);
            }
        }
    }
}
