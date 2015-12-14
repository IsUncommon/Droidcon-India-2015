package is.uncommon.droidcon2015.tabs;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;

/**
 * Created by Madhu on 14/12/15.
 */
public class TabsCustomViewDemoActivity extends AppCompatActivity {

    private static final int[] ICONS = new int[] {
            R.drawable.ic_create_white_24dp, R.drawable.ic_done_white_24dp, R.drawable.ic_add_shopping_cart_white_24dp, R.drawable.ic_add_white_24dp
    };

    private static final int SELECTED_TAB_COLOR = Color.parseColor("#FF9800");
    private static final int UNSELECTED_TAB_COLOR = Color.parseColor("#2196F3");


    @Bind(R.id.appbar) AppBarLayout mAppBar;
    @Bind(R.id.tabs) TabLayout mTabLayout;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_custom_view_demo);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Tabs Custom View Demo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        for (int i = 0; i < ICONS.length; i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            mTabLayout.addTab(tab);
            tab.setCustomView(R.layout.custom_tab);
            tab.setText("Tab#" + (i + 1));
            tab.setIcon(ICONS[i]);
            applyTabUnSelected(tab);
        }

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                applyTabSelection(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                applyTabUnSelected(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabLayout.setSelectedTabIndicatorColor(SELECTED_TAB_COLOR);
        applyTabSelection(mTabLayout.getTabAt(0));
    }

    private void applyTabUnSelected(final TabLayout.Tab tab) {
        setTabSelected(tab, false);
    }

    private void setTabSelected(final TabLayout.Tab tab, boolean selected) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ValueAnimator colorAnimator = ValueAnimator.ofArgb(selected ? UNSELECTED_TAB_COLOR : SELECTED_TAB_COLOR, selected ? SELECTED_TAB_COLOR : UNSELECTED_TAB_COLOR);
            colorAnimator.setDuration(300);
            colorAnimator.setInterpolator(new AccelerateInterpolator());
            colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int colorValue = (int) valueAnimator.getAnimatedValue();
                    setTabColor(tab, colorValue);
                }
            });
            colorAnimator.start();
        } else {
            setTabColor(tab, UNSELECTED_TAB_COLOR);
        }
    }

    private void setTabColor(TabLayout.Tab tab, int color) {
        TextView tabText = (TextView) tab.getCustomView().findViewById(android.R.id.text1);
        tabText.setTextColor(color);
        ImageView iconView = (ImageView) tab.getCustomView().findViewById(android.R.id.icon);
        for (int i = 0; i < ICONS.length; i++) {
            if (mTabLayout.getTabAt(i) == tab) {
                Drawable d = ContextCompat.getDrawable(this, ICONS[i]);
                DrawableCompat.setTint(d, color);
                iconView.setImageDrawable(d);
                break;
            }
        }
    }

    private void applyTabSelection(TabLayout.Tab tab) {
        setTabSelected(tab, true);
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
