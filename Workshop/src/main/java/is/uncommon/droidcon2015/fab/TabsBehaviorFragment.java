package is.uncommon.droidcon2015.fab;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import is.uncommon.droidcon2015.R;
import is.uncommon.droidcon2015.utils.HtmlUtils;

/**
 * Created by Madhu on 04/12/15.
 */
public class TabsBehaviorFragment extends Fragment {
    private static final String TAG = TabsBehaviorFragment.class.getSimpleName();
    private static final String ARGS_KEY_TEXT = TAG + ".TEXT";

    private View mRootView;
    @Bind(R.id.text) TextView mTextView;
    private String mText;

    public static final TabsBehaviorFragment getInstance(String text) {
        TabsBehaviorFragment frag = new TabsBehaviorFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_KEY_TEXT, text);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_tabs_behavior, null, false);
        ButterKnife.bind(this, mRootView);
        mText = getArguments().getString(ARGS_KEY_TEXT);
        HtmlUtils.setHtmlText(mTextView, mText);
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
