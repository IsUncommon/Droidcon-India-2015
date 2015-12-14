package is.uncommon.droidcon2015;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.models.PrimaryContent;
import is.uncommon.droidcon2015.utils.Extras;
import is.uncommon.droidcon2015.utils.HtmlUtils;

public class SectionDetailActivity extends AppCompatActivity {

    private final static String TAG = "SectionDetailActivity";

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.iv_header_image) ImageView mHeaderImageView;
    @Bind(R.id.tv_card_summary) TextView mSummaryTextView;
    @Bind(R.id.stub) ViewStub mViewStub;

    private PrimaryContent mContent;

    public static void startActivity(Activity activity, PrimaryContent content, ImageView imageView) {
        Intent intent = new Intent(activity, SectionDetailActivity.class);
        intent.putExtra(Extras.CONTENT, content);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imageView, "header_image");
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_detail);
        ButterKnife.bind(this);

        mContent = getIntent().getParcelableExtra(Extras.CONTENT);
        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout ct = ButterKnife.findById(this, R.id.ct_header);
        ct.setContentScrimColor(ContextCompat.getColor(this, mContent.color));
        ct.setStatusBarScrimColor(ContextCompat.getColor(this, mContent.color));
        
        mHeaderImageView.setImageResource(mContent.image);
        mHeaderImageView.setColorFilter(mContent.color, PorterDuff.Mode.SRC_ATOP);
        mSummaryTextView.setText(HtmlUtils.getHtml(mContent.summary, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
                getResources().getDisplayMetrics()), Color.parseColor("#c3c3c3")));
        getSupportActionBar().setTitle(mContent.sectionName);
        mViewStub.setLayoutResource(mContent.layoutId);
        mViewStub.inflate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }
}
