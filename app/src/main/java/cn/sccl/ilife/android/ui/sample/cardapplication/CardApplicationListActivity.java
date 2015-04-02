package cn.sccl.ilife.android.ui.sample.cardapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.sample.cardapplication.ExpandableItem;
import cn.sccl.ilife.android.sample.cardapplication.ExpandingAnimationController;
import cn.sccl.ilife.android.sample.cardapplication.ExpandingAnimationListener;
import cn.sccl.ilife.android.ui.ilifeactionbar.ILifeActionBarActivity;
import cn.sccl.ilife.android.ui.ilifeactionbar.ILifeMenu;
import cn.sccl.ilife.android.ui.ilifeactionbar.ILifeMenuItem;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_list_view_animation)
public class CardApplicationListActivity extends ILifeActionBarActivity implements AdapterView.OnItemClickListener{
    @InjectView(R.id.animation_listview)
    private ListView mLV;

    private List<ExpandableItem> items = new ArrayList<ExpandableItem>();
    private ExpandingAnimationController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getILifeActionBar().setTitle("慧生活");
        getILifeActionBar().setHomeAsUp(false);
        initList();
        final PropertyAnimatorAdapter adapter = new PropertyAnimatorAdapter(this, items);
        mLV.setAdapter(adapter);
        controller = new ExpandingAnimationController(adapter);
        mLV.setOnItemClickListener(this);
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

    private void initList() {
        for(int i=0;i<14;i++) {
            items.add(new ExpandableItem());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view,final int i, long l) {
        View below = view.findViewById(R.id.below);
        final  PropertyAnimatorAdapter propertyAnimatorAdapter = (PropertyAnimatorAdapter)adapterView.getAdapter();
        controller.setExpandingAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLV.smoothScrollToPosition(i);
                        mLV.setSelection(i);
                        propertyAnimatorAdapter.notifyDataSetChanged();
                    }
                }, 150);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        controller.toggleAnimation(below, i);
    }
}
