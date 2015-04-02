package cn.sccl.ilife.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.ui.ILifeActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * 一个可以调用Progress的Activity
 */
@ContentView(R.layout.activity_progress)
public abstract class ProgressActivity extends ILifeActivity {
    private LinearLayout customLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customLayout = (LinearLayout) findViewById(R.id.progress_custom_view);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        LayoutInflater inflater = LayoutInflater.from(this);
        customLayout.addView(onCreateCustomView(inflater));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_progress, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 在此方法当中初始化子activity的view，这个view会放到{@link #customLayout}当中。
     * @param inflater LayoutInflater，实例化view
     * @return         子activity的view
     */
    protected abstract View onCreateCustomView(LayoutInflater inflater);
    protected void showProgress(boolean isShow) {
        if(isShow) {
            customLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            customLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}
