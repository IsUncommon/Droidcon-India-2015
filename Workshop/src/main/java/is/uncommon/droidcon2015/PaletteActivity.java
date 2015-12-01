package is.uncommon.droidcon2015;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PaletteActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.vp_palette) ViewPager mViewPager;

    private static final int[] IMAGES = { R.drawable.palette1, R.drawable.palette2, R.drawable.palette3, R.drawable.palette4 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Palette.");

        mViewPager.setAdapter(new PalettePagerAdapter());
    }

    protected class PalettePagerAdapter extends PagerAdapter {

        @Bind(R.id.iv_palette) ImageView imageView;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.page_palette, container, false);
            ButterKnife.bind(this, view);
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
