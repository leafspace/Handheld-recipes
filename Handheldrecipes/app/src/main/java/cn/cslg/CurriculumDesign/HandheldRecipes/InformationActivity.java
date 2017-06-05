package cn.cslg.CurriculumDesign.HandheldRecipes;

import android.view.Menu;
import android.widget.*;
import java.util.Random;
import android.os.Bundle;
import android.view.View;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.view.MenuItem;
import android.content.Intent;
import cn.cslg.CurriculumDesign.HandheldRecipes.ToolBean.Data;

/**
 * Created by Administrator on 2017/6/3.
 * LastEdit: 2017-6-4
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class InformationActivity extends Activity {
    private String[] textList = {"销魂清爽的蒜泥白肉", "蒜蓉花甲粉丝", "干豆角焖猪蹄", "风味茄子", "猪肉饭卷", "无锡糖醋排骨", "上海菜饭", "鱼香肉丝", "西葫芦炒鸡蛋", "抱蛋煎饺"};
    private int[] imageList = {R.drawable.food_1 , R.drawable.food_2, R.drawable.food_3, R.drawable.food_4, R.drawable.food_5,
            R.drawable.food_6, R.drawable.food_7, R.drawable.food_8, R.drawable.food_9, R.drawable.food_10};
    private String[] fileList = {"food_1.txt", "food_2.txt", "food_3.txt", "food_4.txt", "food_5.txt",
            "food_6.txt","food_7.txt", "food_8.txt", "food_9.txt", "food_10.txt"};

    private ImageView imageView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;

    private boolean isgood = false;
    private boolean isadd = false;

    private int index;
    private Data application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_acvity);

        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.textView1 = (TextView) findViewById(R.id.textView1);
        this.textView2 = (TextView) findViewById(R.id.textView3);
        this.textView3 = (TextView) findViewById(R.id.textView4);
        this.linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        this.linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        this.linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);

        this.index = getIntent().getIntExtra("id", 0);
        this.setInformation(this.index);

        this.application = (Data) getApplication();
        this.isgood = this.application.getIsGood(this.index);
        this.isadd = this.application.getIsAdd(this.index);

        if(this.isadd) {
            ((TextView) findViewById(R.id.textView6)).setText("移除菜谱");
            ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.delete);
        }

        if(this.isgood) {
            ((ImageView) findViewById(R.id.imageView2)).setImageResource(R.drawable.good_after);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.son, menu);
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
        final Intent intent = new Intent(InformationActivity.this, ShowFoodsActivity.class);
        startActivity(intent);
    }

    public void doClick1(View view) {
        if(!this.isgood) {
            int number = Integer.parseInt(textView3.getText().toString());
            textView3.setText(++number + "");
            ((ImageView) findViewById(R.id.imageView2)).setImageResource(R.drawable.good_after);
            this.isgood = true;
            this.application.setIsGood(this.index);
        } else {
            Toast.makeText(InformationActivity.this, "你今天已经赞过了！", 2000).show();
        }
    }

    public void doClick2(View view) {
        Toast.makeText(InformationActivity.this, "请等待下个版本开发", 2000).show();
    }

    public void doClick3(View view) {
        if(!this.isadd) {
            Toast.makeText(InformationActivity.this, "已加入今日菜谱", 2000).show();

            this.isadd = true;
            this.application.setIsAdd(this.index);
            this.application.addFood(this.index);

            ((TextView) findViewById(R.id.textView6)).setText("移除菜谱");
            ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.delete);
        } else {
            Toast.makeText(InformationActivity.this, "已移除今日菜单", 2000).show();

            this.isadd = false;
            this.application.removeIsAdd(this.index);
            this.application.removeFood(this.index);

            ((TextView) findViewById(R.id.textView6)).setText("添加到菜谱");
            ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.add);
        }
    }

    private void setInformation(int index) {
        Random random = new Random();
        this.imageView.setImageResource(imageList[index]);
        this.textView1.setText(textList[index]);
        this.textView2.setText(this.getcontent(fileList[index]));
        this.textView3.setText(random.nextInt(100) + "");
    }

    private String getcontent(String name) {
        String str = null;
        InputStream inputStream;
        try {
            inputStream = getResources().getAssets().open(name);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            str = new String(buffer,"utf8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
