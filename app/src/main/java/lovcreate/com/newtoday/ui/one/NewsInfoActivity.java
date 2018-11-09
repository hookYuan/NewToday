package lovcreate.com.newtoday.ui.one;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import lovcreate.com.newtoday.R;
import lovcreate.com.newtoday.adapter.MoreBaseAdapter;
import lovcreate.com.newtoday.base.BaseActivity;
import lovcreate.com.newtoday.witget.CircleTransform;

/**
 * 新闻展示详情页面
 */
public class NewsInfoActivity extends BaseActivity {

    ListView listView;
    private View head;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK); //更改标题栏的颜色
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_news_info);
        setStatusBar();
        initData();
        initView();

    }

//    private void initView() {
//        WebView webview = (WebView)findViewById(R.id.webView);
//        WebSettings webSettings = webview.getSettings();
//        //设置WebView属性，能够执行Javascript脚本
//        webSettings.setJavaScriptEnabled(true);
//        //设置可以访问文件
//        webSettings.setAllowFileAccess(true);
//        //设置支持缩放
//        webSettings.setBuiltInZoomControls(true);
//
//        webview.loadUrl("http://news.timedg.com/2016-09/22/20473007.shtml");
//        //设置Web视图
//        webview.setWebViewClient(new webViewClient());
//    }

    /**
     * 初始化数据
     */
    private void initData() {
        data = new ArrayList<String>();
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
        data.add("213412351");
    }

    private void initView() {
        head = LayoutInflater.from(this).inflate(R.layout.head_news_info,null);
        WebView webview = (WebView) head.findViewById(R.id.webView);
        WebSettings webSettings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(this);
        webview.addJavascriptInterface(javaScriptInterface,"JSInterface");
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
//        webview.loadUrl("http://news.timedg.com/2016-09/22/20473007.shtml");
        webview.loadUrl("file:///android_asset/NewsInfo/index.html");
//        webview.loadUrl("http://toutiao.com/i6333535839762530818/");
        //设置Web视图
        webview.setWebViewClient(new webViewClient());
        listView = (ListView)findViewById(R.id.listView);
        listView.addHeaderView(head);
        listView.setAdapter(new MoreBaseAdapter<String>(data,R.layout.item_news_info_comment) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                ImageView imageView = holder.getView(R.id.iv_ImageView);
                Glide.with(NewsInfoActivity.this).load(R.drawable.news_01).transform(new CircleTransform(NewsInfoActivity.this)).into(imageView);
            }
        });
    }

    /**
     * 写接口类，给Javascript调用
     */
    public class JavaScriptInterface{
        Context context;
        public JavaScriptInterface(Context context){
            this.context = context;
        }

        @JavascriptInterface
        public void openActivity(String[] urls){
            //跳转到图片展示页面
            Intent intent = new Intent(NewsInfoActivity.this,ImageInfoActivity.class);
            intent.putExtra("img_url",urls);
            NewsInfoActivity.this.startActivity(intent);
        }
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
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
        View status_bar = findViewById(R.id.status_bar);
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) status_bar.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height = statusBarHeight1;// 控件的高强制设成20
        linearParams.width = LinearLayout.LayoutParams.MATCH_PARENT;// 控件的宽强制设成30
        status_bar.setLayoutParams(linearParams); //使设置好的布局参数应用到控件</pre>
    }
}
