package cn.cslg.CurriculumDesign.HandheldRecipes;

import android.view.*;
import java.util.List;
import java.util.Timer;
import android.widget.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.content.Context;
import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import cn.cslg.CurriculumDesign.HandheldRecipes.Adapter.MyAdapter;
import cn.cslg.CurriculumDesign.HandheldRecipes.Adapter.ListAdapter;

/**
 * Created by Administrator on 2017/6/2.
 * LastEdit: 2017-6-3
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class MainActivity extends Activity {
    private ViewPager viewpager;
    private LinearLayout layout;
    private int[] imgs = {R.drawable.mainslider_1, R.drawable.mainslider_2, R.drawable.mainslider_3,
            R.drawable.mainslider_4, R.drawable.mainslider_5, R.drawable.mainslider_6, };
    private List<View> list;

    private int curIndex;
    private EditText searchEdit;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<View>();
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.layout = (LinearLayout) findViewById(R.id.layout);
        this.searchEdit = (EditText) findViewById(R.id.searchEdit);
        this.listView = (ListView) findViewById(R.id.listView);

        this.setSlider();
        this.setSearchEdit();
        this.setGoods();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id)
        {
            case R.id.todayFoods : this.showFoods(); break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showFoods() {
        final Intent intent = new Intent(MainActivity.this, ShowFoodsActivity.class);
        startActivity(intent);
    }

    public void doSearch(View view) {
        String keyWords = searchEdit.getText().toString();
        keyWords.trim();
        if(keyWords.equals("搜索")) {
            keyWords = "";
        }
        if(keyWords.length() != 0) {
            final Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("keyWords", keyWords);
            startActivity(intent);
        } else {
            final Intent intent = new Intent(MainActivity.this, ClassifyActivity.class);
            startActivity(intent);
        }
    }

    private void setSlider() {
        //为viewpager控件添加要展示的页面
        for(int i = 0; i < imgs.length; i++){
            ImageView iv = new ImageView(this);
            ViewGroup.LayoutParams params =
                    new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(params);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setImageResource(imgs[i]);
            list.add(iv);
        }
        //添加特殊页面

        viewpager.setAdapter(new MyAdapter(list));
        //创建导航点
        for(int i = 0; i < list.size(); i++){
            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        this.curIndex = list.size() - 1;
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                setSliderTimerRun();
            }
        };
        timer.schedule(timerTask, 0, 1000 * 2);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            curIndex = (curIndex + 1) % list.size();
            viewpager.setCurrentItem(curIndex);
        }
    };

    private void setSliderTimerRun() {
        handler.sendMessage(handler.obtainMessage(1));
    }

    private void setSearchEdit() {
        this.searchEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                searchEdit.setText("");
                searchEdit.setFocusable(true);
                searchEdit.setFocusableInTouchMode(true);
                searchEdit.requestFocus();
                searchEdit.setTextColor(Color.rgb(92, 92, 92));
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                return true;
            }
        });


        this.searchEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER | keyCode == EditorInfo.IME_ACTION_DONE) {
                    String keyWords = searchEdit.getText().toString();
                    final Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("keyWords", keyWords);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    private void setGoods() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        ListAdapter adapter = new ListAdapter(this, R.layout.goods_layout, list);
        this.listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView, adapter);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.smoothScrollTo(0,20);
    }

    private void setListViewHeightBasedOnChildren(ListView listView, ListAdapter listAdapter) {
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        ((ViewGroup.MarginLayoutParams)params).setMargins(15, 15, 15, 15);
        listView.setLayoutParams(params);
    }
}
