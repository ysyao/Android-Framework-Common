package cn.sccl.ilife.android.core.localresource;

import com.google.inject.Inject;

import android.content.Context;

/**
 * 一个本地资源类，用来获取安卓本地资源
 * @author yishiyao
 *
 */
public class ILifeResource {
	private Context context;
    @Inject
    public ILifeResource(Context context) {
        this.context = context;
    }

    protected Context getApplicationContext() {
        return context;
    }
}
