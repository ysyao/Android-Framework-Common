package cn.sccl.ilife.android.core.httpclient;

import java.util.concurrent.ExecutorService;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectHandler;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.protocol.HttpContext;

import roboguice.RoboGuice;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

/**
 * 发送GET，POST，PUT，DELETE请求客户端，基于async-http-client
 * 
 * @author yishiyao
 */
public class ILifeHttpClient {
	private static AsyncHttpClient client = new AsyncHttpClient();
	private Context context;

	public ILifeHttpClient(Context context) {
		this.context = context;
		RoboGuice.getInjector(context).injectMembers(this);
	}

	protected Context getApplicationContext() {
		return context;
	}

	public HttpClient getHttpClient() {
		return client.getHttpClient();
	}

	public HttpContext getHttpContext() {
		return client.getHttpContext();
	}

	public void setCookieStore(CookieStore cookieStore) {
		client.setCookieStore(cookieStore);
	}

	public void setThreadPool(ExecutorService threadPool) {
		client.setThreadPool(threadPool);
	}

	public ExecutorService getThreadPool() {
		return client.getThreadPool();
	}

	public void setEnableRedirects(final boolean enableRedirects,
			final boolean enableRelativeRedirects,
			final boolean enableCircularRedirects) {
		client.setEnableRedirects(enableRedirects, enableRelativeRedirects,
				enableCircularRedirects);
	}

	public void setEnableRedirects(final boolean enableRedirects,
			final boolean enableRelativeRedirects) {
		client.setEnableRedirects(enableRedirects, enableRelativeRedirects);
	}

	public void setEnableRedirects(final boolean enableRedirects) {
		client.setEnableRedirects(enableRedirects);
	}

	public void setRedirectHandler(final RedirectHandler customRedirectHandler) {
		client.setRedirectHandler(customRedirectHandler);
	}

	public void setUserAgent(String userAgent) {
		client.setUserAgent(userAgent);
	}

	public int getMaxConnections() {
		return client.getMaxConnections();
	}

	public void setMaxConnections(int maxConnections) {
		client.setMaxConnections(maxConnections);
	}

	public int getTimeout() {
		return client.getTimeout();
	}

	public void setTimeout(int value) {
		client.setTimeout(value);
	}

	public int getConnectTimeout() {
		return client.getConnectTimeout();
	}

	public void setConnectTimeout(int value) {
		client.setConnectTimeout(value);
	}

	public int getResponseTimeout() {
		return client.getResponseTimeout();
	}

	public void setResponseTimeout(int value) {
		client.setResponseTimeout(value);
	}

	public void setProxy(String hostname, int port, String username,
			String password) {
		client.setProxy(hostname, port, username, password);
	}

	public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
		client.setSSLSocketFactory(sslSocketFactory);
	}

	public void removeAllHeaders() {
		client.removeAllHeaders();
	}

	public void addHeader(String header, String value) {
		client.addHeader(header, value);
	}

	public void removeHeader(String header) {
		client.removeHeader(header);
	}

	public void setBasicAuth(String username, String password) {
		client.setBasicAuth(username, password);
	}

	public void setCredentials(AuthScope authScope, Credentials credentials) {
		client.setCredentials(authScope, credentials);
	}

	public void cancelAllRequests(boolean mayInterruptIfRunning) {
		client.cancelAllRequests(mayInterruptIfRunning);
	}

	public void cancelRequests(final Context context,
			final boolean mayInterruptIfRunning) {
		client.cancelRequests(context, mayInterruptIfRunning);
	}

	public RequestHandle head(String url,
			ResponseHandlerInterface responseHandler) {
		return client.head(url, responseHandler);
	}

	public RequestHandle head(String url, RequestParams params,
			ResponseHandlerInterface responseHandler) {
		return client.head(url, params, responseHandler);
	}

	public RequestHandle get(String url,
			ResponseHandlerInterface responseHandler) {
		return client.get(url, responseHandler);
	}

	public RequestHandle get(String url, RequestParams params,
			ResponseHandlerInterface responseHandler) {
		return client.get(url, params, responseHandler);
	}

	public RequestHandle get(Context context, String url,
			ResponseHandlerInterface responseHandler) {
        Log.d("com.sccl.ilife.android", client.getUrlWithQueryString(false, url, null));
        return client.get(context, url, responseHandler);
	}

	public RequestHandle get(Context context, String url, RequestParams params,
			ResponseHandlerInterface responseHandler) {
        Log.d("com.sccl.ilife.android", client.getUrlWithQueryString(false, url, params));
		return client.get(context, url, params, responseHandler);
	}

	public RequestHandle get(Context context, String url, Header[] headers,
			RequestParams params, ResponseHandlerInterface responseHandler) {
		return client.get(context, url, headers, params, responseHandler);
	}

	public RequestHandle post(String url,
			ResponseHandlerInterface responseHandler) {
		return client.post(url, responseHandler);
	}

	public RequestHandle post(String url, RequestParams params,
			ResponseHandlerInterface responseHandler) {
		return client.post(url, params, responseHandler);
	}

	public RequestHandle post(Context context, String url,
			RequestParams params, ResponseHandlerInterface responseHandler) {
		return client.post(context, url, params, responseHandler);
	}

	public RequestHandle post(Context context, String url, HttpEntity entity,
			String contentType, ResponseHandlerInterface responseHandler) {
		return client.post(context, url, entity, contentType, responseHandler);
	}

	public RequestHandle post(Context context, String url, Header[] headers,
			RequestParams params, String contentType,
			ResponseHandlerInterface responseHandler) {
		return client.post(context, url, headers, params, contentType,
				responseHandler);
	}

	public RequestHandle post(Context context, String url, Header[] headers,
			HttpEntity entity, String contentType,
			ResponseHandlerInterface responseHandler) {
		return client.post(context, url, headers, entity, contentType,
				responseHandler);
	}

	public void setURLEncodingEnabled(boolean enabled) {
		client.setURLEncodingEnabled(enabled);
	}
}
