package is.uncommon.droidcon2015.toolbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.utils.HtmlUtils;

/**
 * Created by Madhu on 12/12/15.
 */
public class AppbarDemoActivity extends AppCompatActivity {

    @Bind(R.id.appbar) AppBarLayout mAppBar;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.text) TextView mText;
    @Bind(R.id.image) ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_demo);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Appbar demo");
        HtmlUtils.setHtmlText(mText, getString(R.string.appbar_demo_description));
        mImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                setupImage(0f);
            }
        });
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) > mToolbar.getHeight()) {
                    setupImage(Math.abs(verticalOffset) - mToolbar.getHeight());
                } else {
                    setupImage(0f);
                }
            }
        });
    }

    private void setupImage(float offset) {
        final Matrix matrix = new Matrix();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.temp_image);
        float bitmapAspect = (float) bitmap.getWidth() / (float) bitmap.getHeight();
        mImageView.setScaleType(ImageView.ScaleType.MATRIX);
        mImageView.setImageBitmap(bitmap);
        //do fit height
        float newHeight = (mImageView.getHeight() - offset);
        float newWidth = newHeight * bitmapAspect;
        float scaleX = (float) newWidth / (float) bitmap.getWidth();
        float scaleY = (float) newHeight / (float) bitmap.getHeight();
        float scaleFactor = Math.max(scaleX, scaleY);
        matrix.setScale(scaleFactor, scaleFactor);
        //now translate to center
        float diff = (float) mImageView.getWidth() - newWidth;
        mImageView.setTranslationX(diff / 2);
        mImageView.setTranslationY(offset);
        mImageView.setImageMatrix(matrix);
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
