package is.uncommon.droidcon2015.steps;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Madhu on 29/11/15.
 */
abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {

    public HeaderScrollingViewBehavior() {}

    public HeaderScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, View child,
            int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec,
            int heightUsed) {
        final int childLpHeight = child.getLayoutParams().height;
        if (childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT
                || childLpHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {
            // If the menu's height is set to match_parent/wrap_content then measure it
            // with the maximum visible height

            final List<View> dependencies = parent.getDependencies(child);
            if (dependencies.isEmpty()) {
                // If we don't have any dependencies, return false
                return false;
            }

            final View header = findFirstDependency(dependencies);
            if (header != null && ViewCompat.isLaidOut(header)) {
                if (ViewCompat.getFitsSystemWindows(header)) {
                    // If the header is fitting system windows then we need to also,
                    // otherwise we'll get CoL's compatible layout functionality
                    ViewCompat.setFitsSystemWindows(child, true);
                }

                int availableHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec);
                if (availableHeight == 0) {
                    // If the measure spec doesn't specify a size, use the current height
                    availableHeight = parent.getHeight();
                }

                final int height = availableHeight - header.getMeasuredHeight()
                        + getScrollRange(header);
                final int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height,
                        childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT
                                ? View.MeasureSpec.EXACTLY
                                : View.MeasureSpec.AT_MOST);

                // Now measure the scrolling menu with the correct height
                parent.onMeasureChild(child, parentWidthMeasureSpec,
                        widthUsed, heightMeasureSpec, heightUsed);

                return true;
            }
        }
        return false;
    }

    abstract View findFirstDependency(List<View> views);

    int getScrollRange(View v) {
        return v.getMeasuredHeight();
    }
}