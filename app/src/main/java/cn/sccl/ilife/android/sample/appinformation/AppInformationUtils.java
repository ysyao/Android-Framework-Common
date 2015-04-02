package cn.sccl.ilife.android.sample.appinformation;

import java.util.List;

public class AppInformationUtils {

    /**
     * 根据给定的packageName筛选所有已经安装的app
     * @param apps        所有已经安装app
     * @param packageName 筛选包名
     * @return            已经筛选过的app
     */
    public static InstalledDefaultApp selectAppByPackageName(List<InstalledDefaultApp> apps, String packageName) {
        for(InstalledDefaultApp app : apps) {
            if(app.getPackageName().equals(packageName)) {
                return app;
            }
        }
        return null;
    }

    /**
     * 设置app是否已经安装
     * @param app       被设置的app
     * @param installed 如果已经安装则为true,未安装false
     */
    public static void setIfAppInstalled(InstalledDefaultApp app, boolean installed) {
        if(installed) {
            app.setState(InstalledDefaultApp.INSTALLED_APP);
        } else {
            app.setState(InstalledDefaultApp.NOT_INSTALLED_APP);
        }

    }
}
