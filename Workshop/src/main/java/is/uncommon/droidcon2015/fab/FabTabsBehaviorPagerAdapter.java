package is.uncommon.droidcon2015.fab;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhu on 04/12/15.
 */
public class FabTabsBehaviorPagerAdapter extends FragmentPagerAdapter {

    private List<String> mTabNames = new ArrayList<>();

    public FabTabsBehaviorPagerAdapter(FragmentManager fm) {
        super(fm);
        mTabNames.add("Page #1");
        mTabNames.add("Page #2");
        mTabNames.add("Page #3");
    }

    public FabTabsBehaviorPagerAdapter(FragmentManager fm, List<String> tabNames) {
        super(fm);
        this.mTabNames = tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        return TabsBehaviorFragment.getInstance(mTabNames.get(position));
    }

    @Override
    public int getCount() {
        return mTabNames.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabNames.get(position);
    }

    public void changeData(List<String> tabNames) {
        mTabNames = new ArrayList<>(tabNames);
        notifyDataSetChanged();
    }
}
