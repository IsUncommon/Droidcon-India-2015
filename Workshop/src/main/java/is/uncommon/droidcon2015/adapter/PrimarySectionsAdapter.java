package is.uncommon.droidcon2015.adapter;

import android.support.v4.content.ContextCompat;
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

    public interface ClickInterface {
        void onClickCard(int position, ImageView imageView);
    }


    private ClickInterface mClickInterface;
    private List<PrimaryContent> mContents;

    public PrimarySectionsAdapter(ClickInterface clickInterface, List<PrimaryContent> contents) {
        this.mClickInterface = clickInterface;
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

        @Bind(R.id.iv_card_image) ImageView imageView;
        @Bind(R.id.tv_card_title) TextView title;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickInterface.onClickCard(getLayoutPosition(), imageView);
                }
            });
        }

        public void bindData(PrimaryContent primaryContent) {
            title.setText(primaryContent.sectionName);
            title.setBackgroundColor(ContextCompat.getColor(title.getContext(), primaryContent.color));
            imageView.setImageResource(primaryContent.image);
        }
    }
}
