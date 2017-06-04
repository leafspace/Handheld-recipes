package cn.cslg.CurriculumDesign.HandheldRecipes.Adapter;

import android.widget.*;
import java.util.Random;
import android.view.View;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.content.Intent;
import android.content.Context;
import android.view.LayoutInflater;
import cn.cslg.CurriculumDesign.HandheldRecipes.*;

/**
 * Created by Administrator on 2017/6/3.
 * LastEdit: 2017-6-4
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class ClassifyAdapter extends BaseAdapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<String> list;

    private String[] textList = {"销魂清爽的蒜泥白肉", "蒜蓉花甲粉丝", "干豆角焖猪蹄", "风味茄子", "猪肉饭卷", "无锡糖醋排骨", "上海菜饭", "鱼香肉丝", "西葫芦炒鸡蛋", "抱蛋煎饺"};
    private int[] imageList = {R.drawable.food_1 , R.drawable.food_2, R.drawable.food_3, R.drawable.food_4, R.drawable.food_5,
            R.drawable.food_6, R.drawable.food_7, R.drawable.food_8, R.drawable.food_9, R.drawable.food_10};

    public ClassifyAdapter(Context context, int resource, ArrayList<String> list){
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
        if(list.size() % 3 > 0) {
            return list.size() / 3 + 1;
        } else {
            return list.size() / 3;
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
        ClassifyAdapter.ViewHolder viewHolder = null;
        if (convertView == null ) {
            convertView = inflater.inflate(resource, null);
            viewHolder = new ClassifyAdapter.ViewHolder();

            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
            viewHolder.textView3 = (TextView) convertView.findViewById(R.id.textView3);
            viewHolder.textView4 = (TextView) convertView.findViewById(R.id.textView4);
            viewHolder.textView5 = (TextView) convertView.findViewById(R.id.textView5);
            viewHolder.imageView1 = (ImageView) convertView.findViewById(R.id.imageView1);
            viewHolder.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
            viewHolder.imageView3 = (ImageView) convertView.findViewById(R.id.imageView3);
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ClassifyAdapter.ViewHolder)convertView.getTag();
        }

        switch (position)
        {
            case 0 : viewHolder.textView4.setText("川"); viewHolder.textView5.setText("菜"); break;
            case 1 : viewHolder.textView4.setText("湘"); viewHolder.textView5.setText("菜"); break;
            case 2 : viewHolder.textView4.setText("浙"); viewHolder.textView5.setText("菜"); break;
            case 3 : viewHolder.textView4.setText("徽"); viewHolder.textView5.setText("菜"); break;
            default: break;
        }
        this.setViewHolder(viewHolder);
        return convertView;
    }

    private void setViewHolder(ViewHolder viewHolder) {
        int[] index = new int[3];
        int isexist = 0;
        do {
            Random random = new Random();
            int randomNumber = random.nextInt(10);
            int i;
            for(i = 0; i < isexist; ++i) {
                if(index[i] == randomNumber) {
                    break;
                }
            }

            if(i == isexist) {
                index[isexist++] = randomNumber;
            }
        } while (isexist != 3);

        viewHolder.textView1.setText(textList[index[0]]);
        viewHolder.textView2.setText(textList[index[1]]);
        viewHolder.textView3.setText(textList[index[2]]);

        viewHolder.imageView1.setImageResource(imageList[index[0]]);
        viewHolder.imageView2.setImageResource(imageList[index[1]]);
        viewHolder.imageView3.setImageResource(imageList[index[2]]);
        this.setListterner(viewHolder);
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
        final Intent intent = new Intent(context, InformationActivity.class);
        intent.putExtra("id", findID(textView));
        context.startActivity(intent);
    }

    private void setListterner(final ViewHolder viewHolder) {
        final String cuisine = viewHolder.textView4.getText().toString() + viewHolder.textView5.getText().toString();
        viewHolder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(viewHolder.textView1, viewHolder.imageView1);
            }
        });
        viewHolder.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(viewHolder.textView2, viewHolder.imageView2);
            }
        });
        viewHolder.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(viewHolder.textView3, viewHolder.imageView3);
            }
        });
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("keyWords", cuisine);
                context.startActivity(intent);
            }
        });

        viewHolder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(viewHolder.textView1, viewHolder.imageView1);
            }
        });

        viewHolder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(viewHolder.textView2, viewHolder.imageView2);
            }
        });
        viewHolder.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(viewHolder.textView3, viewHolder.imageView3);
            }
        });

    }

    public class ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        LinearLayout linearLayout;
    }
}
