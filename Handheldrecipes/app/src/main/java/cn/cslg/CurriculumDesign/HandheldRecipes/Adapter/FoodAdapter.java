package cn.cslg.CurriculumDesign.HandheldRecipes.Adapter;

import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import cn.cslg.CurriculumDesign.HandheldRecipes.R;
import cn.cslg.CurriculumDesign.HandheldRecipes.InformationActivity;

/**
 * Created by Administrator on 2017/6/4.
 * LastEdit: 2017-6-4
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class FoodAdapter extends BaseAdapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected int[] list;

    private String[] textList = {"销魂清爽的蒜泥白肉", "蒜蓉花甲粉丝", "干豆角焖猪蹄", "风味茄子", "猪肉饭卷", "无锡糖醋排骨", "上海菜饭", "鱼香肉丝", "西葫芦炒鸡蛋", "抱蛋煎饺"};
    private int[] imageList = {R.drawable.food_1 , R.drawable.food_2, R.drawable.food_3, R.drawable.food_4, R.drawable.food_5,
            R.drawable.food_6, R.drawable.food_7, R.drawable.food_8, R.drawable.food_9, R.drawable.food_10};

    public FoodAdapter(Context context, int resource, int[] list){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        if(list == null){
            this.list = new int[10];
        }else{
            this.list = list;
        }
    }

    @Override
    public int getCount() {
        for(int i = 0; i < 10; ++i) {
            if(this.list[i] == 0) {
                return i;
            }
        }
        return 10;
    }
    @Override
    public Object getItem(int position) {
        return list[position];
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodAdapter.ViewHolder viewHolder = null;
        if (convertView == null ) {
            convertView = inflater.inflate(resource, null);
            viewHolder = new FoodAdapter.ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (FoodAdapter.ViewHolder)convertView.getTag();
        }

        int index = list[position];
        this.setViewHolder(viewHolder, index);
        this.setListterner(viewHolder);
        return convertView;
    }

    private void setViewHolder(ViewHolder viewHolder, int index) {
        viewHolder.textView.setText(textList[index]);
        viewHolder.imageView.setImageResource(imageList[index]);
    }

    private int findID(TextView textView) {
        String foodName = textView.getText().toString();
        int index = 0;
        for(int i = 0; i < textList.length; ++i) {
            if(foodName.equals(textList[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void showInfo(ViewHolder viewHolder) {
        final Intent intent = new Intent(context, InformationActivity.class);
        intent.putExtra("id", findID(viewHolder.textView));
        context.startActivity(intent);
    }

    private void setListterner(final ViewHolder viewHolder) {
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(viewHolder);
            }
        });

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(viewHolder);
            }
        });
    }

    public class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
