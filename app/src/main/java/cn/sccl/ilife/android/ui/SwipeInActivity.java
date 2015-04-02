package cn.sccl.ilife.android.ui;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import cn.sccl.ilife.android.R;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Administrator on 2015/3/26.
 */
@ContentView(R.layout.activity_swipein)
public class SwipeInActivity extends ILifeActivity {
    @InjectView(R.id.image)
    private ImageView image;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        animationDrawable = (AnimationDrawable) image.getDrawable();
        animationDrawable.start();

    }
}
