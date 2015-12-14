package is.uncommon.droidcon2015;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.utils.HtmlUtils;

/**
 * Created by Madhu on 14/12/15.
 */
public class SwipeRefreshLayoutDemoActivity extends AppCompatActivity {

    private static final int[] COLORS = new int[] {
            Color.parseColor("#9C27B0"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#E91E63"),
            Color.parseColor("#FF9800")
    };

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.text) TextView mText;
    @Bind(R.id.swipe_refresh) SwipeRefreshLayout mSwipeRefreshLayout;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Swipe Refresh Demo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSwipeRefreshLayout.setColorSchemeColors(COLORS);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });
        HtmlUtils.setHtmlText(mText, getString(R.string.swipe_refresh_demo));
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
