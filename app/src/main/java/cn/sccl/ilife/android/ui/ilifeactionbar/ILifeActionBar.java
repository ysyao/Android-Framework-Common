package cn.sccl.ilife.android.ui.ilifeactionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.sccl.ilife.android.R;

/**
 * 慧生活工作栏
 * Created by yishiyao on 2015/3/24.
 */
public class ILifeActionBar implements AdapterView.OnItemClickListener{
    public interface OnMenuItemSelectedListener {
        void onILifeMenuItemSelected(ILifeMenuItem item, View view);
    }
    private View bar;
    private ILifeMenu menu;
    private Activity context;
    private OnMenuItemSelectedListener onMenuItemSelectedListener;
    private boolean isSetHomeAsUp = false;
    private DropDownMenuList dropDownMenuList;
    private LinearLayout leftLayout;
    private LinearLayout rightLayout;

    public ILifeActionBar(Activity context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        bar = inflater.inflate(R.layout.ilife_action_bar, null);
        this.context = context;
        menu = new ILifeMenu();
    }

    /**
     * 设置home的位置是否是回退符号，当HOME是回退的时候，组菜单自动放到最右边，否则默认放到左边，注意，如果组菜单
     * 当中只有一个菜单，则直接在动作栏上面显示这个菜单，不用用溢出列表。
     * @param isSetHomeAsUp TRUE为回退符号，FALSE为默认，是一个组菜单
     */
    public void setHomeAsUp(boolean isSetHomeAsUp) {
        this.isSetHomeAsUp = isSetHomeAsUp;
        List<ILifeMenuItem> groupItems = getGroupItems();
        leftLayout.removeAllViews();
        rightLayout.removeAllViews();
        if(isSetHomeAsUp) {
            //新建group menu
            createGroupMenu(rightLayout, groupItems);

            //创建回退符号
            TextView back = addShowAsActionMenu2Anchor(R.drawable.back_btn_selector);
            leftLayout.addView(back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.finish();
                }
            });
        } else {
            createGroupMenu(leftLayout, groupItems);
        }
    }

    public void setOnMenuItemSelectedListener(OnMenuItemSelectedListener listener) {
        this.onMenuItemSelectedListener = listener;
    }

    public View getILifeActionBarRootView() {
        return bar;
    }

    /**
     * 设置动作栏标题
     * @param titleStr
     */
    public void setTitle(String titleStr) {
        TextView title = (TextView)bar.findViewById(R.id.action_bar_title);
        title.setText(titleStr);
    }

    public ILifeMenu getMenu() {
        return menu;
    }

    /**
     * 新建动作栏上面的菜单，这里为默认的将动作栏靠左部分设置成为组菜单，将动作栏右边部分设置成为非组菜单。
     */
    public void createOptionMenu() {
        leftLayout = (LinearLayout)bar.findViewById(R.id.action_bar_left_container);
        rightLayout = (LinearLayout)bar.findViewById(R.id.action_bar_right_container);
        LinearLayout nonGroupLayout = (LinearLayout) bar.findViewById(R.id.action_bar_non_group_container);

        List<ILifeMenuItem> groupItems = getGroupItems();
        createGroupMenu(leftLayout, groupItems);

        List<ILifeMenuItem> rightGroupItems = ILifeActionBarUtils.itemFilter(menu.getMenuItems(), ILifeMenuItem.DISPLAY_NON_GROUP_ITEM);
        for(ILifeMenuItem item : rightGroupItems) {
            createNonGroupMenu(nonGroupLayout, item);
        }
    }

    /**
     * 获取已经设置好的所有菜单项
     * @return
     */
    protected List<ILifeMenuItem> getGroupItems() {
        return ILifeActionBarUtils.itemFilter(menu.getMenuItems(), ILifeMenuItem.DISPLAY_GROUP_ITEM);
    }

    /**
     * 创建组菜单
     * @param linearLayout
     * @return
     */
    private LinearLayout createGroupMenu(LinearLayout linearLayout, List<ILifeMenuItem> groupItems) {
        if(groupItems == null || groupItems.size() == 0) {
            return linearLayout;
        }

        if(groupItems.size() == 1) {
            createNonGroupMenu(linearLayout, groupItems.get(0));
            return linearLayout;
        }

        //设置anchor
        TextView groupMenu = addShowAsActionMenu2Anchor(R.drawable.more_menu_selector);
        linearLayout.addView(groupMenu);

        //通过上面创建的anchor来决定菜单列表出现的位置
        displayMenuList(linearLayout, groupItems);
        return linearLayout;
    }

    /**
     * 创建单个显示菜单
     * @param linearLayout
     * @param item
     * @return
     */
    private LinearLayout createNonGroupMenu(final LinearLayout linearLayout, final ILifeMenuItem item) {
        TextView nonGroupMenu = addShowAsActionMenu2Anchor(item.getMenuDrawable());
        linearLayout.addView(nonGroupMenu);
        //设置点击事件
        if(onMenuItemSelectedListener == null) {
            return null;
        }
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMenuItemSelectedListener.onILifeMenuItemSelected(item, view);
            }
        });

        return linearLayout;
    }

    /**
     * 通过图片ID创建一个在动作栏上面显示的菜单图标。
     * @param drawableId
     * @return
     */
    private TextView addShowAsActionMenu2Anchor(int drawableId) {
        TextView pic = new TextView(context);
        pic.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        pic.setPadding(8, 8, 8, 8);
        pic.setBackgroundDrawable(context.getResources().getDrawable(drawableId));
        return pic;
    }

    /**
     * 展示溢出菜单列表
     * @param anchor        溢出菜单列表会在anchor下面显示
     * @param groupItems    需要显示的菜单列表
     */
    private void displayMenuList(LinearLayout anchor, List<ILifeMenuItem> groupItems) {
        dropDownMenuList = new DropDownMenuList(context, isSetHomeAsUp, anchor, groupItems, this);
        anchor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropDownMenuList.show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        dropDownMenuList.dismiss();
        GroupMenuItemAdapter adapter = (GroupMenuItemAdapter) adapterView.getAdapter();
        ILifeMenuItem item = adapter.getItem(i);

        //设置点击事件
        if(onMenuItemSelectedListener == null) {
            return;
        }
        onMenuItemSelectedListener.onILifeMenuItemSelected(item, view);
    }

}
