package lovcreate.com.newtoday.header;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.indicator.PtrTensionIndicator;
import lovcreate.com.newtoday.R;

/**
 * 作者：yuanYe创建于2016/9/21
 * QQ：962851730
 */

public class MeiTuanHeader extends FrameLayout implements PtrUIHandler {
    TextView headerImg;
    private PtrTensionIndicator mPtrTensionIndicator;
    PtrFrameLayout mPtrFrameLayout;
    View view;
    private TextView tv_update;

    public MeiTuanHeader(Context context) {
        super(context);
        init(context);
    }

    public MeiTuanHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MeiTuanHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.head_pullto, this, false);
        addView(view);
        headerImg = (TextView) view.findViewById(R.id.tv_refresh);
        tv_update = (TextView) view.findViewById(R.id.tv_update);
    }
    public void setUp(PtrFrameLayout ptrFrameLayout) {
        mPtrFrameLayout = ptrFrameLayout;
        mPtrTensionIndicator = new PtrTensionIndicator();
        mPtrFrameLayout.setPtrIndicator(mPtrTensionIndicator);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }


    //开始刷新的时候
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        AnimationDrawable animationDrawable;
        headerImg.setText("正在努力加载");
        headerImg.setVisibility(VISIBLE);
        tv_update.setVisibility(GONE);
        new Handler().postDelayed(new Runnable(){

            public void run() {
                headerImg.setVisibility(GONE);
                tv_update.setText("今日头条推荐引擎有8条更新");
                tv_update.setVisibility(VISIBLE);
            }
        }, 1500);
    }

    //刷新完成时提示
    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        headerImg.clearAnimation();
        headerImg.setVisibility(GONE);
        tv_update.setText("今日头条推荐引擎有8条更新");
        tv_update.setVisibility(VISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();

//        Log.i("yuanye","mOffsetToRefresh="+mOffsetToRefresh);
//        Log.i("yuanye","currentPos="+currentPos);

        if (currentPos < mOffsetToRefresh ) {
            //未到达刷新线
            if (status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                headerImg.setVisibility(VISIBLE);
                tv_update.setVisibility(GONE);
                headerImg.setText("下拉推荐");
                headerImg.setScaleX((float) currentPos / mOffsetToRefresh);
                headerImg.setScaleY((float) currentPos / mOffsetToRefresh);
            }
        } else if (currentPos > mOffsetToRefresh ) {
            //到达或超过刷新线
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                AnimationDrawable animationDrawable;
                headerImg.setText("松开推荐");
                headerImg.setVisibility(VISIBLE);
                tv_update.setVisibility(GONE);
//                animationDrawable= (AnimationDrawable) headerImg.getDrawable();
//                animationDrawable.start();
            }
        }
    }
}