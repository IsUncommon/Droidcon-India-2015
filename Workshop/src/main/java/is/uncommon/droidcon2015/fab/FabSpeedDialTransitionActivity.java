package is.uncommon.droidcon2015.fab;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;

/**
 * Created by Madhu on 08/12/15.
 */
public class FabSpeedDialTransitionActivity extends AppCompatActivity {
    private static final int[] COLORS = new int[] {
            Color.parseColor("#9C27B0"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#E91E63")
    };

    private static final int[] ICONS = new int[] {
            R.drawable.ic_create_white_24dp,
            R.drawable.ic_replay_white_24dp,
            R.drawable.ic_add_shopping_cart_white_24dp,
            R.drawable.ic_done_white_24dp
    };

    private static final String[] DESCRIPTIONS = new String[] {
            "You just clicked create",
            "You just clicked replay",
            "You just clicked shopping cart",
            "You just clicked done"
    };

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.fab) FloatingActionButton mFab;
    @Bind(R.id.fab_1) FloatingActionButton mFab1;
    @Bind(R.id.fab_2) FloatingActionButton mFab2;
    @Bind(R.id.fab_3) FloatingActionButton mFab3;
    @Bind(R.id.fab_4) FloatingActionButton mFab4;
    @Bind(R.id.rootview) CoordinatorLayout mRootView;

    private FloatingActionButton[] mFabs = new FloatingActionButton[4];
    private boolean mHasFabExpanded = false;
    private Runnable mPostAnimationRunnable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_speeddial_transition_demo);
        ButterKnife.bind(this);
        mFabs[0] = mFab1;
        mFabs[1] = mFab2;
        mFabs[2] = mFab3;
        mFabs[3] = mFab4;
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("FAB Speed Dial Transition");
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFab();
            }
        });
        for (int i = 0; i < mFabs.length; i++) {
            mFabs[i].setBackgroundTintList(ColorStateList.valueOf(COLORS[i % COLORS.length]));
            mFabs[i].setScaleX(0);
            mFabs[i].setScaleY(0);
            mFabs[i].setImageDrawable(ContextCompat.getDrawable(this, ICONS[i % ICONS.length]));
            final int finalI = i;
            mFabs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleFab();
                    mPostAnimationRunnable = new Runnable() {
                        @Override
                        public void run() {
                            mFab.setClickable(false);
                            Snackbar snackbar = Snackbar.make(mRootView, DESCRIPTIONS[finalI], Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            snackbar.setCallback(new Snackbar.Callback() {
                                @Override
                                public void onDismissed(Snackbar snackbar, int event) {
                                    mFab.setClickable(true);
                                }
                            });
                        }
                    };
                }
            });
        }
    }

    private void toggleFab() {
        mFab.setScaleType(ImageView.ScaleType.MATRIX);
        final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFab.getLayoutParams();
        final Matrix imageMatrix = new Matrix();
        final float height = mFab.getDrawable().getIntrinsicHeight();
        final float width = mFab.getDrawable().getIntrinsicWidth();
        ValueAnimator rotateFabPlus = ValueAnimator.ofFloat(mHasFabExpanded ? 1 : 0, mHasFabExpanded ? 0 : 1);
        rotateFabPlus.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = (float) valueAnimator.getAnimatedValue();
                imageMatrix.setRotate(-fraction * 135, width / 2, height / 2);
                mFab.setImageMatrix(imageMatrix);
                for (int i = 0; i < mFabs.length; i++) {
                    mFabs[i].setAlpha(fraction);
                    mFabs[i].setTranslationY(-(i + 1) * fraction * (mFab.getHeight() + params.bottomMargin));
                }
            }
        });
        rotateFabPlus.setInterpolator(mHasFabExpanded ? new DecelerateInterpolator() : new AccelerateDecelerateInterpolator());
        rotateFabPlus.setDuration(300);
        rotateFabPlus.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (!mHasFabExpanded) {
                    for (int i = 0; i < mFabs.length; i++) {
                        mFabs[i].setScaleX(1);
                        mFabs[i].setScaleY(1);
                    }
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mHasFabExpanded = !mHasFabExpanded;
                if (!mHasFabExpanded) {
                    for (int i = 0; i < mFabs.length; i++) {
                        mFabs[i].setTranslationY(0);
                        mFabs[i].setScaleX(0);
                        mFabs[i].setScaleY(0);
                    }
                    if (mPostAnimationRunnable != null) {
                        mPostAnimationRunnable.run();
                        mPostAnimationRunnable = null;
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                mHasFabExpanded = !mHasFabExpanded;
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        rotateFabPlus.start();
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
