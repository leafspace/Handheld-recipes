package cn.cslg.CurriculumDesign.HandheldRecipes.Adapter;

import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.view.PagerAdapter;

/**
 * Created by Administrator on 2017/6/3.
 * LastEdit: 2017-6-3
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class MyAdapter extends PagerAdapter {
    private List<View > list;

    public MyAdapter(List<View> list) {
        super();
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }
}