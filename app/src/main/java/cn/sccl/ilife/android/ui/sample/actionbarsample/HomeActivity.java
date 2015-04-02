package cn.sccl.ilife.android.ui.sample.actionbarsample;

import android.os.Bundle;
import android.widget.Toast;

import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.ui.ilifeactionbar.ILifeMenu;
import cn.sccl.ilife.android.ui.ilifeactionbar.ILifeMenuItem;
import cn.sccl.ilife.android.ui.ilifeactionbar.ILifeActionBarActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_home)
public class HomeActivity extends ILifeActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getILifeActionBar().setTitle("慧生活");
        getILifeActionBar().setHomeAsUp(true);
    }

    @Override
    protected void onILifeOptionMenuCreate(ILifeMenu menu) {
        menu.add(0, "设置", R.drawable.setting_btn_selector, ILifeMenuItem.DISPLAY_NON_GROUP_ITEM);
        menu.add(1, "购买应用", R.drawable.shoppingcart, ILifeMenuItem.DISPLAY_GROUP_ITEM);
        menu.add(2, "我的订单", R.drawable.order, ILifeMenuItem.DISPLAY_GROUP_ITEM);
        menu.add(3, "系统通知", R.drawable.notification, ILifeMenuItem.DISPLAY_GROUP_ITEM);
    }

    @Override
    protected void onILifeMenuItemSelected(ILifeMenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
