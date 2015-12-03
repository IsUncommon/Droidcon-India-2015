package is.uncommon.droidcon2015.fab;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.buttons.TintRowViewHolder;


/**
 * Created by Madhu on 03/12/15.
 */
public class FabControlsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tint_row_fab_background) LinearLayout mTintLayoutBackground;
    @Bind(R.id.tint_row_fab_ripple) LinearLayout mTintLayoutRipple;
    @Bind(R.id.fab_mini) FloatingActionButton mFabMini;
    @Bind(R.id.fab_normal) FloatingActionButton mFabNormal;
    @Bind(R.id.fab_mini_show_checkbox) CheckBox mFabMiniCheckbox;
    @Bind(R.id.fab_normal_show_checkbox) CheckBox mFabNormalCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_controls);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.title_fab_controls);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //background
        TintRowViewHolder holder = TintRowViewHolder.getInstance(mTintLayoutBackground);
        holder.setOnTintRowSelectedListener(new TintRowViewHolder.TintRowSelectedListener() {
            @Override
            public void onTintSelected(int color) {
                ColorStateList colorState = ColorStateList.valueOf(color);
                mFabMini.setBackgroundTintList(colorState);
                mFabNormal.setBackgroundTintList(colorState);
            }
        });
        ColorStateList colorState = ColorStateList.valueOf(holder.getSelectedColor());
        mFabMini.setBackgroundTintList(colorState);
        mFabNormal.setBackgroundTintList(colorState);
        //ripple
        holder = TintRowViewHolder.getInstance(mTintLayoutRipple);
        holder.setOnTintRowSelectedListener(new TintRowViewHolder.TintRowSelectedListener() {
            @Override
            public void onTintSelected(int color) {
                mFabMini.setRippleColor(color);
                mFabNormal.setRippleColor(color);
            }
        });
        mFabMini.setRippleColor(holder.getSelectedColor());
        mFabNormal.setRippleColor(holder.getSelectedColor());
        mFabMiniCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mFabMini.isShown()) {
                    mFabMini.hide();
                } else {
                    mFabMini.show();
                }
            }
        });

        mFabNormalCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mFabNormal.isShown()) {
                    mFabNormal.hide();
                } else {
                    mFabNormal.show();
                }
            }
        });
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
