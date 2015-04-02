package cn.sccl.ilife.android.sample.cardapplication;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import java.math.BigDecimal;

import cn.sccl.ilife.android.ui.sample.cardapplication.PropertyAnimatorAdapter;

import static android.widget.LinearLayout.LayoutParams.*;

/**
 * 动画控制类
 * Created by yishiyao on 2015/3/23.
 */
public class ExpandingAnimationController {
    public static final int CARD_BOTTOM_MARGIN = 32;
    private PropertyAnimatorAdapter adapter;
    private Animation.AnimationListener expandingAnimationListener;

    public ExpandingAnimationController(PropertyAnimatorAdapter adapter) {
        this.adapter = adapter;
    }

    public void setExpandingAnimationListener(Animation.AnimationListener expandingAnimationListener) {
        this.expandingAnimationListener = expandingAnimationListener;
    }

    public void toggleAnimation(View v, int position) {
        ExpandableItem item = adapter.getItem(position);
        if(item.isExpanding()) {
            collapse(v, item);
        } else {
            expand(v, item);
        }
    }

    /**
     * 展开item
     * @param v
     * @param item
     */
    public void expand(View v, ExpandableItem item) {
        v.measure(MATCH_PARENT, MATCH_PARENT);
        int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) v.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        v.setVisibility(View.VISIBLE);
        item.setExpanding(true);

        // 0.1dp/ms
        ExpandingAnimation expandingAnimation = new ExpandingAnimation(v, targetHeight);
        expandingAnimation.setInterpolator(v.getContext(), android.R.anim.decelerate_interpolator);
        expandingAnimation.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        if(expandingAnimationListener != null) {
            expandingAnimation.setAnimationListener(expandingAnimationListener);
        }
        v.startAnimation(expandingAnimation);
    }

    /**
     * 收缩item
     * @param v
     * @param item
     */
    public void collapse(View v, ExpandableItem item) {
        int initialHeight = v.getMeasuredHeight();
        item.setExpanding(false);

        CollapsingAnimation collapseAnimation = new CollapsingAnimation(v, initialHeight);
        // 1dp/ms
        collapseAnimation.setInterpolator(v.getContext(), android.R.anim.accelerate_interpolator);
        collapseAnimation.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        if(expandingAnimationListener != null) {
            collapseAnimation.setAnimationListener(expandingAnimationListener);
        }
        v.startAnimation(collapseAnimation);
    }

}
