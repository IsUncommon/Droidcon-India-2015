package is.uncommon.droidcon2015.buttons;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.utils.HtmlUtils;

/**
 * Created by Madhu on 02/12/15.
 */
public class ButtonsDemoActivity extends AppCompatActivity implements TintRowViewHolder.TintRowSelectedListener {

    @Bind(R.id.raised_button_desc) TextView mRaisedButtonDesc;
    @Bind(R.id.fab_button_desc) TextView mFabButtonDesc;
    @Bind(R.id.demo_button) AppCompatButton mDemoButton;
    @Bind(R.id.tint_row) LinearLayout mTintLayout;
    @Bind(R.id.fab) FloatingActionButton mFab;
    @Bind(R.id.appbar) AppBarLayout mAppbar;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.demo_button_flat) AppCompatButton mDemoFlatButton;
    @Bind(R.id.flat_button_desc) TextView mFlatButtonDescription;
    @Bind(R.id.scroll_view) NestedScrollView mNestedScrollView;
    private int mCurrentTintColor = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons_demo);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.demo_buttons);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        HtmlUtils.setHtmlText(mRaisedButtonDesc, getString(R.string.raised_button_description));
        HtmlUtils.setHtmlText(mFabButtonDesc, getString(R.string.fab_description));
        HtmlUtils.setHtmlText(mFlatButtonDescription, getString(R.string.borderless_button_description));
        TintRowViewHolder holder = TintRowViewHolder.getInstance(mTintLayout);
        holder.setOnTintRowSelectedListener(this);
        onTintSelected(holder.getSelectedColor());
    }

    @Override
    public void onTintSelected(final int color) {
        if (mCurrentTintColor == -1) {
            changeTintColor(color);
            mCurrentTintColor = color;
        } else {
            //animate tint color
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ValueAnimator animator = ValueAnimator.ofArgb(mCurrentTintColor, color);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(300);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        changeTintColor((Integer) valueAnimator.getAnimatedValue());
                    }
                });
                animator.start();
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        changeTintColor(color);
                        mCurrentTintColor = color;
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        changeTintColor(color);
                        mCurrentTintColor = color;
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
            } else {
                changeTintColor(color);
                mCurrentTintColor = color;
            }
        }
    }

    private void changeTintColor(int color) {
        ColorStateList colorState = ColorStateList.valueOf(color);
        mDemoButton.setSupportBackgroundTintList(colorState);
        ViewCompat.setBackgroundTintList(mFab, colorState);
        ViewCompat.setBackgroundTintList(mAppbar, colorState);
        ViewCompat.setBackgroundTintList(mToolbar, colorState);
        mDemoFlatButton.setTextColor(colorState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
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
