package lovcreate.com.newtoday.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import lovcreate.com.newtoday.R;
import lovcreate.com.newtoday.adapter.NewsAdapter;
import lovcreate.com.newtoday.application.BaseApplication;
import lovcreate.com.newtoday.base.BaseFragment;
import lovcreate.com.newtoday.bean.ChannelItem;
import lovcreate.com.newtoday.bean.NewsBean;
import lovcreate.com.newtoday.header.MeiTuanHeader;
import lovcreate.com.newtoday.ui.one.ChannelActivity;
import lovcreate.com.newtoday.ui.one.NewsInfoActivity;

/**
 * 作者：yuanYe创建于2016/9/19
 * QQ：962851730
 */
public class OneFragment extends BaseFragment {


    private TabLayout tl_tabLayout;
    private ViewPager vp_viewpager;
    private View view;
    private List<View> fragmentList;
    private ArrayList<String> titleList;
    private TextView tv_add;
    public final static int CHANNELREQUEST = 1;
    public final static int CHANNELRESULT = 10;
    /**
     * 用户选择的新闻分类列表
     */
    private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    private ListView listView;
    private ArrayList<NewsBean> news;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (View) LayoutInflater.from(getContext()).inflate(R.layout.one_frag_main, null);
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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initData() {

        titleList = new ArrayList();
        titleList.add("推荐");
        titleList.add("热点");
        titleList.add("三农");
        titleList.add("星座");
        titleList.add("军事");
        titleList.add("科技");
        titleList.add("政务");
        titleList.add("情感");
        titleList.add("火山直播");
        titleList.add("动漫");


        /**
         * 构造列表展示的假数据
         */
        news = new ArrayList<NewsBean>();
        NewsBean bean = new NewsBean();  //文本测试type_1
        bean.setType(1);
        bean.setIs_hot(true);
        bean.setTitle("城镇医疗保险和农村合作医疗的区别是什么");
        bean.setComment_count(23);
        bean.setMechanism_name("中国");
        bean.setComment_time(1350354229299L);
        news.add(bean);

        NewsBean bean2 = new NewsBean();  //三张图片测试type_2
        bean2.setType(2);
        bean2.setIs_hot(true);
        bean2.setTitle("城镇医疗保险和农村合作医疗的区别是什么");
        bean2.setComment_count(23);
        bean2.setMechanism_name("中国");
        bean2.setComment_time(1350354229299L);
        bean2.setUrl_content_01(R.drawable.news_01);
        bean2.setUrl_content_02(R.drawable.news_01);
        bean2.setUrl_content_03(R.drawable.news_01);
        news.add(bean2);

        NewsBean bean3 = new NewsBean();  //1张图片测试type_3
        bean3.setType(3);
        bean3.setIs_hot(true);
        bean3.setTitle("城镇医疗保险和农村合作医疗的区别是什么");
        bean3.setComment_count(23);
        bean3.setMechanism_name("中国");
        bean3.setComment_time(1350354229299L);
        bean3.setUrl_content_01(R.drawable.news_01);
        news.add(bean3);

        NewsBean bean4 = new NewsBean();  //1张图片测试type_4
        bean4.setType(4);
        bean4.setIs_hot(true);
        bean4.setTitle("城镇医疗保险和农村合作医疗的区别是什么");
        bean4.setComment_count(23);
        bean4.setMechanism_name("中国");
        bean4.setComment_time(1350354229299L);
        bean4.setUrl_content_01(R.drawable.news_01);
        news.add(bean4);
        news.add(bean);
        news.add(bean4);
        news.add(bean2);
        news.add(bean3);
        news.add(bean2);
        news.add(bean2);
        news.add(bean4);
        news.add(bean);


        /**
         * 构造新闻滑动页面
         */
        fragmentList = new ArrayList();
        for (int i = 0; i < titleList.size(); i++) {
            addView(); //根据titleList的个数添加view的页数
        }
    }

    private void initTabLayout() {
        tl_tabLayout = (TabLayout) view.findViewById(R.id.tl_tabLayout);
        tl_tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //添加标题
        for (int i = 0; i < titleList.size(); i++) {
            tl_tabLayout.addTab(tl_tabLayout.newTab().setText(titleList.get(i)));
        }

    }

    public void addView() {
        View item = LayoutInflater.from(getContext()).inflate(R.layout.one_frag_item, null);
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
                Log.i("yuanye", "1111111111");
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            /**
             * 刷新时开始执行
             * @param frame
             */
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Log.i("yuanye", "333333333333333");
                storeHousePtrFrame.postDelayed(new Runnable() {
                    /**
                     * 刷新完成时执行
                     */
                    @Override
                    public void run() {
                        storeHousePtrFrame.refreshComplete();
                        Log.i("yuanye", "2222222222");
                    }
                }, 2000);
            }
        });
        fragmentList.add(item);
        listView = (ListView) item.findViewById(R.id.listView);
        if(news.size()!=0){
            ImageView iv_tou_tiao = (ImageView) item.findViewById(R.id.iv_tou_tiao);
            iv_tou_tiao.setVisibility(View.GONE);
        }
        NewsAdapter adapter = new NewsAdapter(news);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), NewsInfoActivity.class));
            }
        });
    }


    private void initView() {
        vp_viewpager = (ViewPager) view.findViewById(R.id.vp_viewpager);
        vp_viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
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
        //添加按钮
        tv_add = (TextView) view.findViewById(R.id.tv_add);
        tv_add.setOnClickListener(listener);

    }

    public class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_add: //点击添加按钮
                    Intent intent_channel = new Intent(getContext(), ChannelActivity.class);
                    getActivity().startActivity(intent_channel);
                    getActivity().overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
                    break;
            }
        }
    }

}

























