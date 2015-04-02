package cn.sccl.ilife.android.ui.ilifeactionbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yishiyao on 15/3/26.
 */
public class ILifeActionBarUtils {
    public static List<ILifeMenuItem> itemFilter(List<ILifeMenuItem> items, int displayDirection) {
        List<ILifeMenuItem> leftItems = new ArrayList<>();
        for(ILifeMenuItem item : items) {
            if(item.getDisplayDirection() == displayDirection) {
                leftItems.add(item);
            }
        }
        return leftItems;
    }
}
