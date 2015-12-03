package is.uncommon.droidcon2015.fab;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.buttons.TintRowViewHolder;


/**
 * Created by Madhu on 03/12/15.
 */
public class FabControlsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tint_row_fab_background) LinearLayout mTintLayoutBackground;
    @Bind(R.id.tint_row_fab_ripple) LinearLayout mTintLayoutRipple;
    @Bind(R.id.fab_mini) FloatingActionButton mFabMini;
    @Bind(R.id.fab_normal) FloatingActionButton mFabNormal;
    @Bind(R.id.fab_mini_normal_switch) SwitchCompat mFabMiniCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_controls);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.title_fab_controls);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //background
        TintRowViewHolder holder = TintRowViewHolder.getInstance(mTintLayoutBackground);
        holder.setOnTintRowSelectedListener(new TintRowViewHolder.TintRowSelectedListener() {
            @Override
            public void onTintSelected(int color) {
                ColorStateList colorState = ColorStateList.valueOf(color);
                mFabMini.setBackgroundTintList(colorState);
                mFabNormal.setBackgroundTintList(colorState);
            }
        });
        ColorStateList colorState = ColorStateList.valueOf(holder.getSelectedColor());
        mFabMini.setBackgroundTintList(colorState);
        mFabNormal.setBackgroundTintList(colorState);
        //ripple
        holder = TintRowViewHolder.getInstance(mTintLayoutRipple);
        holder.setOnTintRowSelectedListener(new TintRowViewHolder.TintRowSelectedListener() {
            @Override
            public void onTintSelected(int color) {
                mFabMini.setRippleColor(color);
                mFabNormal.setRippleColor(color);
            }
        });
        mFabMini.setRippleColor(holder.getSelectedColor());
        mFabNormal.setRippleColor(holder.getSelectedColor());
        mFabMiniCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                mFabMiniCheckbox.setText(checked? "Normal" : "Mini");
                if (mFabMini.isShown()) {
                    mFabMini.hide();
                    animateFab(mFabNormal);
                } else {
                    mFabNormal.hide();
                    animateFab(mFabMini);
                }
            }
        });
        mFabMini.hide();
        mFabMiniCheckbox.setText("Normal");
    }

    private void animateFab(final FloatingActionButton fab) {
        final Matrix imageMatrix = new Matrix();
        fab.setScaleType(ImageView.ScaleType.MATRIX);
        imageMatrix.setScale(0, 0);
        fab.setImageMatrix(imageMatrix);
        fab.show(new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onShown(final FloatingActionButton fab) {
                final Matrix imageMatrix = new Matrix();
                final int fabWidth = fab.getDrawable().getIntrinsicWidth();
                final int fabHeight = fab.getDrawable().getIntrinsicHeight();
                fab.setScaleType(ImageView.ScaleType.MATRIX);
                imageMatrix.setScale(0, 0);
                fab.setImageMatrix(imageMatrix);
                ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(150);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float scale = (float) valueAnimator.getAnimatedValue();
                        imageMatrix.setScale(scale, scale, fabWidth / 2, fabHeight / 2);
                        fab.setImageMatrix(imageMatrix);
                    }
                });
                animator.start();
            }
        });
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
