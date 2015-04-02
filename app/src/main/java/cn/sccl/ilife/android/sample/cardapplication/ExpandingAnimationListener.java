package cn.sccl.ilife.android.sample.cardapplication;

import android.view.animation.Animation;

/**
 * Created by yishiyao on 2015/3/29.
 */
public interface ExpandingAnimationListener extends Animation.AnimationListener {
    public void onExpanding(float interpolatedTime);
    public void onCollapseing(float interpolatedTime);
}
