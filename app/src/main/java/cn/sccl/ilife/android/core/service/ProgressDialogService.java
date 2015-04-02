package cn.sccl.ilife.android.core.service;

import com.google.inject.Inject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.app.FragmentManager;

import cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse;
import cn.sccl.ilife.android.core.httpclient.responsehandler.ILifeHttpResponseHandler;
import cn.sccl.ilife.android.ui.LightProgressDialog;
import cn.sccl.ilife.android.ui.ProgressDialogFragment;

/**
 * 一个在获取网络数据同时展示等待画面的service。
 * 
 * @author yishiyao
 */
public class ProgressDialogService extends CheckInternetStateService implements
        ListenedAsyncHttpCallbackResponse.OnFinishCallbackListener,
        ListenedAsyncHttpCallbackResponse.OnCancelCallbackListener{
    private AlertDialog lightProgressDialog;

	@Inject
	public ProgressDialogService(Context context) {
		super(context);
	}

	@Override
	public void onStart() {
		super.onStart();
        lightProgressDialog = LightProgressDialog.create(getServiceContext(), "请稍等");
        lightProgressDialog.show();
	}

	@Override
	public void onFinish() {
		dismissProgress();
	}

	@Override
	public void onCancel() {
		dismissProgress();
	}

	@Override
	public void cancelRequest(boolean mayInterruptIfRunning) {
		super.cancelRequest(mayInterruptIfRunning);
		dismissProgress();
	}

	/**
	 * 取消等待画面
	 */
	private void dismissProgress() {
        if (lightProgressDialog != null) {
            lightProgressDialog.dismiss();
            lightProgressDialog = null;
        }
	}

    protected void makeProgressEnable(ILifeHttpResponseHandler responseHandler) {
        responseHandler.setOnStartCallbackListener(this);
        responseHandler.setOnFinishCallbackListener(this);
        responseHandler.setOnCancelCallbackListener(this);
    }

}
