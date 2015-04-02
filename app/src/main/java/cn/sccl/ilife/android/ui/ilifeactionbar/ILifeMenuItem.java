package cn.sccl.ilife.android.ui.ilifeactionbar;

/**
 * 慧生活Actionbar下拉菜单
 * Created by yishiyao on 2015/3/25.
 */
public class ILifeMenuItem {
    public static final int DISPLAY_GROUP_ITEM = 0x01;
    public static final int DISPLAY_NON_GROUP_ITEM = 0x02;

    private int id;
    private String title;
    private int menuDrawable;
    private int displayOption;
    private int displayDirection;

    public ILifeMenuItem() {}
    public ILifeMenuItem(int id, String title, int menuDrawable, int displayDirection) {
        this.title = title;
        this.menuDrawable = menuDrawable;
        this.id = id;
        this.displayOption = displayOption;
        this.displayDirection = displayDirection;
    }
    public int getMenuDrawable() {
        return menuDrawable;
    }

    public void setMenuDrawable(int menuDrawable) {
        this.menuDrawable = menuDrawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDisplayOption() {
        return displayOption;
    }

    public void setDisplayOption(int displayOption) {
        this.displayOption = displayOption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDisplayDirection() {
        return displayDirection;
    }

    public void setDisplayDirection(int displayDirection) {
        this.displayDirection = displayDirection;
    }
}
