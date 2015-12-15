package is.uncommon.droidcon2015.coord;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.utils.HtmlUtils;


/**
 * Created by Madhu on 11/12/15.
 */
public class CLAnchorsActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.text) TextView mText;
    @Bind(R.id.fab) FloatingActionButton mFab;

    @Bind(R.id.sheet1_left_top) ImageButton mSheet1LeftTop;
    @Bind(R.id.sheet1_left_center) ImageButton mSheet1LeftCenter;
    @Bind(R.id.sheet1_left_bottom) ImageButton mSheet1LeftBottom;

    @Bind(R.id.sheet1_center_top) ImageButton mSheet1CenterTop;
    @Bind(R.id.sheet1_center) ImageButton mSheet1Center;
    @Bind(R.id.sheet1_center_bottom) ImageButton mSheet1CenterBottom;

    @Bind(R.id.sheet1_right_top) ImageButton mSheet1RightTop;
    @Bind(R.id.sheet1_right_center) ImageButton mSheet1RightCenter;
    @Bind(R.id.sheet1_right_bottom) ImageButton mSheet1RightBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cl_anchors);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setTitle("Anchors Demo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        HtmlUtils.setHtmlText(mText, getString(R.string.cl_anchors_description));
        ImageButton[] sheetButtons = new ImageButton[] {
                mSheet1LeftTop, mSheet1LeftCenter, mSheet1LeftBottom,
                mSheet1CenterTop, mSheet1Center, mSheet1CenterBottom,
                mSheet1RightTop, mSheet1RightCenter, mSheet1RightBottom,
        };
        for(ImageButton button : sheetButtons) {
            button.setOnClickListener(this);
        }
        mSheet1Center.performClick();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateFabLayout(int anchorId, int gravity) {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mFab.getLayoutParams();
        layoutParams.setAnchorId(anchorId);
        layoutParams.anchorGravity = gravity;
        mFab.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View view) {
        int layoutId = -1;
        int gravity = -1;
        if (view.getId() == R.id.sheet1_left_top) {
            layoutId = R.id.sheet1;
            gravity = Gravity.START | Gravity.TOP;
        } else if (view.getId() == R.id.sheet1_left_center) {
            layoutId = R.id.sheet1;
            gravity = Gravity.START | Gravity.CENTER;
        } else if (view.getId() == R.id.sheet1_left_bottom) {
            layoutId = R.id.sheet1;
            gravity = Gravity.START | Gravity.BOTTOM;
        } else if (view.getId() == R.id.sheet1_right_top) {
            layoutId = R.id.sheet1;
            gravity = Gravity.END | Gravity.TOP;
        } else if (view.getId() == R.id.sheet1_right_center) {
            layoutId = R.id.sheet1;
            gravity = Gravity.END | Gravity.CENTER;
        } else if (view.getId() == R.id.sheet1_right_bottom) {
            layoutId = R.id.sheet1;
            gravity = Gravity.END | Gravity.BOTTOM;
        } else if (view.getId() == R.id.sheet1_center_top) {
            layoutId = R.id.sheet1;
            gravity = Gravity.CENTER | Gravity.TOP;
        } else if (view.getId() == R.id.sheet1_center) {
            layoutId = R.id.sheet1;
            gravity = Gravity.CENTER;
        } else if (view.getId() == R.id.sheet1_center_bottom) {
            layoutId = R.id.sheet1;
            gravity = Gravity.CENTER | Gravity.BOTTOM;
        }
        updateFabLayout(layoutId, gravity);
    }
}
