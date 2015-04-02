package cn.sccl.ilife.android.ui;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import cn.sccl.ilife.android.R;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;

/**
 * 一个带有水平ProgressBar、主内容显示为WebView的Activity
 * Created by yishiyao on 2015/2/27.
 */
public abstract class WebViewActivity extends ILifeActivity {
    @InjectView(R.id.login_webview)
    protected WebView webView;
    @InjectView(R.id.webview_progressbar)
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress == 100) {
                    setProgressBarInVisible();
                } else {
                    if(progressBar.getVisibility() == View.GONE) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                }
            }
        });
    }

    /**
     * 将水平进度条设置成为不可见状态
     */
    protected void setProgressBarInVisible() {
        progressBar.setVisibility(View.GONE);
    }
}
