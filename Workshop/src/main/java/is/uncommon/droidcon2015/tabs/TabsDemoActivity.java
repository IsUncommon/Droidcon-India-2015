package is.uncommon.droidcon2015.tabs;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.buttons.TintRowViewHolder;
import is.uncommon.droidcon2015.fab.FabTabsBehaviorPagerAdapter;

/**
 * Created by Madhu on 14/12/15.
 */
public class TabsDemoActivity extends AppCompatActivity {
    private static final int[] ICONS = new int[] {
            R.drawable.ic_create_white_24dp, R.drawable.ic_done_white_24dp, R.drawable.ic_add_shopping_cart_white_24dp, R.drawable.ic_add_white_24dp
    };

    @Bind(R.id.appbar) AppBarLayout mAppBar;
    @Bind(R.id.viewpager) ViewPager mViewPager;
    @Bind(R.id.tabs) TabLayout mTabLayout;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.add_tab) Button mAddTab;
    @Bind(R.id.remove_tab) Button mRemoveTab;
    @Bind(R.id.show_icons_switch) SwitchCompat mIconSwitch;
    @Bind(R.id.show_text_switch) SwitchCompat mTextSwitch;
    @Bind(R.id.scrollable_switch) SwitchCompat mScrollableTabsSwitch;
    @Bind(R.id.tab_indicator_color_selector) LinearLayout mTabIndicatorColorSelector;
    @Bind(R.id.tab_selected_text_color_selector) LinearLayout mTabSelectedTextColorSelector;
    @Bind(R.id.tab_normal_text_color_selector) LinearLayout mTabNormalTextColorSelector;
    @Bind(R.id.wire_pager_switch) SwitchCompat mWirePagerSwitch;

    List<String> mTabNames = new ArrayList<String>();
    private FabTabsBehaviorPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_demo);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Tabs Demo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //color indicators
        TintRowViewHolder tabIndicatorColorSelector = TintRowViewHolder.getInstance(mTabIndicatorColorSelector);
        final TintRowViewHolder selectedTextColorSelector = TintRowViewHolder.getInstance(mTabSelectedTextColorSelector);
        final TintRowViewHolder normalTextColorSelector = TintRowViewHolder.getInstance(mTabNormalTextColorSelector);

        tabIndicatorColorSelector.setOnTintRowSelectedListener(new TintRowViewHolder.TintRowSelectedListener() {
            @Override
            public void onTintSelected(int color) {
                mTabLayout.setSelectedTabIndicatorColor(color);
            }
        });
        mTabLayout.setSelectedTabIndicatorColor(tabIndicatorColorSelector.getSelectedColor());

        selectedTextColorSelector.setOnTintRowSelectedListener(new TintRowViewHolder.TintRowSelectedListener() {
            @Override
            public void onTintSelected(int color) {
                mTabLayout.setTabTextColors(normalTextColorSelector.getSelectedColor(), color);
            }
        });

        normalTextColorSelector.setOnTintRowSelectedListener(new TintRowViewHolder.TintRowSelectedListener() {
            @Override
            public void onTintSelected(int color) {
                mTabLayout.setTabTextColors(color, selectedTextColorSelector.getSelectedColor());
            }
        });
        mTabLayout.setTabTextColors(normalTextColorSelector.getSelectedColor(), selectedTextColorSelector.getSelectedColor());

        setUpAddAndRemoveTabs();
        setUpIconAndTextSwitches();

        mWirePagerSwitch.setChecked(false);

        mWirePagerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    mPagerAdapter = new FabTabsBehaviorPagerAdapter(getFragmentManager(), mTabNames);
                    mViewPager.setAdapter(mPagerAdapter);
                    mViewPager.setCurrentItem(mTabLayout.getSelectedTabPosition());
                    mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
                    mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            int selectedTabPosition = mTabLayout.getSelectedTabPosition();
                            mViewPager.setCurrentItem(selectedTabPosition);
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                } else {
                    mViewPager.setAdapter(null);
                }
            }
        });
    }

    private void setUpIconAndTextSwitches() {
        mIconSwitch.setChecked(true);
        mTextSwitch.setChecked(true);

        mIconSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                        mTabLayout.getTabAt(i).setIcon(ICONS[i % ICONS.length]);
                    }
                } else {
                    for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                        mTabLayout.getTabAt(i).setIcon(null);
                    }
                }
            }
        });

        mTextSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                        mTabLayout.getTabAt(i).setText("Tab #" + (i + 1));
                    }
                } else {
                    for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                        mTabLayout.getTabAt(i).setText(null);
                    }
                }
            }
        });

        mScrollableTabsSwitch.setChecked(true);
        mScrollableTabsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                mTabLayout.setTabMode(checked ? TabLayout.MODE_SCROLLABLE : TabLayout.MODE_FIXED);
            }
        });
    }

    private void setUpAddAndRemoveTabs() {
        mAddTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addANewTab();
            }
        });

        mRemoveTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeLastTab();
            }
        });
    }

    private void addANewTab() {
        int size = mTabNames.size();
        String newItemName = "Tab #" + (size + 1);
        mTabNames.add(newItemName);
        TabLayout.Tab tab = mTabLayout.newTab();
        if (mTextSwitch.isChecked()) {
            tab.setText(newItemName);
        } else {
            tab.setText(null);
        }

        if (mIconSwitch.isChecked()) {
            tab.setIcon(ICONS[mTabNames.size() % ICONS.length]);
        } else {
            tab.setIcon(null);
        }

        mTabLayout.addTab(tab);
        if (mPagerAdapter != null) {
            mPagerAdapter.changeData(mTabNames);
        }
    }

    private void removeLastTab() {
        if (!mTabNames.isEmpty()) {
            int size = mTabNames.size();
            mTabLayout.removeTabAt(size - 1);
            mTabNames.remove(size - 1);
        }
        if (mPagerAdapter != null) {
            mPagerAdapter.changeData(mTabNames);
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
