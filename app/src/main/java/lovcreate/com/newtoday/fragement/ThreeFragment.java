package lovcreate.com.newtoday.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import lovcreate.com.newtoday.R;
import lovcreate.com.newtoday.base.BaseFragment;

/**
 * 作者：yuanYe创建于2016/9/19
 * QQ：962851730
 */
public class ThreeFragment extends BaseFragment {
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.three_frag_main,null);
        setStatusBar();
        return view;
    }

    public void setStatusBar(){
        /**
         * 获取状态栏高度——方法1
         * */
        int statusBarHeight1 = 0;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        View status_bar = view.findViewById(R.id.status_bar);
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) status_bar.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height = statusBarHeight1;// 控件的高强制设成20
        linearParams.width = LinearLayout.LayoutParams.MATCH_PARENT;// 控件的宽强制设成30
        status_bar.setLayoutParams(linearParams); //使设置好的布局参数应用到控件</pre>
    }
}
