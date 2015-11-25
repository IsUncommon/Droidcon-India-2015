package is.uncommon.droidcon2015;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.rv_main) RecyclerView mRecyclerView;

    private static final String[] PRIMARY_CONTENTS = { "Layouts", "Views", "View Groups", "Palette", "Styles, Themes and Backward Compatibility" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new PrimarySectionsAdapter());
    }

    private class PrimarySectionsAdapter extends RecyclerView.Adapter<PrimarySectionsAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.primarysections_card, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.title.setText(PRIMARY_CONTENTS[position]);
            holder.imageView.setImageResource(R.drawable.temp_image);
        }

        @Override
        public int getItemCount() {
            return PRIMARY_CONTENTS.length;
        }

        protected class ViewHolder extends RecyclerView.ViewHolder {

            private ImageView imageView;
            private TextView title;

            public ViewHolder(View itemView) {
                super(itemView);
                this.imageView = ButterKnife.findById(itemView, R.id.card_image);
                this.title = ButterKnife.findById(itemView, R.id.card_title);
            }
        }
    }
}
