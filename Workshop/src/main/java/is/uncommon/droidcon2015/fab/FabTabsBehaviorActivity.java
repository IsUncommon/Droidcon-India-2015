package is.uncommon.droidcon2015.fab;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;

/**
 * Created by Madhu on 04/12/15.
 */
public class FabTabsBehaviorActivity extends AppCompatActivity {
    private static final int[] COLORS = new int[] {
            Color.parseColor("#009688"),
            Color.parseColor("#E91E63"),
            Color.parseColor("#FF9800")
    };
    private static final String TAG = FabTabsBehaviorActivity.class.getSimpleName();


    @Bind(R.id.tabs) TabLayout mTabLayout;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.viewpager) ViewPager mViewPager;
    @Bind(R.id.fab) FloatingActionButton mFab;
    @BindDrawable(R.drawable.ic_create_white_24dp) Drawable mCreateDrawable;
    @BindDrawable(R.drawable.ic_add_shopping_cart_white_24dp) Drawable mCartDrawable;
    @BindDrawable(R.drawable.ic_replay_white_24dp) Drawable mReplayDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_tabs_behavior);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("FAB Tab Behavior");
        mViewPager.setAdapter(new FabTabsBehaviorPagerAdapter(getFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateFabForPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
        updateFabForPosition(0);
    }

    private void updateFabForPosition(final int position) {
        //scale down
        ValueAnimator scaleDownAnimator = ValueAnimator.ofFloat(1, 0);
        scaleDownAnimator.setInterpolator(new AccelerateInterpolator());
        scaleDownAnimator.setDuration(150);
        scaleDownAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float currentScale = (float) valueAnimator.getAnimatedValue();
                mFab.setScaleX(currentScale);
                mFab.setScaleY(currentScale);
            }
        });
        //scale up
        ValueAnimator scaleUpAnimator = ValueAnimator.ofFloat(0, 1);
        scaleUpAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleUpAnimator.setDuration(150);
        scaleUpAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float currentScale = (float) valueAnimator.getAnimatedValue();
                mFab.setScaleX(currentScale);
                mFab.setScaleY(currentScale);
            }
        });
        scaleUpAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                mFab.setBackgroundTintList(ColorStateList.valueOf(COLORS[position % 3]));
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        scaleUpAnimator.setStartDelay(150);
        scaleUpAnimator.setDuration(150);
        scaleDownAnimator.start();
        scaleUpAnimator.start();
        float from = 0;
        float to = 0;
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFab.getLayoutParams();
        float translationDistance = mViewPager.getWidth() / 2 - mFab.getWidth() / 2 - params.leftMargin - params.rightMargin;
        if (position % 2 != 0) {
            from = 0;
            to = translationDistance;
        } else {
            from = translationDistance;
            to = 0;
        }
        //translate to center
        ValueAnimator translationAnimator = ValueAnimator.ofFloat(from, to);
        translationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        translationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float distance = (float) valueAnimator.getAnimatedValue();
                mFab.setTranslationX(-distance);
            }
        });
        translationAnimator.setDuration(150);
        translationAnimator.start();
        mFab.setScaleType(ImageView.ScaleType.MATRIX);
        final Matrix matrix = new Matrix();
        ValueAnimator drawableAnim = null;
        if (position % 3 == 0) {
            //scale create button
            matrix.setScale(0, 0, mCreateDrawable.getIntrinsicWidth() / 2, mCreateDrawable.getIntrinsicHeight() / 2);
            mFab.setImageDrawable(mCreateDrawable);
            mFab.setImageMatrix(matrix);
            drawableAnim = ValueAnimator.ofFloat(0, 1);
            drawableAnim.setInterpolator(new AccelerateInterpolator());
            drawableAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float scale = (float) valueAnimator.getAnimatedValue();
                    matrix.setScale(scale, scale, mCreateDrawable.getIntrinsicWidth() / 2, mCreateDrawable.getIntrinsicHeight() / 2);
                    mFab.setImageMatrix(matrix);
                }
            });
        } else if (position % 3 == 1) {
            //bring in the cart
            matrix.setTranslate(mFab.getWidth(), 0);
            mFab.setImageDrawable(mCartDrawable);
            mFab.setImageMatrix(matrix);
            drawableAnim = ValueAnimator.ofFloat(1, 0);
            drawableAnim.setInterpolator(new AccelerateDecelerateInterpolator());
            drawableAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float trans = (float) valueAnimator.getAnimatedValue();
                    matrix.setTranslate(mFab.getWidth() * trans, 0);
                    mFab.setImageMatrix(matrix);
                }
            });

        } else if (position % 3 == 2) {
            matrix.setScale(0, 0, mReplayDrawable.getIntrinsicWidth() / 2, mReplayDrawable.getIntrinsicHeight() / 2);
            mFab.setImageDrawable(mReplayDrawable);
            mFab.setImageMatrix(matrix);
            drawableAnim = ValueAnimator.ofFloat(0, 1);
            drawableAnim.setInterpolator(new AccelerateInterpolator());
            drawableAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float scale = (float) valueAnimator.getAnimatedValue();
                    matrix.setRotate((1 - scale) * (180), mCreateDrawable.getIntrinsicWidth() / 2, mCreateDrawable.getIntrinsicHeight() / 2);
                    mFab.setImageMatrix(matrix);
                }
            });
        }
        if (drawableAnim != null) {
            drawableAnim.setStartDelay(200);
            drawableAnim.setDuration(150);
            drawableAnim.start();
        }
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
