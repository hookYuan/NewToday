package lovcreate.com.newtoday.ui.one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lovcreate.com.newtoday.R;
import lovcreate.com.newtoday.base.BaseActivity;

/**
 * 新闻照片浏览界面
 */
public class ImageInfoActivity extends BaseActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_info);
        webView = (WebView)findViewById(R.id.webView);
//        https://h5.m.taobao.com/#index

        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);

        webView.loadUrl("https://h5.m.taobao.com/#index");
        //设置Web视图
        webView.setWebViewClient(new webViewClient());
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
