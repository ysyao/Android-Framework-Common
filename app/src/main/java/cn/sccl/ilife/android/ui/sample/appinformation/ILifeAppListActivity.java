package cn.sccl.ilife.android.ui.sample.appinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.inject.Inject;
import java.util.List;
import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.sample.appinformation.AppInformationResource;
import cn.sccl.ilife.android.sample.appinformation.AppInformationUtils;
import cn.sccl.ilife.android.sample.appinformation.InstalledDefaultApp;
import cn.sccl.ilife.android.ui.ILifeActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_ilife_app_list)
public class ILifeAppListActivity extends ILifeActivity {
    @Inject
    private AppInformationResource appResource;
    @InjectView(R.id.app_list)
    private ListView mLV;
    private DefaultAppAdapter mAdapter;
    //测试数据
    private static final String PACKAGE_NAME = "sccl.platfrom.app";
    private static final String MESSAGE_SEND_TO_INSTALLED_APP = "let us rock and roll!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //查询所有已安装的DEFAULT应用
        List<InstalledDefaultApp> installedDefaultApps = appResource.queryInstalledDefaultApps();
        if(installedDefaultApps == null || installedDefaultApps.size() == 0) {
            return;
        }

        List<InstalledDefaultApp> allApps = appResource.queryInstalledLauncherApps();
        //筛选应用，与给定数据匹配，判定哪些是安装的哪些是未安装的
        for(InstalledDefaultApp defaultApp : installedDefaultApps) {
            AppInformationUtils.setIfAppInstalled(defaultApp, true);
        }
        installedDefaultApps.addAll(allApps);

        //展示所有数据
        displayDefaultApps(installedDefaultApps);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ilife_app_list, menu);
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
     * 展示已安装Default应用
     * @param installedDefaultApps 已经安装DEFUALT应用列表
     */
    private void displayDefaultApps(List<InstalledDefaultApp> installedDefaultApps) {
        if(mAdapter == null) {
            mAdapter = new DefaultAppAdapter(this, installedDefaultApps);
            mLV.setAdapter(mAdapter);
            mLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    InstalledDefaultApp app = mAdapter.getItem(i);
                    if(app.getState() == InstalledDefaultApp.NOT_INSTALLED_APP) {
                        return;
                    }

                    Intent intent = appResource.createInstalledDefaultAppsIntentByPackageName(PACKAGE_NAME);
                    intent.putExtra("token", MESSAGE_SEND_TO_INSTALLED_APP);
                    startActivity(intent);
                }
            });
        } else {
            mAdapter.updateAdapter(installedDefaultApps);
        }
    }
}
