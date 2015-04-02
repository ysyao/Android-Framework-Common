package cn.sccl.ilife.android.core.httpclient;

import com.loopj.android.http.RequestHandle;

/**
 * 请求handler，基于RequestHandler类
 * 
 * @author yishiyao
 *
 */
public class ILifeHttpRequestHandler {
	private RequestHandle handler;

	public ILifeHttpRequestHandler(RequestHandle handler) {
		this.handler = handler;
	}

	/**
	 * 取消请求，有可能会取消请求失败，一般情况下不用这个取消请求，而是用Service当中的cancelRequest
	 * 
	 * @param mayInterruptIfRunning
	 * @return
	 */
	public boolean cancel(boolean mayInterruptIfRunning) {
		return handler.cancel(mayInterruptIfRunning);
	}

	/**
	 * Returns true if this task completed. Completion may be due to normal
	 * termination, an exception, or cancellation -- in all of these cases, this
	 * method will return true.
	 *
	 * @return true if this task completed
	 */
	public boolean isFinished() {
		return handler.isFinished();
	}

	/**
	 * Returns true if this task was cancelled before it completed normally.
	 *
	 * @return true if this task was cancelled before it completed
	 */
	public boolean isCancelled() {
		return handler.isCancelled();
	}

	public boolean shouldBeGarbageCollected() {
		return handler.shouldBeGarbageCollected();
	}
}
