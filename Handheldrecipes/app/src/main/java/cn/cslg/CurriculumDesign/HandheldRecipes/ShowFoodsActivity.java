package cn.cslg.CurriculumDesign.HandheldRecipes;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import cn.cslg.CurriculumDesign.HandheldRecipes.ToolBean.Data;
import cn.cslg.CurriculumDesign.HandheldRecipes.Adapter.FoodAdapter;

/**
 * Created by Administrator on 2017/6/4.
 * LastEdit: 2017-6-4
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class ShowFoodsActivity extends Activity {
    private ListView listView;
    private RatingBar ratingBar1;
    private RatingBar ratingBar2;
    private RatingBar ratingBar3;
    private Data application;

    private String[] textList = {"销魂清爽的蒜泥白肉", "蒜蓉花甲粉丝", "干豆角焖猪蹄", "风味茄子", "猪肉饭卷", "无锡糖醋排骨", "上海菜饭", "鱼香肉丝", "西葫芦炒鸡蛋", "抱蛋煎饺"};
    private int[] imageList = {R.drawable.food_1 , R.drawable.food_2, R.drawable.food_3, R.drawable.food_4, R.drawable.food_5,
            R.drawable.food_6, R.drawable.food_7, R.drawable.food_8, R.drawable.food_9, R.drawable.food_10};
    private String[] fileList = {"food_1.txt", "food_2.txt", "food_3.txt", "food_4.txt", "food_5.txt",
            "food_6.txt","food_7.txt", "food_8.txt", "food_9.txt", "food_10.txt"};

    private int[] karulyList;
    private int[] healtyList;
    private int[] satietyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showfoods_activity);

        this.listView = (ListView) findViewById(R.id.listView);
        this.ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);
        this.ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        this.ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);

        this.application = (Data) getApplication();
        this.karulyList = this.application.getKarulyList();
        this.healtyList = this.application.getHealtyList();
        this.satietyList = this.application.getSatietyList();

        FoodAdapter adapter = new FoodAdapter(this, R.layout.food_layout, this.application.getChoice());
        this.listView.setAdapter(adapter);

        double sum_karuly = 0;
        double sum_healty = 0;
        double sum_satety = 0;

        for(int i = 0; i < this.application.getIsExist(); ++i) {
            sum_karuly += this.karulyList[this.application.getChoice()[i]];
            sum_healty += this.healtyList[this.application.getChoice()[i]];
            sum_satety += this.satietyList[this.application.getChoice()[i]];
        }

        this.ratingBar1.setMax(10);
        this.ratingBar2.setMax(10);
        this.ratingBar3.setMax(10);

        sum_karuly = sum_karuly * 1.0 / this.application.getIsExist();
        sum_healty = sum_healty * 1.0 / this.application.getIsExist();
        sum_satety = sum_satety * 1.0 / this.application.getIsExist();

        this.ratingBar1.setRating((float) sum_karuly * 3 / 8);
        this.ratingBar2.setRating((float) sum_healty * 3 / 8);
        this.ratingBar3.setRating((float) sum_satety * 3 / 8);

        String information = new String();
        information += this.getKarulySuggest(sum_karuly);
        information += "\n";
        information += this.getHealtySuggest(sum_healty);
        information += "\n";
        information += this.getSatetySuggest(sum_satety);

        ((TextView) findViewById(R.id.textView6)).setText(information);
    }


    private String getKarulySuggest(double number) {
        switch ((int)number)
        {
            case 1 : case 2 : case 3 : return "这个餐谱的卡路里还不能满足您的消耗呢！";
            case 4 : case 5 : case 6 : return "您可以再添加一些食物哦！";
            case 7 : case 8 : case 9 : return "这个餐谱卡路里太高了，你要攒肉肉吗？";
            case 10 :case 0: return "？";
            default: return "";
        }
    }

    private String getHealtySuggest(double number) {
        switch ((int)number)
        {
            case 1 : case 2 : case 3 : return "这个餐谱也太不健康了！";
            case 4 : case 5 : case 6 : return "您的食谱还有待改善呢！";
            case 7 : case 8 : case 9 : return "很好，这个餐谱绝对健康，继续保持！";
            case 10 :case 0: return "？";
            default: return "";
        }
    }

    private String getSatetySuggest(double number) {
        switch ((int)number)
        {
            case 1 : case 2 : case 3 : return "您吃这个一定吃不饱！";
            case 4 : case 5 : case 6 : return "太好啦，这个程度刚刚好！";
            case 7 : case 8 : case 9 : return "吃的太饱不利于学习哦！";
            case 10 :case 0: return "？";
            default: return "";
        }
    }
}
