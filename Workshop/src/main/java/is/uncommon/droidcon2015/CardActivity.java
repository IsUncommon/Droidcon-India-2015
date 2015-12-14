package is.uncommon.droidcon2015;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.SeekBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.seek_card_elevation) SeekBar mElevationSeekBar;
    @Bind(R.id.seek_card_radius) SeekBar mRadiusSeekBar;
    @Bind(R.id.card_card) CardView mCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cards.");

        mElevationSeekBar.setOnSeekBarChangeListener(elevationChangeListener);
        mElevationSeekBar.setMax((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));

        mRadiusSeekBar.setOnSeekBarChangeListener(radiusChangeListener);
        mRadiusSeekBar.setMax((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics()));
    }

    SeekBar.OnSeekBarChangeListener elevationChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int willuse = progress + 2;
            float elev = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, willuse, getResources().getDisplayMetrics());
            mCardView.setCardElevation(elev);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    SeekBar.OnSeekBarChangeListener radiusChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int willuse = progress + 4;
            float radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, willuse, getResources().getDisplayMetrics());
            mCardView.setRadius(radius);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
