package cn.sccl.ilife.android.sample.cardapplication;

import static cn.sccl.ilife.android.sample.cardapplication.ExpandingAnimationController.CARD_BOTTOM_MARGIN;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import java.math.BigDecimal;

/**
 * 收缩动画
 * Created by yishiyao on 2015/4/1.
 */
public class CollapsingAnimation extends Animation{
    private View v;
    private int initialHeight;
    public CollapsingAnimation(View v, int initialHeight) {
        super();
        this.v = v;
        this.initialHeight = initialHeight;
    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t){
        if(interpolatedTime == 1){
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) v.getLayoutParams();
            params.height = initialHeight;
            params.setMargins(0 ,0, 0, CARD_BOTTOM_MARGIN);
            v.setVisibility(View.GONE);
        }else{
            if(interpolatedTime > collapsePaddingTime()) {
                v.getLayoutParams().height = getAnimatingHeight(interpolatedTime);
            } else {
                ViewGroup.LayoutParams params = v.getLayoutParams();
                ((LinearLayout.LayoutParams) params).setMargins(0, 0, 0, getAnimatingPadding(interpolatedTime));
                v.setLayoutParams(params);
            }
        }
        v.requestLayout();
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

    /**
     * 获取收起时候设置padding的时间是多长
     * @return
     */
    private float collapsePaddingTime() {
        double value = (double)CARD_BOTTOM_MARGIN / ((double)initialHeight + (double)CARD_BOTTOM_MARGIN);
        BigDecimal b = new BigDecimal(value);
        float f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }

    private int getCollapseSpeed() {
        return (initialHeight + CARD_BOTTOM_MARGIN);
    }

    private int getAnimatingHeight(float interpolatedTime) {
        return (initialHeight - (int)(getCollapseSpeed() * interpolatedTime - CARD_BOTTOM_MARGIN));
    }

    private int getAnimatingPadding(float interpolatedTime) {
        return (CARD_BOTTOM_MARGIN - (int)(getCollapseSpeed() * interpolatedTime));
    }
}
