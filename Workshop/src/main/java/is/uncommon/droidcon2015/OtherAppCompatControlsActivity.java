package is.uncommon.droidcon2015;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherAppCompatControlsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tv_other) TextView mTextView;
    @Bind(R.id.btn_other) Button mButton;
    @Bind(R.id.til_other_password) TextInputLayout mPasswordTextInputLayout;
    @Bind(R.id.et_password) EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_appcompat);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Other controls.");

        setupPasswordTextChangeListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.other_appcompat_controls, menu);

        MenuItem shareItem = menu.findItem(R.id.other_appcompat_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "public static void main()");
        shareActionProvider.setShareIntent(intent);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupPasswordTextChangeListener() {
        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isDigitsOnly(s)) {
                    mPasswordTextInputLayout.setError("Only numbers allowed!");
                    mPasswordTextInputLayout.setErrorEnabled(true);
                } else {
                    mPasswordTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.btn_other_textview_none)
    public void removeTint() {
        ViewCompat.setBackgroundTintList(mTextView, null);
        ViewCompat.setBackgroundTintList(mButton, null);
    }

    @OnClick(R.id.btn_other_textview_default_tint)
    public void defaultColorTint() {
        removeTint();
        ViewCompat.setBackgroundTintList(mTextView, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary)));
        ViewCompat.setBackgroundTintMode(mTextView, PorterDuff.Mode.SRC_IN);

        ViewCompat.setBackgroundTintList(mButton, ContextCompat.getColorStateList(this, R.color.text_colors));
    }

    @OnClick(R.id.btn_other_textview_lighten)
    public void lightenTint() {
        removeTint();
        ViewCompat.setBackgroundTintList(mTextView, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary)));
        ViewCompat.setBackgroundTintMode(mTextView, PorterDuff.Mode.LIGHTEN);

        ViewCompat.setBackgroundTintList(mButton, ContextCompat.getColorStateList(this, R.color.text_colors));

    }

    @OnClick(R.id.btn_other_textview_darken)
    public void darkenTint() {
        removeTint();
        ViewCompat.setBackgroundTintList(mTextView, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary)));
        ViewCompat.setBackgroundTintMode(mTextView, PorterDuff.Mode.DARKEN);

        ViewCompat.setBackgroundTintList(mButton, ContextCompat.getColorStateList(this, R.color.text_colors));

    }

    @OnClick(R.id.btn_other_textview_multiply)
    public void multiplyTint() {
        removeTint();
        ViewCompat.setBackgroundTintList(mTextView, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary)));
        ViewCompat.setBackgroundTintMode(mTextView, PorterDuff.Mode.MULTIPLY);

        ViewCompat.setBackgroundTintList(mButton, ContextCompat.getColorStateList(this, R.color.text_colors));
    }
}
