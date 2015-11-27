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
import is.uncommon.droidcon2015.utiils.HtmlUtils;

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
        private static final int MIN_LINES = 4;
        private static final int MAX_LINES = 200;

        @Bind(R.id.card_image)
        ImageView imageView;
        @Bind(R.id.card_title)
        TextView title;
        @Bind(R.id.card_summary)
        TextView summary;
        @Bind(R.id.card_action_more)
        Button more;


        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickInterface.onClickCard(getLayoutPosition());
                }
            });

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (more.getText().equals(itemView.getResources().getString(R.string.more))) {
                        expandSummary();
                    } else {
                        contractSummary();
                    }
                }
            });
        }

        private void contractSummary() {
            final ValueAnimator animator = ValueAnimator.ofFloat(1, 0);
            final int toLineNum = MIN_LINES;
            final int currLine = summary.getLayout().getLineCount();
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration(300);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float val = (float) valueAnimator.getAnimatedValue();
                    int numLines = Math.round(toLineNum + (val) * (currLine - toLineNum));
                    summary.setMaxLines(numLines);
                }
            });
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    updateMoreText();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    updateMoreText();
                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        }

        private void expandSummary() {
            final ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(300);
            final int currLine = summary.getLayout().getLineCount();
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float val = (float) valueAnimator.getAnimatedValue();
                    int numLines = Math.round(currLine + (val) * (MAX_LINES - currLine));
                    summary.setMaxLines(numLines);
                    if (!summaryHasEllipsis()) {
                        animator.cancel();
                    }
                }
            });
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    updateMoreText();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    updateMoreText();
                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        }

        private boolean summaryHasEllipsis() {
            Layout l = summary.getLayout();
            if (l != null) {
                int lines = l.getLineCount();
                if (lines > 0) {
                    if (l.getEllipsisCount(lines - 1) > 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        private void updateMoreText() {
            if (!summaryHasEllipsis()) {
                more.setText(R.string.less);
            } else {
                more.setText(R.string.more);
            }
        }

        public void bindData(PrimaryContent primaryContent) {
            title.setText(primaryContent.sectionName);
            summary.setText(HtmlUtils.getHtml(primaryContent.summary, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
                    itemView.getResources().getDisplayMetrics()), Color.parseColor("#c3c3c3")));
            summary.setMaxLines(MIN_LINES);
            more.setText(R.string.more);
            imageView.setImageResource(R.drawable.temp_image);
        }
    }
}
