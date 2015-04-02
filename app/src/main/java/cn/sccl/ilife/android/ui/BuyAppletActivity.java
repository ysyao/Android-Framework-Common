package cn.sccl.ilife.android.ui;

import android.os.Bundle;
import android.widget.GridView;

import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.ui.ILifeActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Administrator on 2015/3/23.
 */
@ContentView(R.layout.activity_buyapplet)
public class BuyAppletActivity  extends ILifeActivity {
    @InjectView(R.id.girdview)
    private GridView mGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
