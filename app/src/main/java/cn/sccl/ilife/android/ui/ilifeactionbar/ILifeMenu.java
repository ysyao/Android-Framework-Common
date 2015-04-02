package cn.sccl.ilife.android.ui.ilifeactionbar;

import java.util.ArrayList;
import java.util.List;

/**
 * 慧生活动作栏menu
 * Created by yishiyao on 2015/3/25.
 */
public class ILifeMenu {
    private List<ILifeMenuItem> items;

    public ILifeMenu() {
        items = new ArrayList<ILifeMenuItem>();
    }

    public void add(int id, String title, int menuDrawable, int displayDirection) {
        if(items != null && items.size() > 0) {
            for(ILifeMenuItem item : items) {
                if(id == item.getId()) {
                    return;
                }
            }
        }

        ILifeMenuItem item = new ILifeMenuItem(id, title, menuDrawable, displayDirection);
        items.add(item);
    }

    public List<ILifeMenuItem> getMenuItems() {
        return items;
    }
}
