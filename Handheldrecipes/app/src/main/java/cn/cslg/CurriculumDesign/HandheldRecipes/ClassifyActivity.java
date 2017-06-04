package cn.cslg.CurriculumDesign.HandheldRecipes;

import android.os.Bundle;
import java.util.ArrayList;
import android.app.Activity;
import android.widget.ListView;
import cn.cslg.CurriculumDesign.HandheldRecipes.Adapter.ClassifyAdapter;

/**
 * Created by Administrator on 2017/6/3.
 * LastEdit: 2017-6-4
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class ClassifyActivity extends Activity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_activity);

        this.listView = (ListView) findViewById(R.id.listView);

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        ClassifyAdapter adapter = new ClassifyAdapter(this, R.layout.classify_layout, list);
        this.listView.setAdapter(adapter);
    }
}
