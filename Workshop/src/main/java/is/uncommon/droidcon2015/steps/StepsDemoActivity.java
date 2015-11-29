package is.uncommon.droidcon2015.steps;

import android.os.Bundle;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.utils.HtmlUtils;

/**
 * Created by Madhu on 29/11/15.
 */
public class StepsDemoActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tv_steps_demo_description_1) TextView mDemoText1;
    @Bind(R.id.tv_steps_demo_description_2) TextView mDemoText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_demo);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.steps_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        HtmlUtils.setHtmlText(mDemoText1, getString(R.string.steps_demo_description_top_sheet));
        HtmlUtils.setHtmlText(mDemoText2, getString(R.string.steps_demo_description_bottom_sheet));
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
