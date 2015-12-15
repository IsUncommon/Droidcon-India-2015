package is.uncommon.droidcon2015;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.models.PrimaryContent;
import is.uncommon.droidcon2015.utils.Extras;
import is.uncommon.droidcon2015.utils.HtmlUtils;

public class SectionDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.iv_header_image) ImageView mHeaderImageView;
    @Bind(R.id.tv_card_summary) TextView mSummaryTextView;
    @Bind(R.id.stub) ViewStub mViewStub;

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

        PrimaryContent content = getIntent().getParcelableExtra(Extras.CONTENT);
        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int color = ContextCompat.getColor(this, content.color);

        CollapsingToolbarLayout ct = ButterKnife.findById(this, R.id.ct_header);
        ct.setContentScrimColor(color);
        ct.setStatusBarScrimColor(color);

        ((TextView) ButterKnife.findById(this, R.id.tv_title1)).setTextColor(color);
        ((TextView) ButterKnife.findById(this, R.id.tv_title2)).setTextColor(color);

        mHeaderImageView.setImageResource(content.image);
        mHeaderImageView.setColorFilter(content.color, PorterDuff.Mode.SRC_ATOP);
        mSummaryTextView.setText(HtmlUtils.getHtml(content.summary, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
                getResources().getDisplayMetrics()), Color.parseColor("#c3c3c3")));
        getSupportActionBar().setTitle(content.sectionName);
        mViewStub.setLayoutResource(content.layoutId);
        ViewGroup group = (ViewGroup) mViewStub.inflate();
        addViewGroupButtonClicks(group);

    }

    private void addViewGroupButtonClicks(ViewGroup group) {
        for (int i = 0; i < group.getChildCount(); i++) {
            View child = group.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(this);
            } else if (child instanceof LinearLayout) {
                addViewGroupButtonClicks((ViewGroup) child);
            }
        }
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

    @Override
    public void onClick(View view) {
        ActivityUtils.launch(this, view.getId());
    }
}
