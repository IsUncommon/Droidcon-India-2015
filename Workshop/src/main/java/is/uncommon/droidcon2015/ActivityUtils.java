package is.uncommon.droidcon2015;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;

import is.uncommon.droidcon2015.buttons.ButtonsDemoActivity;
import is.uncommon.droidcon2015.coord.CLAnchorsActivity;
import is.uncommon.droidcon2015.coord.CLExistingBehaviorActivity;
import is.uncommon.droidcon2015.coord.CLSwipeDismissBehaviorActivity;
import is.uncommon.droidcon2015.fab.FabControlsActivity;
import is.uncommon.droidcon2015.fab.FabDialogSheetMorphActivity;
import is.uncommon.droidcon2015.fab.FabSpeedDialTransitionActivity;
import is.uncommon.droidcon2015.fab.FabTabsBehaviorActivity;
import is.uncommon.droidcon2015.fab.FabToolbarTransitionActivity;
import is.uncommon.droidcon2015.steps.StepsDemoActivity;
import is.uncommon.droidcon2015.tabs.TabsCustomViewDemoActivity;
import is.uncommon.droidcon2015.tabs.TabsDemoActivity;
import is.uncommon.droidcon2015.toolbar.AppbarDemoActivity;
import is.uncommon.droidcon2015.toolbar.CollapsingToolbarDemoActivity;
import is.uncommon.droidcon2015.toolbar.ToolbarDemoActivity;

/**
 * Created by Madhu on 14/12/15.
 */
public class ActivityUtils {
    public static final void launch(Activity activity, @IdRes int resId) {
        Intent intent = null;

        if (resId == R.id.action_components_one_appcompat_tinting) {
            intent = new Intent(activity, OtherAppCompatControlsActivity.class);
        } else if (resId == R.id.action_components_one_button) {
            intent = new Intent(activity, ButtonsDemoActivity.class);
        } else if (resId == R.id.action_components_one_cards) {
            intent = new Intent(activity, CardActivity.class);
        } else if (resId == R.id.action_components_one_snackbar) {
            intent = new Intent(activity, SnackbarActivity.class);
        } else if (resId == R.id.action_components_one_fab_controls) {
            intent = new Intent(activity, FabControlsActivity.class);
        } else if (resId == R.id.action_components_one_fab_speeddial_transition) {
            intent = new Intent(activity, FabSpeedDialTransitionActivity.class);
        } else if (resId == R.id.action_components_one_fab_sheet_morph) {
            intent = new Intent(activity, FabDialogSheetMorphActivity.class);
        } else if (resId == R.id.action_components_one_fab_tabs_behavior) {
            intent = new Intent(activity, FabTabsBehaviorActivity.class);
        } else if (resId == R.id.action_components_one_fab_toolbar_transition) {
            intent = new Intent(activity, FabToolbarTransitionActivity.class);
        } else if (resId == R.id.action_layout_seams_demo) {
            intent = new Intent(activity, SeamDemoActivity.class);
        } else if (resId == R.id.action_layout_steps_demo) {
            intent = new Intent(activity, StepsDemoActivity.class);
        } else if (resId == R.id.action_palette_demo) {
            intent = new Intent(activity, PaletteActivity.class);
        } else if (resId == R.id.action_components_two_anchors) {
            intent = new Intent(activity, CLAnchorsActivity.class);
        } else if (resId == R.id.action_components_two_existing_behavior) {
            intent = new Intent(activity, CLExistingBehaviorActivity.class);
        } else if (resId == R.id.action_components_two_custom_behavior) {
            intent = new Intent(activity, StepsDemoActivity.class);
        } else if (resId == R.id.action_components_two_swipe_dismiss) {
            intent = new Intent(activity, CLSwipeDismissBehaviorActivity.class);
        } else if (resId == R.id.action_components_two_appbar) {
            intent = new Intent(activity, AppbarDemoActivity.class);
        } else if (resId == R.id.action_components_two_collapsing_toolbar) {
            intent = new Intent(activity, CollapsingToolbarDemoActivity.class);
        } else if (resId == R.id.action_components_two_toolbars) {
            intent = new Intent(activity, ToolbarDemoActivity.class);
        } else if (resId == R.id.action_components_two_navigation_view) {
            intent = new Intent(activity, NavigationViewActivity.class);
        } else if (resId == R.id.action_components_two_tabs_layout) {
            intent = new Intent(activity, TabsDemoActivity.class);
        } else if (resId == R.id.action_components_two_tabs_custom) {
            intent = new Intent(activity, TabsCustomViewDemoActivity.class);
        } else if (resId == R.id.action_components_two_text_input_layout) {
            intent = new Intent(activity, OtherAppCompatControlsActivity.class);
        }
        if (intent != null) {
            activity.startActivityForResult(intent, 0x1001);
        }
    }
}
