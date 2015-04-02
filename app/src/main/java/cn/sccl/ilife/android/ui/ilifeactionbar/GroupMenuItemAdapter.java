package cn.sccl.ilife.android.ui.ilifeactionbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.ui.BaseListAdapter;

/**
 * 动作栏菜单列表adapter
 * Created by yishiyao on 2015/3/26.
 */
public class GroupMenuItemAdapter extends BaseListAdapter<ILifeMenuItem> {

    class ViewHolder {
        TextView tv;
    }

    public GroupMenuItemAdapter(Context context, List<ILifeMenuItem> items) {
        super(context, items);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            holder = new ViewHolder();
            view = getInflater().inflate(R.layout.item_group_menu_item, viewGroup, false);
            holder.tv = (TextView) view.findViewById(R.id.menu_item);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ILifeMenuItem item = getItem(i);
        holder.tv.setText(item.getTitle());
        Drawable drawable = getApplicationContext().getResources().getDrawable(item.getMenuDrawable());
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        holder.tv.setCompoundDrawables(drawable, null, null, null);
        return view;
    }


}
