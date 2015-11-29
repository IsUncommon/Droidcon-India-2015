package is.uncommon.droidcon2015.steps;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Madhu on 30/11/15.
 */
public class StepsDemoFABBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public StepsDemoFABBehavior() {
    }

    public StepsDemoFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, final View dependency) {
        if (dependency instanceof TopCardView) {
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
                    animator.setDuration(300);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int currLeft = dependency.getLeft();
                            float fraction = animator.getAnimatedFraction();
                            int distance = Math.round(fraction * (float) (currLeft));
                            ViewCompat.offsetLeftAndRight(dependency, -distance);
                            ViewCompat.setAlpha(dependency, fraction);

                        }
                    });
                    animator.start();
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        float xFraction = (float) (dependency.getLeft()) / (float) (dependency.getWidth());
        int yOffset = Math.round(xFraction * dependency.getHeight());
        ViewCompat.offsetTopAndBottom(child, yOffset);
        return true;
    }
}
