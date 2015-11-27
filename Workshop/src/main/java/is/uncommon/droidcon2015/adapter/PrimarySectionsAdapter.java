package is.uncommon.droidcon2015.adapter;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.models.PrimaryContent;
import is.uncommon.droidcon2015.utils.HtmlUtils;

public class PrimarySectionsAdapter extends RecyclerView.Adapter<PrimarySectionsAdapter.ViewHolder> {

    public interface ClickInterface {
        void onClickCard(int position);
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
        @Bind(R.id.card_image)
        ImageView imageView;
        @Bind(R.id.card_title)
        TextView title;
        @Bind(R.id.card_summary)
        TextView summary;


        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickInterface.onClickCard(getLayoutPosition());
                }
            });
        }

        public void bindData(PrimaryContent primaryContent) {
            title.setText(primaryContent.sectionName);
            summary.setText(HtmlUtils.getHtml(primaryContent.summary, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
                    itemView.getResources().getDisplayMetrics()), Color.parseColor("#c3c3c3")));
        }
    }
}
