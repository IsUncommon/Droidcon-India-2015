package is.uncommon.droidcon2015;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaletteActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.vp_palette) ViewPager mViewPager;
    @Bind(R.id.tv_palette_head) TextView mHeadingTextView;
    @Bind(R.id.tv_palette_subhead) TextView mSubheadingTextView;
    @Bind(R.id.tv_palette_body) TextView mBodyTextView;
    @Bind(R.id.btn_vibrant) Button mVibrantButton;
    @Bind(R.id.btn_muted) Button mMutedButton;

    private static final int[] IMAGES = { R.drawable.palette5, R.drawable.palette6, R.drawable.palette3 };
    private WeakHashMap<Bitmap, Palette> mBitmapPaletteMap;
    private boolean isVibrantSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Palette.");

        mBitmapPaletteMap = new WeakHashMap<>();

        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(new PalettePagerAdapter());
        onPageSelected(mViewPager.getCurrentItem());

        if (isVibrantSelected) {
            mVibrantButton.setSelected(true);
            mMutedButton.setSelected(false);
        } else {
            mVibrantButton.setSelected(false);
            mMutedButton.setSelected(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_vibrant)
    public void onClickVibrantButton() {
        isVibrantSelected = true;
        mVibrantButton.setSelected(true);
        mMutedButton.setSelected(false);
        onPageSelected(mViewPager.getCurrentItem());
    }

    @OnClick(R.id.btn_muted)
    public void onClickMutedButton() {
        isVibrantSelected = false;
        mVibrantButton.setSelected(false);
        mMutedButton.setSelected(true);
        onPageSelected(mViewPager.getCurrentItem());
    }

    @Override
    public void onPageSelected(int position) {
        Bitmap bitmap = ((BitmapDrawable) ContextCompat.getDrawable(this, IMAGES[position])).getBitmap();
        if (mBitmapPaletteMap.containsKey(bitmap)) {
            colorTextUsingPalette(mBitmapPaletteMap.get(bitmap));
        } else {
            generatePalette(bitmap);
        }
    }

    private void colorTextUsingPalette(Palette palette) {
        int vibrantHeadColor = palette.getVibrantColor(0xffbbbbbb);
        int vibrantSubheadColor = palette.getDarkVibrantColor(0xffbbbbbb);
        int vibrantBodyColor = palette.getLightVibrantColor(0xffbbbbbb);
        int mutedHeadColor = palette.getMutedColor(0xffbbbbbb);
        int mutedSubheadColor = palette.getDarkMutedColor(0xffbbbbbb);
        int mutedBodyColor = palette.getLightMutedColor(0xffbbbbbb);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ValueAnimator headerColorAnimator = ValueAnimator.ofArgb(mHeadingTextView.getCurrentTextColor(), isVibrantSelected ? vibrantHeadColor : mutedHeadColor);
            headerColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int color = (int) valueAnimator.getAnimatedValue();
                    mHeadingTextView.setTextColor(color);
                }
            });

            ValueAnimator subheadColorAnimator = ValueAnimator.ofArgb(mSubheadingTextView.getCurrentTextColor(), isVibrantSelected ? vibrantSubheadColor : mutedSubheadColor);
            subheadColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int color = (int) valueAnimator.getAnimatedValue();
                    mSubheadingTextView.setTextColor(color);
                }
            });

            ValueAnimator bodyColorAnimator = ValueAnimator.ofArgb(mBodyTextView.getCurrentTextColor(), isVibrantSelected ? vibrantBodyColor : mutedBodyColor);
            bodyColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int color = (int) valueAnimator.getAnimatedValue();
                    mBodyTextView.setTextColor(color);
                }
            });

            AnimatorSet set1 = new AnimatorSet();
            set1.play(headerColorAnimator).with(subheadColorAnimator);
            AnimatorSet set2 = new AnimatorSet();
            set2.play(set1).with(bodyColorAnimator);

            set2.setDuration(300);
            set2.setInterpolator(new AccelerateDecelerateInterpolator());
            set2.start();
        } else {
            if (isVibrantSelected) {
                mHeadingTextView.setTextColor(vibrantHeadColor);
                mSubheadingTextView.setTextColor(vibrantSubheadColor);
                mBodyTextView.setTextColor(vibrantBodyColor);
            } else {
                mHeadingTextView.setTextColor(mutedHeadColor);
                mSubheadingTextView.setTextColor(mutedSubheadColor);
                mBodyTextView.setTextColor(mutedBodyColor);
            }
        }

    }

    private void generatePalette(final Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                mBitmapPaletteMap.put(bitmap, palette);
                colorTextUsingPalette(palette);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    protected class PalettePagerAdapter extends PagerAdapter {

        @Bind(R.id.iv_palette) ImageView imageView;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.page_palette, container, false);
            ButterKnife.bind(this, view);
            //this should be loaded async, nvm now
            imageView.setImageResource(IMAGES[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(((ImageView) object));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return IMAGES.length;
        }
    }
}
