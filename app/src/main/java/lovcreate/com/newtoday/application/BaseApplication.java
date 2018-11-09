package lovcreate.com.newtoday.application;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yuanYe创建于2016/9/19
 * QQ：962851730
 */
public class BaseApplication extends Application {

    private static BaseApplication application;
    private List<Activity> activityList = new ArrayList<>();;

    @Override
    public void onCreate() {
        super.onCreate();
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        // 注册crashHandler
//        crashHandler.init(getApplicationContext());
    }

    public static BaseApplication getApplication(){
        if(application==null){
            application = new BaseApplication();
        }
        return application;
    }

    public void removeActivity(Activity activity){
        if(activityList==null){
            activityList = new ArrayList<>();
        }
    }

    public List<Activity> getActivityList(){
        return activityList;
    }

    public void addActivity(Activity activity){
        if(activityList==null){
            activityList = new ArrayList<>();
        }
        activityList.add(activity);
    }

    public void closeAllActivity(){
        for (Activity activity :activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
