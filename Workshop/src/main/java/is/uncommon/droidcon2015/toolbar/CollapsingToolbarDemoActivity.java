package is.uncommon.droidcon2015.toolbar;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.buttons.TintRowViewHolder;

/**
 * Created by Madhu on 12/12/15.
 */
public class CollapsingToolbarDemoActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.content_scrim_tint_row) LinearLayout contentScrimTintRow;
    @Bind(R.id.status_scrim_tint_row) LinearLayout statusScrimTintRow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsible_toolbar_demo);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TintRowViewHolder contentScrimTintRowViewHolder = TintRowViewHolder.getInstance(contentScrimTintRow);
        TintRowViewHolder statusScrimTintRowViewHolder = TintRowViewHolder.getInstance(statusScrimTintRow);
        contentScrimTintRowViewHolder.setOnTintRowSelectedListener(new TintRowViewHolder.TintRowSelectedListener() {
            @Override
            public void onTintSelected(int color) {
                mCollapsingToolbar.setContentScrimColor(color);
            }
        });
        mCollapsingToolbar.setContentScrimColor(contentScrimTintRowViewHolder.getSelectedColor());
        statusScrimTintRowViewHolder.setOnTintRowSelectedListener(new TintRowViewHolder.TintRowSelectedListener() {
            @Override
            public void onTintSelected(int color) {
                mCollapsingToolbar.setStatusBarScrimColor(color);
            }
        });
        mCollapsingToolbar.setStatusBarScrimColor(statusScrimTintRowViewHolder.getSelectedColor());
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
