package com.yjbo.yjboandroidmodule.util.swipeUtil;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.yjbo.yjboandroidmodule.R;


/**
 * Created by Eric on 15/3/3.
 */
public class SwipeBackActivity extends AppCompatActivity implements SwipeBackLayout.SwipeBackListener {

    private SwipeBackLayout swipeBackLayout;
    private ImageView ivShadow;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(getContainer());
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        swipeBackLayout.addView(view);
    }

    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setOnSwipeBackListener(this);
        ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(getResources().getColor(R.color.white));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        return container;
    }

    public void setDragEdge(SwipeBackLayout.DragEdge dragEdge) {
        swipeBackLayout.setDragEdge(dragEdge);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackLayout;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
        /*最小版本11才有效果*/
        ivShadow.setAlpha(1 - fractionScreen);
    }

}
