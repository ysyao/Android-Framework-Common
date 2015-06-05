package cn.sccl.ilife.android.ui.sample.picturelist;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.inject.Inject;

import org.apache.http.Header;

import java.util.List;

import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.core.httpclient.ILifeRequestError;
import cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler.ILifeHttpJsonResponseHandler;
import cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler.ILifeJsonResponseInterface;
import cn.sccl.ilife.android.sample.picturelist.DouBanBook;
import cn.sccl.ilife.android.sample.picturelist.DouBanBookList;
import cn.sccl.ilife.android.sample.picturelist.DouBanBookService;
import cn.sccl.ilife.android.ui.BaseListItemActivity;
import cn.sccl.ilife.android.ui.ILifeActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_item_list)
public class ItemListActivity extends BaseListItemActivity<ItemAdapter, DouBanBook> {
    @Inject
    private DouBanBookService bookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        swipeRefreshLayout.setColorScheme(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                httpGetBookSearchResult();
            }
        });
        httpGetBookSearchResult();
    }

    @Override
    protected ItemAdapter newItemAdapterInstance(List<DouBanBook> jsonList) {
        return new ItemAdapter(this, jsonList);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bookService.cancelRequest(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void httpGetBookSearchResult() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        }, 300);
        bookService.getBooks(new ILifeJsonResponseInterface<DouBanBookList>() {
            @Override
            public void onILifeRequestSuccess(int statusCode, Header[] headers, byte[] responseBody, DouBanBookList douBanBookList) {
                displayData(douBanBookList.getBooks());
            }


            @Override
            public void onILifeHttpConnectingFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
