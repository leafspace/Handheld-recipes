package cn.cslg.CurriculumDesign.HandheldRecipes.Adapter;

import java.util.List;
import android.view.View;
import java.util.ArrayList;
import android.widget.Toast;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import cn.cslg.CurriculumDesign.HandheldRecipes.R;
import cn.cslg.CurriculumDesign.HandheldRecipes.InformationActivity;

/**
 * Created by Administrator on 2017/6/3.
 * LastEdit: 2017-6-3
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class ListAdapter extends BaseAdapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<String> list;

    private String[] textList = {"销魂清爽的蒜泥白肉", "蒜蓉花甲粉丝", "干豆角焖猪蹄", "风味茄子", "猪肉饭卷", "无锡糖醋排骨", "上海菜饭", "鱼香肉丝", "西葫芦炒鸡蛋", "抱蛋煎饺"};
    private int[] imageList = {R.drawable.food_1 , R.drawable.food_2, R.drawable.food_3, R.drawable.food_4, R.drawable.food_5,
            R.drawable.food_6, R.drawable.food_7, R.drawable.food_8, R.drawable.food_9, R.drawable.food_10};

    public ListAdapter(Context context, int resource, ArrayList<String> list){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        if(list == null){
            this.list = new ArrayList<>();
        }else{
            this.list = list;
        }
    }

    @Override
    public int getCount() {
        if(list.size() % 2 > 0) {
            return list.size() / 2 + 1;
        } else {
            return list.size() / 2;
        }
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null ) {
            convertView = inflater.inflate(resource, null);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
            viewHolder.imageView1 = (ImageView) convertView.findViewById(R.id.imageView1);
            viewHolder.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        int distance =  list.size() - position*2;
        int cellCount = distance >= 2 ? 2 : distance;
        final List<String> itemList = list.subList(position * 2, position * 2 + cellCount);
        if (itemList.size() > 0) {
            String index = itemList.get(0);
            this.setViewHolder(viewHolder.textView1, viewHolder.imageView1, index);
            this.setListterner(viewHolder.textView1, viewHolder.imageView1);

            if (itemList.size() > 1){
                index = itemList.get(1);
                viewHolder.textView2.setVisibility(View.VISIBLE);
                viewHolder.imageView2.setVisibility(View.VISIBLE);
                this.setViewHolder(viewHolder.textView2, viewHolder.imageView2, index);
                this.setListterner(viewHolder.textView2, viewHolder.imageView2);
            }else{
                viewHolder.textView2.setVisibility(View.INVISIBLE);
                viewHolder.imageView2.setVisibility(View.INVISIBLE);
            }
        }
        return convertView;
    }

    private void setViewHolder(TextView textView, ImageView imageView, String index) {
        textView.setText(textList[Integer.parseInt(index)]);
        imageView.setImageResource(imageList[Integer.parseInt(index)]);
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

    private void showInfo(TextView textView, ImageView imageView) {
        Toast.makeText(context, textView.getText(), 1000).show();
        final Intent intent = new Intent(context, InformationActivity.class);
        intent.putExtra("id", findID(textView));
        context.startActivity(intent);
    }

    private void setListterner(final TextView textView, final ImageView imageView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(textView, imageView);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(textView, imageView);
            }
        });
    }

    public class ViewHolder {
        TextView textView1;
        TextView textView2;
        ImageView imageView1;
        ImageView imageView2;
    }
}
