package is.uncommon.droidcon2015;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.models.PrimaryContent;
import is.uncommon.droidcon2015.utils.Extras;

public class SectionDetailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.iv_header_image) ImageView mHeaderImageView;
    //@Bind(R.id.tv_card_summary) TextView mSummaryTextView;

    public static void startActivity(Activity activity, PrimaryContent content, ImageView imageView) {
        Intent intent = new Intent(activity, SectionDetailActivity.class);
        intent.putExtra(Extras.CONTENT, content);
        intent.putExtra(Extras.HEADER_IMAGE, content.image);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imageView, "header_image");
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mHeaderImageView.setImageResource(getIntent().getIntExtra(Extras.HEADER_IMAGE, -1));
        PrimaryContent content = getIntent().getParcelableExtra(Extras.CONTENT);

        /*mSummaryTextView.setText(HtmlUtils.getHtml(content.summary, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
                    getResources().getDisplayMetrics()), Color.parseColor("#c3c3c3")));*/
        getSupportActionBar().setTitle(content.sectionName);
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
        supportFinishAfterTransition();
        super.onBackPressed();
    }
}
