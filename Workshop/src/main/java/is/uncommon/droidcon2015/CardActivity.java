package is.uncommon.droidcon2015;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
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
        mElevationSeekBar.setMax(20);
        mRadiusSeekBar.setOnSeekBarChangeListener(radiusChangeListener);
        mRadiusSeekBar.setMax(40);
    }

    SeekBar.OnSeekBarChangeListener elevationChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

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

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
