package cn.sccl.ilife.android.sample.login;

import com.google.inject.Inject;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import cn.sccl.ilife.android.core.httpclient.ILifeHttpRequestHandler;
import cn.sccl.ilife.android.core.httpclient.ListenedAsyncHttpCallbackResponse;
import cn.sccl.ilife.android.core.httpclient.responsehandler.ILifeHttpResponseHandler;
import cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler.ILifeHttpJsonResponseHandler;
import cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler.ILifeJsonResponseInterface;
import cn.sccl.ilife.android.core.service.ProgressDialogService;
import static cn.sccl.ilife.android.core.httpclient.ILifeConstants.SAMPLE_CODE_LOGIN;

/**
 * 示例代码：获取登录数据
 *
 * @author yishiyao
 *
 */
public class LoginService extends ProgressDialogService {

	@Inject
	public LoginService(Context context) {
		super(context);
	}

	/**
	 * 获取account
	 *
	 * @param listener
	 *            响应处理
	 * @return ILifeHttpRequestHandler
	 */
	public ILifeHttpRequestHandler getAccount(
			ILifeJsonResponseInterface<Account> listener) {

		/**
		 * 这里是为了将Service当中的回调设置进入ResponseHandler，
		 * 这也是在Service当中引入回调的目的。
		 */
		ILifeHttpJsonResponseHandler responseHandler =
				new ILifeHttpJsonResponseHandler<Account>(Account.class, listener);
		makeProgressEnable(responseHandler);
		responseHandler.setParseType(ILifeHttpJsonResponseHandler.JsonType.OBJECT_TYPE);

		//组织请求参数
		RequestParams params = new RequestParams();
		params.add("action", "login_nopassword");
		params.add("mobile", "18980769871");

		return sendRequest(SAMPLE_CODE_LOGIN, params, responseHandler);
	}
}
