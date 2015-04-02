package cn.sccl.ilife.android.ui.sample.cardapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.sample.cardapplication.AnimatedExpandingListViewUtils;
import cn.sccl.ilife.android.sample.cardapplication.ExpandableItem;
import cn.sccl.ilife.android.ui.BaseListAdapter;

public class PropertyAnimatorAdapter extends BaseListAdapter<ExpandableItem> {
    private ViewHolder holder;
    private int[] bgTop = {
            R.drawable.colortop_01, R.drawable.colortop_02, R.drawable.colortop_03, R.drawable.colortop_04,
            R.drawable.colortop_05, R.drawable.colortop_06
    };
    private int[] bgBottom = {
            R.drawable.colorbottom_01, R.drawable.colorbottom_02,R.drawable.colorbottom_03, R.drawable.colorbottom_04,
            R.drawable.colorbottom_05, R.drawable.colorbottom_06
    };

    public PropertyAnimatorAdapter(Context context, List<ExpandableItem> items) {
        super(context, items);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ExpandableItem item = getItem(i);
        if(view == null) {
            holder = new ViewHolder();
            view = getInflater().inflate(R.layout.item_expandable, viewGroup, false);

            holder.above = view.findViewById(R.id.above);
            holder.below = view.findViewById(R.id.below);
            holder.confirm = (TextView)view.findViewById(R.id.confirm);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        int j = i % 6;
        holder.above.setBackgroundDrawable(AnimatedExpandingListViewUtils.getDrawable(getApplicationContext(), bgTop[j]));
        holder.below.setBackgroundDrawable(AnimatedExpandingListViewUtils.getDrawable(getApplicationContext(), bgBottom[j]));
        holder.confirm.setText(String.valueOf(i));
        if(item.isExpanding()) {
             holder.below.setVisibility(View.VISIBLE);
        } else {
            holder.below.setVisibility(View.GONE);
        }
        return view;
    }

    class ViewHolder {
        View above;
        View below;
        TextView confirm;
    }
}
