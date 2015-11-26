package is.uncommon.droidcon2015;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        String[] headings = getResources().getStringArray(R.array.primary_content_headings);
        String[] summaries = getResources().getStringArray(R.array.primary_content_description);
        List<PrimaryContent> contents = new ArrayList<>();
        for (int i = 0; i < headings.length; i++) {
            PrimaryContent content = new PrimaryContent();
            content.sectionName = headings[i];
            content.summary = summaries[i];
            contents.add(content);
        }
        mRecyclerView.setAdapter(new PrimarySectionsAdapter(this, contents));
    }


    @Override
    public void onClickCard(int position) {
        startActivity(new Intent(this, SectionDetailActivity.class));
    }
}
