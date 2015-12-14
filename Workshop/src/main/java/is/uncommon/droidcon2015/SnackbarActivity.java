package is.uncommon.droidcon2015;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import is.uncommon.droidcon2015.buttons.TintRowViewHolder;

public class SnackbarActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.cl_snackbar) CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.tint_row_fab_background) LinearLayout mTintLayoutBackground;
    @Bind(R.id.et_snackbar) EditText mEditText;

    private Snackbar mSnackbar;
    private ColorStateList mColorStateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Snackbars.");

        TintRowViewHolder holder = TintRowViewHolder.getInstance(mTintLayoutBackground);
        holder.setOnTintRowSelectedListener(new TintRowViewHolder.TintRowSelectedListener() {
            @Override
            public void onTintSelected(int color) {
                mColorStateList = ColorStateList.valueOf(color);
            }
        });
        mColorStateList = ColorStateList.valueOf(holder.getSelectedColor());

        showSnackbarWithMessage("Hi, I'm a snackbar.");
    }

    private void showSnackbarWithMessage(String message) {
        mSnackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab_snackbar)
    public void onClickSnackbar() {
        if (TextUtils.isEmpty(mEditText.getText())) {
            showSnackbarWithMessage("... and again!");
        } else {
            showSnackbarWithMessage(mEditText.getText().toString());
        }
    }

    @OnClick(R.id.btn_snackbar_with_action)
    public void onClickSnackbarWithAction() {
        if (TextUtils.isEmpty(mEditText.getText())) {
            showSnackbarWithMessageAndAction("... and again!");
        } else {
            showSnackbarWithMessageAndAction(mEditText.getText().toString());
        }
    }

    private void showSnackbarWithMessageAndAction(String message) {
        mSnackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
        mSnackbar.setActionTextColor(mColorStateList);
        mSnackbar.setAction("nope.", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        mSnackbar.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Some message?")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

}
