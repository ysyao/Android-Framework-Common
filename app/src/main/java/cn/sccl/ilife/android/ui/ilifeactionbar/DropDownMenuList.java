package cn.sccl.ilife.android.ui.ilifeactionbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.List;

import cn.sccl.ilife.android.R;

/**
 * 动作栏溢出菜单
 * Created by yishiyao on 2015/3/27.
 */
public class DropDownMenuList {
    private View anchor;
    private List<ILifeMenuItem> items;
    private PopupWindow popupWindow;
    private LayoutInflater inflater;
    private Activity context;
    private boolean setHomeAsUp;
    private AdapterView.OnItemClickListener onItemClickListener;

    public DropDownMenuList(Activity context, boolean setHomeAsUp, View anchor, List<ILifeMenuItem> items, AdapterView.OnItemClickListener listener) {
        this.anchor = anchor;
        this.items = items;
        this.setHomeAsUp = setHomeAsUp;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.onItemClickListener = listener;
        initPopupWindow();
    }

    public void show() {
        popupWindow.showAsDropDown(anchor);
    }

    public void dismiss() {
        if(popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    protected void initPopupWindow() {
        View content = inflater.inflate(R.layout.popup_window_action_bar_menu_list, null);
        initContent(content);
        popupWindow = new PopupWindow(content, (int) generateMenuWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    protected void initContent(View content) {
        ListView listView = (ListView) content.findViewById(R.id.menu_item_listview);
        if(setHomeAsUp) {
            listView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.menu_item_right_bg));
        } else {
            listView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.menu_item_left_bg));
        }
        listView.setAdapter(new GroupMenuItemAdapter(context, items));
        listView.setOnItemClickListener(onItemClickListener);
    }

    private double generateMenuWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels * 0.4;
    }

}
