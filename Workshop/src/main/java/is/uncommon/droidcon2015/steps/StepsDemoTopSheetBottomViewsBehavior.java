package is.uncommon.droidcon2015.steps;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by Madhu on 29/11/15.
 */
public class StepsDemoTopSheetBottomViewsBehavior extends HeaderScrollingViewBehavior {

    private static final String TAG = StepsDemoTopSheetBottomViewsBehavior.class.getSimpleName();
    private SwipeDismissBehavior<TopCardView> swipeDismissBehavior = new SwipeDismissBehavior<>();

    public StepsDemoTopSheetBottomViewsBehavior() {
    }

    public StepsDemoTopSheetBottomViewsBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    View findFirstDependency(List<View> views) {
        for (int i = 0; i < views.size(); i++) {
            View view = views.get(i);
            if (view instanceof TopCardView) {
                return view;
            }
        }
        return null;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if(dependency instanceof TopCardView) {
            CoordinatorLayout.LayoutParams params  = (CoordinatorLayout.LayoutParams) dependency.getLayoutParams();
            params.setBehavior(swipeDismissBehavior);
            swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
            swipeDismissBehavior.setStartAlphaSwipeDistance(0.1f);
            swipeDismissBehavior.setEndAlphaSwipeDistance(0.9f);
            swipeDismissBehavior.setDragDismissDistance(0.8f);
            return true;
        }
        return false;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        // First lay out the child as normal
        super.onLayoutChild(parent, child, layoutDirection);

        // Now offset us correctly to be in the correct position. This is important for things
        // like activity transitions which rely on accurate positioning after the first layout.
        final List<View> dependencies = parent.getDependencies(child);
        for (int i = 0, z = dependencies.size(); i < z; i++) {
            if (updateOffset(parent, child, dependencies.get(i))) {
                // If we updated the offset, break out of the loop now
                break;
            }
        }
        return true;
    }

    private boolean updateOffset(CoordinatorLayout parent, View child, View dependency) {
        if (dependency instanceof TopCardView) {
            //check x offsets
            float xFraction = (float)(dependency.getWidth() - dependency.getLeft()) / (float) (dependency.getWidth());
            int yOffset = Math.round(xFraction * dependency.getHeight());
            setTopAndBottomOffset(yOffset);
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return updateOffset(parent, child, dependency);
    }
}
