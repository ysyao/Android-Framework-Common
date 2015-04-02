package cn.sccl.ilife.android.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import java.util.List;

import cn.sccl.ilife.android.R;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * 一个带有swiperefreshlayout和listview的activity,这里的{@link T}是adapter的类型，
 * 默认必须继承{@link cn.sccl.ilife.android.ui.BaseListAdapter}，
 * 这里的{@link E}是获取到的json解析的来的adapter需要展示的每一行item的数据类型（POJO）。
 * 只需要实现{@link #newItemAdapterInstance(java.util.List)} )}方法，为adapter新建一个实例，
 * 就能无所忧虑地调用{@link #displayData(java.util.List)}用以展示数据。
 * Created by yishiyao on 2015/3/19.
 */
@ContentView(R.layout.activity_item_list)
public abstract class BaseListItemActivity<T extends BaseListAdapter, E> extends ILifeActivity{
    @InjectView(R.id.picture_list_view)
    protected ListView mListView;
    @InjectView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected T mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }
    protected ListView getListView() {
        return mListView;
    }

    /**
     * 通过获得的list数据展示成列表
     * @param jsonList
     */
    protected void displayData(List<E> jsonList) {
        if(mAdapter != null) {
            mAdapter.updateAdapter(jsonList);
        } else {
            mAdapter = newItemAdapterInstance(jsonList);
            mListView.setAdapter(mAdapter);
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    protected abstract T newItemAdapterInstance(List<E> jsonList);
}
