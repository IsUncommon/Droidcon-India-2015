package is.uncommon.droidcon2015;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherAppCompatControlsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tv_other) TextView mTextView;
    @Bind(R.id.btn_other) Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_appcompat);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Other controls.");
    }

    @OnClick(R.id.btn_other_textview_none)
    public void removeTextViewTint() {
        ViewCompat.setBackgroundTintList(mTextView, null);
    }

    @OnClick(R.id.btn_other_textview_default_tint)
    public void defaultColorTextViewTint() {
        ViewCompat.setBackgroundTintList(mTextView, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary)));
        ViewCompat.setBackgroundTintList(mButton, ContextCompat.getColorStateList(this, R.color.text_colors));
    }

    @OnClick(R.id.btn_other_textview_lighten)
    public void lightenTextViewTint() {
        ViewCompat.setBackgroundTintList(mTextView, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary)));
        ViewCompat.setBackgroundTintMode(mTextView, PorterDuff.Mode.LIGHTEN);
    }

    @OnClick(R.id.btn_other_textview_darken)
    public void darkenTextViewTint() {
        ViewCompat.setBackgroundTintList(mTextView, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary)));
        ViewCompat.setBackgroundTintMode(mTextView, PorterDuff.Mode.DARKEN);
    }

    @OnClick(R.id.btn_other_textview_multiply)
    public void multiplyTextViewTint() {
        ViewCompat.setBackgroundTintList(mTextView, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary)));
        ViewCompat.setBackgroundTintMode(mTextView, PorterDuff.Mode.MULTIPLY);
    }
}
