package cn.sccl.ilife.android.core.networkdetect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 检测是否能够上网
 * Created by yishiyao on 2015/3/16.
 */
public class OnlineChecker {

    private Context context;
    public OnlineChecker(Context context) {
        this.context = context;
    }

    /**
     * 检测网络是否连接
     * @return
     */
    public boolean checkIfOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 获取网络连接方式
     * @return
     */
    public int checkConnectionType() {
        ConnectivityManager cn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cn.getActiveNetworkInfo();
        return netInfo.getType();
    }
}
