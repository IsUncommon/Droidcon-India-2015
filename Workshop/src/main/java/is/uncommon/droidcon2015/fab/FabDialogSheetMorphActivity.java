package is.uncommon.droidcon2015.fab;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.utils.MathUtils;

/**
 * Created by Madhu on 08/12/15.
 */
public class FabDialogSheetMorphActivity extends AppCompatActivity {

    @Bind(R.id.fab) FloatingActionButton mFab;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.rootview) CoordinatorLayout mRootView;
    @Bind(R.id.compose_button) TextView mComposeButton;
    @Bind(R.id.dialog_sheet) CardView mDialogSheet;
    @Bind(R.id.scroll_view) NestedScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_dialog_sheet_morph_demo);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("FAB Dialog Sheet Morph");
        mDialogSheet.setVisibility(View.GONE);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSheet();
            }
        });
    }

    private void showSheet() {
        final float radius = Math.max(mDialogSheet.getWidth(), mDialogSheet.getHeight());

        ValueAnimator fabTranslator = ValueAnimator.ofFloat(0, 1);
        final float startTransX = 0;
        final float endTransX = -mDialogSheet.getWidth() / 2 + mFab.getWidth() / 2;
        final float startTransY = 0;
        final float endTransY = -mDialogSheet.getHeight() / 4 + mFab.getHeight() / 2;
        final float controlPointX = endTransX;
        final float controlPointY = 0;
        fabTranslator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float val = (float) valueAnimator.getAnimatedValue();
                float tranX = (float) MathUtils.quadBezierValue(startTransX, controlPointX, endTransX, val);
                float tranY = (float) MathUtils.quadBezierValue(startTransY, controlPointY, endTransY, val);
                mFab.setTranslationX(tranX);
                mFab.setTranslationY(tranY);
            }
        });
        fabTranslator.setDuration(150);

        ValueAnimator fabScaler = ValueAnimator.ofFloat(0, 1);
        fabScaler.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = (float) valueAnimator.getAnimatedValue();
                float scaleX = (mFab.getWidth() + (fraction) * (radius / 4 - mFab.getWidth())) / mFab.getWidth();
                float scaleY = (mFab.getHeight() + (fraction) * (radius / 4 - mFab.getHeight())) / mFab.getHeight();
                mFab.setScaleX(scaleX);
                mFab.setScaleY(scaleY);
            }
        });
        fabScaler.setDuration(150);
        AnimatorSet fabSet = new AnimatorSet();
        fabSet.play(fabTranslator).with(fabScaler);
        fabSet.setInterpolator(new AccelerateDecelerateInterpolator());
        fabSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mDialogSheet.setScaleX(0);
                mDialogSheet.setScaleY(0);
                mDialogSheet.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        int accentColor = getResources().getColor(R.color.colorAccent);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ValueAnimator colorTranslator = ValueAnimator.ofArgb(accentColor, Color.WHITE);
            colorTranslator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int color = (int) valueAnimator.getAnimatedValue();
                    mDialogSheet.setCardBackgroundColor(color);
                }
            });
            colorTranslator.setDuration(150);

            ValueAnimator translation = ValueAnimator.ofFloat(1, 0);
            translation.setDuration(150);
            translation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float fraction = (float) valueAnimator.getAnimatedValue();
                    float maxTrans = mDialogSheet.getHeight() / 4;
                    mDialogSheet.setTranslationY(fraction * maxTrans);
                }
            });
            translation.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    mDialogSheet.setScaleX(1);
                    mDialogSheet.setScaleY(1);
                    mDialogSheet.setTranslationY(mDialogSheet.getHeight() / 4);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mDialogSheet.setCardBackgroundColor(Color.WHITE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            Animator animator = ViewAnimationUtils.createCircularReveal(mDialogSheet, mDialogSheet.getWidth() / 2, mDialogSheet.getHeight() / 2, radius / 4, radius);
            animator.setDuration(150);
            AnimatorSet dialogSet = new AnimatorSet();
            dialogSet.play(animator).with(translation);

            AnimatorSet newOne = new AnimatorSet();
            newOne.play(dialogSet).with(colorTranslator);
            dialogSet = newOne;
            dialogSet.setInterpolator(new AccelerateDecelerateInterpolator());

            AnimatorSet completeSet = new AnimatorSet();
            completeSet.play(fabSet).before(dialogSet);
            completeSet.start();

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

    @Override
    public void onBackPressed() {
        if (mDialogSheet.getVisibility() == View.VISIBLE) {
            mDialogSheet.setVisibility(View.GONE);
            mFab.setTranslationX(0);
            mFab.setTranslationY(0);
            mFab.setScaleX(1);
            mFab.setScaleY(1);
            return;
        }
        super.onBackPressed();
    }
}
