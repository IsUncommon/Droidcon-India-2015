package is.uncommon.droidcon2015;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NavigationViewActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view) NavigationView mNavigationView;
    @Bind(R.id.tv_nav_message) TextView mNavMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationview);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("NavigationView.");

        mDrawerLayout.openDrawer(mNavigationView);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.navigation_item_home:
                        mNavMessage.setText(R.string.pull_from_left_edge);
                        return true;

                    case R.id.navigation_item_1:
                        mNavMessage.setText("Selected navigation item one");
                        return true;

                    case R.id.navigation_item_2:
                        mNavMessage.setText("Selected navigation item two");
                        return true;

                    case R.id.navigation_sub_item_1:
                        mNavMessage.setText("Selected navigation subitem one");
                        return true;

                    case R.id.navigation_sub_item_2:
                        mNavMessage.setText("Selected navigation subitem two");
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
