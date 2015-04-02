package cn.sccl.ilife.android.sample.cardapplication;

import static cn.sccl.ilife.android.sample.cardapplication.ExpandingAnimationController.CARD_BOTTOM_MARGIN;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import java.math.BigDecimal;

/**
 * 展开动画
 * Created by yishiyao on 2015/4/1.
 */
public class ExpandingAnimation extends Animation {
    private View v;
    private int targetHeight;
    public ExpandingAnimation(View v, int targetHeight) {
        super();
        this.v = v;
        this.targetHeight = targetHeight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        if(interpolatedTime == 1) {
            ViewGroup.LayoutParams params = v.getLayoutParams();
            params.height = LinearLayout.LayoutParams.MATCH_PARENT;
            ((LinearLayout.LayoutParams) params).setMargins(0, 0, 0, CARD_BOTTOM_MARGIN);
            v.setLayoutParams(params);
        } else {
            if(interpolatedTime > expandViewTime()) {
                ViewGroup.LayoutParams params = v.getLayoutParams();
                ((LinearLayout.LayoutParams) params).setMargins(0, 0, 0, (int)(interpolatedTime * getExpandHeightSpeed() - targetHeight));
                v.setLayoutParams(params);
            } else {
                v.getLayoutParams().height = (int)(interpolatedTime * getExpandHeightSpeed());
            }
        }
        v.requestLayout();
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

    private int getExpandHeightSpeed() {
        return (targetHeight + CARD_BOTTOM_MARGIN);
    }

    private float expandViewTime() {
        double value = (double)targetHeight / ((double)targetHeight + (double)CARD_BOTTOM_MARGIN);
        BigDecimal b = new BigDecimal(value);
        return b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
