package is.uncommon.droidcon2015;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.adapter.PrimarySectionsAdapter;
import is.uncommon.droidcon2015.models.PrimaryContent;

public class MainActivity extends AppCompatActivity implements PrimarySectionsAdapter.ClickInterface {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.rv_main) RecyclerView mRecyclerView;

    private static final String TAG = "MainActivity";
    private List<PrimaryContent> mPrimaryContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(manager);
        String[] headings = getResources().getStringArray(R.array.primary_content_headings);
        String[] summaries = getResources().getStringArray(R.array.primary_content_description);
        TypedArray images = getResources().obtainTypedArray(R.array.primary_content_images);
        TypedArray colors = getResources().obtainTypedArray(R.array.primary_content_colors);

        mPrimaryContents = new ArrayList<>();
        for (int i = 0; i < headings.length; i++) {
            PrimaryContent content = new PrimaryContent();
            content.sectionName = headings[i];
            content.summary = summaries[i];
            content.image = images.getResourceId(i, -1);
            content.color = colors.getResourceId(i, -1);
            mPrimaryContents.add(content);
        }
        images.recycle();
        colors.recycle();
        mRecyclerView.setAdapter(new PrimarySectionsAdapter(this, mPrimaryContents));
    }

    @Override
    public void onClickCard(int position, ImageView imageView) {
        SectionDetailActivity.startActivity(this, mPrimaryContents.get(position), imageView);
    }
}
