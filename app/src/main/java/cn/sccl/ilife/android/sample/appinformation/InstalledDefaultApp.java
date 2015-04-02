package cn.sccl.ilife.android.sample.appinformation;

import android.graphics.drawable.Drawable;

/**
 * 已安装app
 */
public class InstalledDefaultApp {
    public static final int INSTALLED_APP = 0x01;
    public static final int NOT_INSTALLED_APP = 0x02;
    private String packageName;
    private Drawable icon;
    private String lable;
    private int state = NOT_INSTALLED_APP;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
