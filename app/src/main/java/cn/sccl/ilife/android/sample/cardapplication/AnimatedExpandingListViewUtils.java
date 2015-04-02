package cn.sccl.ilife.android.sample.cardapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ListView;

public class AnimatedExpandingListViewUtils {

    /**
     * 通过postion获取listview的对应item的view
     * @param pos
     * @param listView
     * @return
     */
    public static View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    /**
     * 将drawable转换成为bitmap
     * @param context
     * @param drawableId
     * @return
     */
    public static Bitmap getBitmapById(Context context, int drawableId) {
        return BitmapFactory.decodeResource(context.getResources(), drawableId);
    }

    public static Drawable parseBitmap2Drawable(Context context, Bitmap bitmap) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public static Drawable getDrawable(Context context, int drawableId){
        return context.getResources().getDrawable(drawableId);
    }
}
