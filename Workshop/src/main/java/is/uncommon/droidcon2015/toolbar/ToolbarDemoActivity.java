package is.uncommon.droidcon2015.toolbar;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.utils.HtmlUtils;

/**
 * Created by Madhu on 12/12/15.
 */
public class ToolbarDemoActivity extends AppCompatActivity {

    @Bind(R.id.appbar) AppBarLayout mAppBar;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.toolbar_sheet) Toolbar mToolbarSheet;
    @Bind(R.id.text) TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_demo);
        ButterKnife.bind(this);
        ViewCompat.setElevation(mAppBar, 0f);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        mToolbarSheet.inflateMenu(R.menu.toolbar_demo_menu);
        mToolbarSheet.setTitle("Toolbar");
        HtmlUtils.setHtmlText(mText, getString(R.string.toolbar_demo_description));
        mToolbarSheet.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String menuTitle = (String) item.getTitle();
                Snackbar.make(mToolbarSheet, "Toolbar item " + menuTitle + " in sheet clicked", Snackbar.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_demo_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
