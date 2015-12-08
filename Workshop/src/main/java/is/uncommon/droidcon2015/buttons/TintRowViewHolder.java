package is.uncommon.droidcon2015.buttons;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import is.uncommon.droidcon2015.R;

public class TintRowViewHolder implements View.OnClickListener {
    private static final int[] COLORS = new int[] {
            Color.parseColor("#9C27B0"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#E91E63"),
            Color.parseColor("#FF9800")
    };

    private List<CardView> mCards = new ArrayList<>();
    private List<ImageView> mCheckmarks = new ArrayList<>();
    private TintRowSelectedListener onTintRowSelectedListener = null;

    public static TintRowViewHolder getInstance(LinearLayout layoutView) {
        TintRowViewHolder viewHolder = new TintRowViewHolder();

        for (int i = 0; i < layoutView.getChildCount(); i++) {
            View v = layoutView.getChildAt(i);
            CardView iv = (CardView) v;
            ImageView checkMark = (ImageView) v.findViewById(R.id.tint_row_check);
            viewHolder.mCards.add(iv);
            viewHolder.mCheckmarks.add(checkMark);
            iv.setCardBackgroundColor(COLORS[i]);
            if (i != 0) {
                checkMark.setVisibility(View.GONE);
            }
            v.setOnClickListener(viewHolder);
        }
        return viewHolder;
    }

    public void setOnTintRowSelectedListener(TintRowSelectedListener listener) {
        this.onTintRowSelectedListener = listener;
    }

    public int getSelectedColor() {
        for (int i = 0; i < mCheckmarks.size(); i++) {
            if (mCheckmarks.get(i).getVisibility() == View.VISIBLE) {
                return COLORS[i];
            }
        }
        return COLORS[0];
    }

    @Override
    public void onClick(View view) {
        int selected = 0;
        for (int i = 0; i < mCards.size(); i++) {
            if (mCards.get(i) == view) {
                setItemSelected(i);
                selected = i;
                break;
            }
        }
        onTintRowSelectedListener.onTintSelected(COLORS[selected]);
    }

    private void setItemSelected(int itemSelected) {
        for (int i = 0; i < mCheckmarks.size(); i++) {
            if (i == itemSelected) {
                mCheckmarks.get(i).setVisibility(View.VISIBLE);
            } else {
                mCheckmarks.get(i).setVisibility(View.GONE);
            }
        }
    }

    public interface TintRowSelectedListener {
        void onTintSelected(int color);
    }
}
