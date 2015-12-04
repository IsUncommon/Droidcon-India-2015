package is.uncommon.droidcon2015.fab;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

/**
 * Created by Madhu on 04/12/15.
 */
public class FabTabsBehaviorPagerAdapter extends FragmentPagerAdapter {

    public FabTabsBehaviorPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TabsBehaviorFragment.getInstance("Position " + (position + 1));
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page #" + (position + 1);
    }
}
