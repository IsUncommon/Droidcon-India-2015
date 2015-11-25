package is.uncommon.droidcon2015.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.models.PrimaryContent;

public class PrimarySectionsAdapter extends RecyclerView.Adapter<PrimarySectionsAdapter.ViewHolder> {

    private List<PrimaryContent> mContents = null;

    public PrimarySectionsAdapter(List<PrimaryContent> contents) {
        this.mContents = contents;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.primarysections_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mContents.get(position));
    }

    @Override
    public int getItemCount() {
        return mContents.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_image) ImageView imageView;
        @Bind(R.id.card_title) TextView title;
        @Bind(R.id.card_summary) TextView summary;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(PrimaryContent primaryContent) {
            title.setText(primaryContent.sectionName);
            summary.setText(primaryContent.summary);
            imageView.setImageResource(R.drawable.temp_image);
        }
    }
}
