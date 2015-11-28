package is.uncommon.droidcon2015.utils;

import android.graphics.Color;
import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * A util class that provides spanned and trimmed html text from html content
 * Created by Madhu on 27/11/15.
 */
public final class HtmlUtils {

    private HtmlUtils() {

    }

    /**
     * Trims trailing whitespace. Removes any of these characters:
     * 0009, HORIZONTAL TABULATION
     * 000A, LINE FEED
     * 000B, VERTICAL TABULATION
     * 000C, FORM FEED
     * 000D, CARRIAGE RETURN
     * 001C, FILE SEPARATOR
     * 001D, GROUP SEPARATOR
     * 001E, RECORD SEPARATOR
     * 001F, UNIT SEPARATOR
     *
     * @return "" if source is null, otherwise string with all trailing whitespace removed
     */
    private static Spanned trimWhitespace(Spanned source) {

        if (TextUtils.isEmpty(source)) {
            return new SpannedString("");
        }

        int trailingIndex = source.length();
        // loop back to the first non-whitespace character
        while (--trailingIndex >= 0 && Character.isWhitespace(source.charAt(trailingIndex))) {
        }

        int leadingIndex = -1;
        // loop back to the first non-whitespace character
        while (++leadingIndex < source.length() && Character.isWhitespace(source.charAt(leadingIndex))) {
        }

        if (leadingIndex >= 0 && leadingIndex < source.length()
                && trailingIndex >= 0 && trailingIndex < source.length()
                && leadingIndex <= trailingIndex) {
            return ((Spanned) source.subSequence(leadingIndex, trailingIndex + 1));
        }

        return new SpannedString("");
    }

    /**
     * Used by text views to convert text to html
     *
     * @param text text that needs to be converted to html span
     * @return trimmed html span
     */
    public static Spanned getHtml(String text) {
        return trimWhitespace(Html.fromHtml(text));
    }

    /**
     * Used by text views to convert text to html with special handling of ul and ol items
     *
     * @param text         text that needs to be converted to html span
     * @param bulletRadius radius of the bullet in pixels
     * @param bulletColor  color of the bullet in pixels
     * @return trimmed html span
     */
    public static Spanned getHtml(String text, int bulletRadius, int bulletColor) {
        return trimWhitespace(Html.fromHtml(text, null, new HtmlListsTagHandler(bulletRadius, bulletColor)));
    }

    public static void setHtmlText(TextView tv, String plainHtml) {
        tv.setText(HtmlUtils.getHtml(plainHtml, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
                tv.getResources().getDisplayMetrics()), Color.parseColor("#c3c3c3")));
    }
}
