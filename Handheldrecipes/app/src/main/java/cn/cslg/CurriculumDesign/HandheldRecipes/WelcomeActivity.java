package cn.cslg.CurriculumDesign.HandheldRecipes;

import java.util.List;
import java.util.Timer;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView.ScaleType;
import android.view.ViewGroup.LayoutParams;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import cn.cslg.CurriculumDesign.HandheldRecipes.Adapter.MyAdapter;

/**
 * Created by Administrator on 2017/6/2.
 * LastEdit: 2017-6-3
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class WelcomeActivity extends Activity {
    private ViewPager viewpager;
    private LinearLayout layout;
    private int[] imgs = {R.drawable.welcomebackground_1, R.drawable.welcomebackground_2};
    private List<View> list;                                                                       //用来保存要在viewpager控件中展示的页面的
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        layout = (LinearLayout) findViewById(R.id.layout);
        list = new ArrayList<View>();
        //为viewpager控件添加要展示的页面
        for(int i = 0; i < imgs.length; i++){
            ImageView iv = new ImageView(this);
            LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(params);
            iv.setScaleType(ScaleType.FIT_CENTER);
            iv.setImageResource(imgs[i]);
            list.add(iv);
        }
        //添加特殊页面

        viewpager.setAdapter(new MyAdapter(list));
        //创建导航点
        for(int i = 0; i < list.size(); i++){
            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            iv.setLayoutParams(params);
            if(i == 0) {
                iv.setImageResource(R.drawable.page_on);
            } else {
                iv.setImageResource(R.drawable.page_off);
            }
            params.setMargins(10, 0, 10, 0);
            layout.addView(iv,params);
        }

        //点亮对应页面的导航点
        viewpager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                for(int i = 0; i < list.size(); i++) {
                    ImageView iv = (ImageView) layout.getChildAt(i);
                    if(i == arg0) {
                        iv.setImageResource(R.drawable.page_on);
                    }
                    else {
                        iv.setImageResource(R.drawable.page_off);
                    }
                }

                if(arg0 == list.size() - 1) {
                    setTimer();
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void setTimer() {
        final Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        timer.schedule(timerTask, 1000 * 3);
    }
}

