package cn.cslg.CurriculumDesign.HandheldRecipes;

import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Context;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import cn.cslg.CurriculumDesign.HandheldRecipes.Adapter.ListAdapter;

/**
 * Created by Administrator on 2017/6/3.
 * LastEdit: 2017-6-3
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class SearchActivity extends Activity {
    private EditText editText;
    private ListView listView;
    private String[] textList = {"销魂清爽的蒜泥白肉", "蒜蓉花甲粉丝", "干豆角焖猪蹄", "风味茄子", "猪肉饭卷", "无锡糖醋排骨", "上海菜饭", "鱼香肉丝", "西葫芦炒鸡蛋", "抱蛋煎饺"};
    private int[] imageList = {R.drawable.food_1 , R.drawable.food_2, R.drawable.food_3, R.drawable.food_4, R.drawable.food_5,
            R.drawable.food_6, R.drawable.food_7, R.drawable.food_8, R.drawable.food_9, R.drawable.food_10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        this.editText = (EditText) findViewById(R.id.editText);
        this.listView = (ListView) findViewById(R.id.listView);

        String keyWords = getIntent().getStringExtra("keyWords");
        this.editText.setText(keyWords);
        ArrayList<String> listIndex = this.searchFood(keyWords);
        this.setGoods(listIndex);
        this.setSearchEdit();

        if(listIndex.size() == 0) {
            Intent intent = new Intent(SearchActivity.this, QuestionActivity.class);
            startActivity(intent);
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goSearch(View view) {
        String keyWords = this.editText.getText().toString();
        keyWords.trim();
        if(keyWords.length() != 0) {
            Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
            intent.putExtra("keyWords", keyWords);
            startActivity(intent);
        }
    }

    private ArrayList<String> searchFood(String keyWords) {
        ArrayList<String> listIndex = new ArrayList<>();
        for(int i = 0; i < textList.length; ++i) {
            if(textList[i].equals("keyWords") | textList[i].contains(keyWords)) {
                listIndex.add("" + i);
            }
        }
        if(listIndex.size() != 0) {
            return  listIndex;
        }

        switch (keyWords)
        {
            case "川菜" : listIndex.add("0"); listIndex.add("6"); listIndex.add("9"); break;
            case "湘菜" : listIndex.add("7"); listIndex.add("3"); listIndex.add("1"); break;
            case "浙菜" : listIndex.add("6"); listIndex.add("0"); listIndex.add("4"); break;
            case "徽菜" : listIndex.add("2"); listIndex.add("5"); listIndex.add("8"); break;
            default: break;
        }

        return listIndex;
    }

    private void setGoods(ArrayList<String> listIndex) {
        ListAdapter listAdapter = new ListAdapter(this, R.layout.goods_layout, listIndex);
        this.listView.setAdapter(listAdapter);
    }

    private void setSearchEdit() {
        this.editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText.setText("");
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                editText.setTextColor(Color.rgb(92, 92, 92));
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                return true;
            }
        });
    }
}
