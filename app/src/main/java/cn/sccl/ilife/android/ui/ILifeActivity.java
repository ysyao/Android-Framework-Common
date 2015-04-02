package cn.sccl.ilife.android.ui;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import cn.sccl.ilife.android.core.networkdetect.OnNetworkChangeReceiver;
import cn.sccl.ilife.android.core.networkdetect.OnlineChecker;
import roboguice.activity.RoboActionBarActivity;
import roboguice.activity.RoboActivity;

/**
 * 一个actionbar始终有返回按钮的activity，且在载入页面时候侦听是否能够连入互联网，不可以则提示出来。
 * Created by yishiyao on 2015/3/11.
 */
public class ILifeActivity extends RoboActivity {
    private OnNetworkChangeReceiver onNetworkChangeReceiver;
    private IntentFilter receiverFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(onNetworkChangeReceiver == null || receiverFilter == null) {
            initBroadcastReceiver();
        }
        this.registerReceiver(onNetworkChangeReceiver, receiverFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(onNetworkChangeReceiver);
    }

    private void initBroadcastReceiver() {
        //注册监听器，在activity的onresume回调检测网络状态，在每一次网络行为之前检测网络状态
        receiverFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        onNetworkChangeReceiver = new OnNetworkChangeReceiver(new OnNetworkChangeReceiver.NetWorkChangeListener() {

            @Override
            public void onNetWorkNotAvailable(OnlineChecker checker) {
                onNotOnlineCallback(checker);
            }

            @Override
            public void onNetWorkAvailable(OnlineChecker checker) {
                onONlineCallback(checker);
            }
        });
    }

    protected void onNotOnlineCallback(OnlineChecker checker) {
        Toast.makeText(this, "当前网络不可用，请检查你的网络设置", Toast.LENGTH_LONG).show();
    }

    protected void onONlineCallback(OnlineChecker checker) {
        int type = checker.checkConnectionType();
        String str;
        if(type == ConnectivityManager.TYPE_WIFI) {
            str = "Wifi-已连接";
        } else if(type == ConnectivityManager.TYPE_MOBILE) {
            str = "移动数据-已连接";
        } else {
            str = "网络-已连接";
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}
