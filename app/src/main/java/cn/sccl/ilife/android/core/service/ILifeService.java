package cn.sccl.ilife.android.core.service;

import com.google.inject.Inject;

import android.content.Context;

import cn.sccl.ilife.android.core.httpclient.ILifeHttpClient;
import cn.sccl.ilife.android.core.httpclient.ResponseHandlerCallback;

/**
 * 用于网络行为的发起类，可以通过ILifeHttpClient发起和取消请求，且引入了响应回调onStart,onFinish,onRetry,
 * onCancel,onProgress，有时候也会发起一些与网络行为相关的ui变动。
 * 
 * @author yishiyao
 */
public class ILifeService {
	protected ILifeHttpClient client;
	private Context context;

	@Inject
	public ILifeService(Context context) {
		this.context = context;
		this.client = new ILifeHttpClient(context);
	}

	public ILifeService(ILifeHttpClient client, Context context) {
		this.client = client;
		this.context = context;
	}

	public ILifeHttpClient getHttpClient() {
		return client;
	}

	public Context getServiceContext() {
		return context;
	}

	/**
	 * 取消当前service所在环境的请求(根据Context参数来取消请求)
	 * 
	 * @param mayInterruptIfRunning
	 *            是否取消当前已经跑起来的和即将跑起来的请求
	 */
	public void cancelRequest(boolean mayInterruptIfRunning) {
		client.cancelRequests(getServiceContext(), mayInterruptIfRunning);
	}

	/**
	 * 取消全部请求
	 * 
	 * @param mayInterruptIfRunning
	 *            是否取消当前已经跑起来的和即将跑起来的请求
	 */
	public void cancelAllRequest(boolean mayInterruptIfRunning) {
		client.cancelAllRequests(mayInterruptIfRunning);
	}
}
