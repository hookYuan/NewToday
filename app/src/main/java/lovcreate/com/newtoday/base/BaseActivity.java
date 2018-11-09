package lovcreate.com.newtoday.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import lovcreate.com.newtoday.application.BaseApplication;

/**
 * 作者：yuanYe创建于2016/9/19
 * QQ：962851730
 */
public class BaseActivity extends AppCompatActivity {
    public BaseApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (BaseApplication) getApplication();
        application.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        application.getActivityList().remove(this);
        super.onDestroy();
    }
}
