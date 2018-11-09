package lovcreate.com.newtoday.fragement;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import lovcreate.com.newtoday.R;
import lovcreate.com.newtoday.base.BaseFragment;

/**
 * 作者：yuanYe创建于2016/9/19
 * QQ：962851730
 */
public class FourFragment extends BaseFragment {

    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.four_frag_main,null);


        return view;
    }
}
