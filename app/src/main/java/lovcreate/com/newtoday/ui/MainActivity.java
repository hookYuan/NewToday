package lovcreate.com.newtoday.ui;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;
import lovcreate.com.newtoday.R;
import lovcreate.com.newtoday.base.BaseActivity;
import lovcreate.com.newtoday.fragement.FourFragment;
import lovcreate.com.newtoday.fragement.OneFragment;
import lovcreate.com.newtoday.fragement.ThreeFragment;
import lovcreate.com.newtoday.fragement.TwoFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private ImageView img1,img2,img3,img4;
    private TextView tv1,tv2,tv3,tv4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        initView();
        init();
    }

    private void initView() {
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
    }

    private void init() {



        img1.setImageResource(R.drawable.b_newhome_tabbar_press);
        tv1.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.home_icon));
        // 初始化默认显示的界面
        if (oneFragment == null) {
            oneFragment = new OneFragment();
            addFragment(oneFragment);
            showFragment(oneFragment);
        } else {
            showFragment(oneFragment);
        }
    }

    /**
     * 添加Fragment
     **/
    public void addFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.relativeLayout, fragment);
        ft.commit();
    }

    /**
     * 显示Fragment
     **/
    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        // 判断页面是否已经创建，如果已经创建，那么就隐藏掉
        if (oneFragment != null) {
            ft.hide(oneFragment);
        }
        if (twoFragment != null) {
            ft.hide(twoFragment);
        }
        if (threeFragment != null) {
            ft.hide(threeFragment);
        }
        if (fourFragment != null) {
            ft.hide(fourFragment);
        }
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll1:
                img1.setImageResource(R.drawable.b_newhome_tabbar_press);
                tv1.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.home_icon));

                img2.setImageResource(R.drawable.b_newdiscover_tabbar);
                tv2.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));

                img3.setImageResource(R.drawable.b_newcare_tabbar);
                tv3.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));

                img4.setImageResource(R.drawable.b_newmine_tabbar);
                tv4.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));

                // 主界面
                if ( oneFragment== null) {
                    oneFragment = new OneFragment();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    addFragment(oneFragment);
                    showFragment(oneFragment);
                } else {
                    if (oneFragment.isHidden()) {
                        showFragment(oneFragment);
                    }
                }
                break;
            case R.id.ll2:

                img2.setImageResource(R.drawable.b_newdiscover_tabbar_press);
                tv2.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.home_icon));

                img1.setImageResource(R.drawable.b_newhome_tabbar);
                tv1.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));

                img3.setImageResource(R.drawable.b_newcare_tabbar);
                tv3.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));

                img4.setImageResource(R.drawable.b_newmine_tabbar);
                tv4.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                // 任务
                if ( twoFragment== null) {
                    twoFragment = new TwoFragment();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    addFragment(twoFragment);
                    showFragment(twoFragment);
                } else {
                    if (twoFragment.isHidden()) {
                        showFragment(twoFragment);
                    }
                }

                break;
            case R.id.ll3:

                img3.setImageResource(R.drawable.b_newcare_tabbar_press);
                tv3.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.home_icon));

                img1.setImageResource(R.drawable.b_newhome_tabbar);
                tv1.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));


                img2.setImageResource(R.drawable.b_newdiscover_tabbar);
                tv2.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));


                img4.setImageResource(R.drawable.b_newmine_tabbar);
                tv4.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                // 工作台
                if ( threeFragment== null) {
                    threeFragment = new ThreeFragment();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    addFragment(threeFragment);
                    showFragment(threeFragment);
                } else {
                    if (threeFragment.isHidden()) {
                        showFragment(threeFragment);
                    }
                }

                break;
            case R.id.ll4:

                img4.setImageResource(R.drawable.b_newmine_tabbar_press);
                tv4.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.home_icon));

                img1.setImageResource(R.drawable.b_newhome_tabbar);
                tv1.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));


                img2.setImageResource(R.drawable.b_newdiscover_tabbar);
                tv2.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));

                img3.setImageResource(R.drawable.b_newcare_tabbar);
                tv3.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));

                if ( fourFragment== null) {
                    fourFragment = new FourFragment();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    addFragment(fourFragment);
                    showFragment(fourFragment);
                } else {
                    if (fourFragment.isHidden()) {
                        showFragment(fourFragment);
                    }
                }
                break;

        }
    }



    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
        }
    }

}
