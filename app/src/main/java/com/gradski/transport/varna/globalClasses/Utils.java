package com.gradski.transport.varna.globalClasses;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.models.ImportantText;

/**
 * Created by lyubomir.babev on 01/06/2017.
 */

public class Utils {
    public static final String INTENT_EXTRA_IS_FOR_LIVE     = "is_for_live";
    public static final String INTENT_EXTRA_BUS_LINE_NUMBER = "bus_line_number";

    public static final int REQUEST_CODE_BUS_LINE = 0;

    public static final String GOOGLE_MAPS_API_KEY = "AIzaSyAUcpfxXBUpDBX_80ABs3ca30PrONEHCz4";

    public static final String HISTORY_VIDEO_FILE_NAME = "history.mp4";

    public static final String INTENT_EXTRA_IMAGES_LIST             = "certificates_list";
    public static final String INTENT_EXTRA_IMAGE_POSITION          = "certificate_position";
    public static final String INTENT_EXTRA_IMAGE_RESOURCE          = "certificate_resource";
    public static final String INTENT_EXTRA_IS_FOR_GALLERY          = "is_for_gallery";
    public static final String INTENT_EXTRA_START_IMAGE_POSITION    = "start_image_position";
    public static final String INTENT_EXTRA_CURRENT_IMAGE_POSITION  = "current_image_position";
    public static final String INTENT_EXTRA_ONE_WAY_BUS_SCHEDULE    = "one_way_bus_schedule";
    public static final String INTENT_EXTRA_OTHER_WAY_BUS_SCHEDULE  = "other_way_bus_schedule";

    public static final int ROUTE_CHANGE_IMAGE_TYPE_0 = 0;
    public static final int ROUTE_CHANGE_IMAGE_TYPE_1 = 1;
    public static final int ROUTE_CHANGE_IMAGE_TYPE_2 = 2;
    public static final int ROUTE_CHANGE_IMAGE_TYPE_3 = 3;
    public static final int ROUTE_CHANGE_IMAGE_TYPE_4 = 4;

    public static final int IMPORTANT_TEXT_TYPE_MESSAGE = 1;
    public static final int IMPORTANT_TEXT_TYPE_LIST    = 2;


    public static void startExpandAnimation(final View animatedView) {
        animatedView.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = animatedView.getMeasuredHeight();

        animatedView.getLayoutParams().height = 1;
        animatedView.setVisibility(View.VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                animatedView.getLayoutParams().height = interpolatedTime == 1 ? LinearLayout.LayoutParams.WRAP_CONTENT : (int)(targetHeight * interpolatedTime);
                animatedView.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        animation.setDuration((int)(targetHeight / animatedView.getContext().getResources().getDisplayMetrics().density));
        animatedView.startAnimation(animation);
    }

    public static void startCollapseAnimation(final View animatedView) {
        final int initialHeight = animatedView.getMeasuredHeight();

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1){
                    animatedView.setVisibility(View.GONE);
                } else{
                    animatedView.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    animatedView.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        animation.setDuration((int)(initialHeight / animatedView.getContext().getResources().getDisplayMetrics().density));
        animatedView.startAnimation(animation);
    }

    public static int getRouteChangeImageResource(int imageType) {
        switch (imageType) {
            case ROUTE_CHANGE_IMAGE_TYPE_0:
                return R.drawable.route_change_type_1;
            case ROUTE_CHANGE_IMAGE_TYPE_1:
                return R.drawable.route_change_type_1;
            case ROUTE_CHANGE_IMAGE_TYPE_2:
                return R.drawable.route_change_type_1;
            case ROUTE_CHANGE_IMAGE_TYPE_3:
                return R.drawable.route_change_type_1;
            case ROUTE_CHANGE_IMAGE_TYPE_4:
                return R.drawable.route_change_type_1;
            default:
                return R.drawable.route_change_type_1;
        }
    }
}
