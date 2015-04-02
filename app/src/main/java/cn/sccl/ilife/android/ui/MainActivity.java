package cn.sccl.ilife.android.ui;

import cn.sccl.ilife.android.ui.sample.actionbarsample.HomeActivity;
import cn.sccl.ilife.android.ui.sample.appinformation.ILifeAppListActivity;
import cn.sccl.ilife.android.ui.sample.cardapplication.CardApplicationListActivity;
import cn.sccl.ilife.android.ui.sample.login.AccountActivity;
import cn.sccl.ilife.android.ui.sample.photoview.PhotoViewListActivity;
import cn.sccl.ilife.android.ui.sample.picturelist.ItemListActivity;
import cn.sccl.ilife.android.ui.sample.waitingflag.ProgressSampleActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import cn.sccl.ilife.android.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends ILifeActivity {
    @InjectView(R.id.function_list)
    private ListView mLV;
    private String[] functions = {
            "获取网络数据", "已安装本应用APP列表", "下载图片", "等待标志", "图片列表", "卡片卡槽","登录页面","设置页面","购买商品页面","item布局", "动作栏","刷卡页面"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().setTitle("示例");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mLV.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, functions));
        mLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i) {
                    case 0:
                        intent = new Intent(MainActivity.this, AccountActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, ILifeAppListActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, PhotoViewListActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, ProgressSampleActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, ItemListActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, CardApplicationListActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(MainActivity.this, SetActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(MainActivity.this, BuyAppletActivity.class);
                        startActivity(intent);
                        break;
                    case 9:
                        intent = new Intent(MainActivity.this, ListItemLayoutTestActivity.class);
                        startActivity(intent);
                        break;
                    case 10:
                        intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case 11:
                        intent = new Intent(MainActivity.this, SwipeInActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        intent = new Intent(MainActivity.this, AccountActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
