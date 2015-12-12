package is.uncommon.droidcon2015.coord;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.utils.HtmlUtils;

/**
 * Created by Madhu on 12/12/15.
 */
public class CLSwipeDismissBehaviorActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.text) TextView mText;
    @Bind(R.id.swipe_behavior_toggle) Button mSwipeBehaviorToggle;
    @Bind(R.id.top_sheet) CardView mTopSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cl_swipe_dismiss);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Swipe dismiss demo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        HtmlUtils.setHtmlText(mText, getString(R.string.cl_swipe_dismiss_behavior));
        mSwipeBehaviorToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mTopSheet.getLayoutParams();
                CoordinatorLayout.Behavior behavior = params.getBehavior();
                if (behavior != null) {
                    //remove the behavior
                    params.setBehavior(null);
                    mSwipeBehaviorToggle.setText("Add Swipe Behavior");
                } else {
                    mSwipeBehaviorToggle.setText("Remove Swipe Behavior");
                    //add the behavior
                    SwipeDismissBehavior<CardView> dismissBehavior = new SwipeDismissBehavior<CardView>();
                    params.setBehavior(dismissBehavior);
                    dismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
                    dismissBehavior.setDragDismissDistance(0.8f);
                    dismissBehavior.setStartAlphaSwipeDistance(0.3f);
                    dismissBehavior.setEndAlphaSwipeDistance(0.9f);
                    dismissBehavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
                        @Override
                        public void onDismiss(View view) {
                            Snackbar snackbar = Snackbar.make(mTopSheet, "Card dismissed", Snackbar.LENGTH_INDEFINITE);
                            snackbar.setAction("Bring back", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ViewCompat.offsetLeftAndRight(mTopSheet, mTopSheet.getWidth());
                                    ViewCompat.setAlpha(mTopSheet, 1f);
                                }
                            });
                            snackbar.show();
                        }

                        @Override
                        public void onDragStateChanged(int state) {

                        }
                    });
                }
            }
        });
        mSwipeBehaviorToggle.setText("Add Swipe Behavior");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
