package is.uncommon.droidcon2015;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SnackbarActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.cl_snackbar) CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.fab_snackbar) FloatingActionButton mFloatingActionButton;
    @Bind(R.id.et_snackbar) EditText mEditText;

    private Snackbar mSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Snackbars.");

        showSnackbarWithMessage("Hi, I'm a snackbar.");
    }

    private void showSnackbarWithMessage(String message) {
        mSnackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
        mSnackbar.show();
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
        mSnackbar.setAction("nope.", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackbar.dismiss();
            }
        });
        mSnackbar.show();
    }

}
