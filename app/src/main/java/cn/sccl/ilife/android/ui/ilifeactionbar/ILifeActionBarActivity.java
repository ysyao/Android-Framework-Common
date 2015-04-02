package cn.sccl.ilife.android.ui.ilifeactionbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cn.sccl.ilife.android.R;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;

/**
 * 一个带有动作栏的activity
 * Created by yishiyao on 2015/3/24.
 */
public abstract class ILifeActionBarActivity extends RoboActionBarActivity implements
        ILifeActionBar.OnMenuItemSelectedListener{
    private ILifeActionBar actionBar;
    private View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        actionBar = getILifeActionBar();
        addActionBar2RootView();
        addMenuItems2ActionBar();
    }

    @Override
    public void setContentView(int layoutResID) {
        View root = getRootView();
        RelativeLayout contentLayout = (RelativeLayout) root.findViewById(R.id.layout_contents);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        inflater.inflate(layoutResID, contentLayout);
        super.setContentView(root);
    }

    @Override
    public void setContentView(View view) {
        View root = getRootView();
        getContentLayout(root).addView(view);
        super.setContentView(root);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        View root = getRootView();
        getContentLayout(root).addView(view);
        super.setContentView(root, params);
    }

    protected View getRootView() {
        if(root == null) {
            root = LayoutInflater.from(this).inflate(R.layout.activity_ilife_action_bar, null);
//            root = findViewById(R.id.root);
        }
        return root;
    }
    /**
     * 获得activity非actionbar的container
     * @param root
     * @return
     */
    protected RelativeLayout getContentLayout(View root) {
        return (RelativeLayout) root.findViewById(R.id.layout_contents);
    }

    protected ILifeActionBar getILifeActionBar() {
        if(actionBar == null) {
            actionBar = new ILifeActionBar(this);
            actionBar.setOnMenuItemSelectedListener(this);
        }
        return actionBar;
    }

    /**
     * 当创建actionbar的时候，如果要自定义menu，则在这里完成
     * @param menu
     */
    protected abstract void onILifeOptionMenuCreate(ILifeMenu menu);

    /**
     * 当menuItem被点击之后，会触发这个方法，通过item的id可以区分是哪个被点击了
     * @param item
     */
    protected abstract void onILifeMenuItemSelected(ILifeMenuItem item);
    /**
     * 将actionbar加入到root当中
     * @return rootView
     */
    private ILifeActionBar addActionBar2RootView() {
        //将actionbar的view加入到root当中
        View root = getRootView();
        RelativeLayout iLifeActionBarLayout = (RelativeLayout) root.findViewById(R.id.ilife_actionbar);
        iLifeActionBarLayout.addView(actionBar.getILifeActionBarRootView());

        return actionBar;
    }

    private void addMenuItems2ActionBar() {
        //新增menu
        ILifeMenu menu = getILifeActionBar().getMenu();
        menu.getMenuItems().clear();
        onILifeOptionMenuCreate(menu);
        getILifeActionBar().createOptionMenu();
    }

    @Override
    public void onILifeMenuItemSelected(ILifeMenuItem item, View view) {
        onILifeMenuItemSelected(item);
    }
}
