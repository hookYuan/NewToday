package lovcreate.com.newtoday.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import lovcreate.com.newtoday.R;
import lovcreate.com.newtoday.adapter.MediaAdapter;
import lovcreate.com.newtoday.adapter.NewsAdapter;
import lovcreate.com.newtoday.adapter.VideoAdapter;
import lovcreate.com.newtoday.base.BaseFragment;
import lovcreate.com.newtoday.bean.MediaBean;
import lovcreate.com.newtoday.header.MeiTuanHeader;
import lovcreate.com.newtoday.ui.one.ChannelActivity;
/**
 * 作者：yuanYe创建于2016/9/19
 * QQ：962851730
 */
public class TwoFragment extends BaseFragment {
    private View view;
    private ArrayList<String> titleList;
    private ArrayList<View> fragmentList;
    private RecyclerView listView;
    private TabLayout tl_tabLayout;
    private ViewPager vp_viewpager;
    private ArrayList<String> data;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (View)LayoutInflater.from(getContext()).inflate(R.layout.two_frag_main,null);
        setStatusBar();

        initData();
        initTabLayout();
        initView();
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

    private void initData() {
        data=new ArrayList();
        MediaBean bean = new MediaBean();
        data.add("/mnt/sdcard/1.mp4");
        data.add("/mnt/sdcard/2.mp4");
        data.add("/mnt/sdcard/1.mp4");
        data.add("/mnt/sdcard/2.mp4");
//        MediaBean bean2 = new MediaBean();
//        data.add(bean2);

        titleList = new ArrayList();
        titleList.add("推荐");
        titleList.add("逗比剧");
        titleList.add("音乐台");
        titleList.add("看天下");
        titleList.add("相声小品");
        titleList.add("游戏");
        titleList.add("原创精选");
        titleList.add("萌萌哒");
        titleList.add("最娱乐");
        titleList.add("爱生活");
        titleList.add("掠影");
        titleList.add("开眼");
        titleList.add("再看一遍");
        titleList.add("火山直播");

        fragmentList = new ArrayList();
        for(int i = 0;i<titleList.size();i++){
            addView(); //根据titleList的个数添加view的页数
        }


    }

    public void addView(){
        View item = LayoutInflater.from(getContext()).inflate(R.layout.two_frag_item,null);
        final PtrFrameLayout storeHousePtrFrame = (PtrFrameLayout) item.findViewById(R.id.store_house_ptr_frame);
        MeiTuanHeader header = new MeiTuanHeader(getActivity());
        storeHousePtrFrame.setHeaderView(header);
        storeHousePtrFrame.addPtrUIHandler(header);
        storeHousePtrFrame.setPtrHandler(new PtrHandler() {
            /**
             * 在下拉时执行
             * @param frame
             * @param content
             * @param header
             * @return
             */
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                Log.i("yuanye","1111111111");
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            /**
             * 刷新时开始执行
             * @param frame
             */
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Log.i("yuanye","333333333333333");
                storeHousePtrFrame.postDelayed(new Runnable() {
                    /**
                     * 刷新完成时执行
                     */
                    @Override
                    public void run() {
                        storeHousePtrFrame.refreshComplete();
                        Log.i("yuanye","2222222222");
                    }
                }, 2000);
            }
        });
        fragmentList.add(item);
        listView = (RecyclerView) item.findViewById(R.id.listView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);

        VideoAdapter adapter = new VideoAdapter(getActivity(),data);
        listView.setAdapter(adapter);
    }

    private void initTabLayout() {
        tl_tabLayout = (TabLayout)view.findViewById(R.id.tl_tabLayout);
        tl_tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //添加标题
        for (int i = 0;i<titleList.size();i++){
            tl_tabLayout.addTab(tl_tabLayout.newTab().setText(titleList.get(i)));
        }

    }

    private void initView() {
        vp_viewpager = (ViewPager)view.findViewById(R.id.vp_viewpager);
        vp_viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(fragmentList.get(position));//添加页卡
                return fragmentList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(fragmentList.get(position));//删除页卡
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);//页卡标题
            }
        });
        tl_tabLayout.setupWithViewPager(vp_viewpager);

        MyListener listener = new MyListener();
    }

    public class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_add: //点击添加按钮
                    Intent intent_channel = new  Intent(getContext(), ChannelActivity.class);
                    getActivity().startActivity(intent_channel);
                    getActivity().overridePendingTransition(R.anim.slide_left_in,R.anim.slide_left_out);
                    break;
            }
        }
    }
}
