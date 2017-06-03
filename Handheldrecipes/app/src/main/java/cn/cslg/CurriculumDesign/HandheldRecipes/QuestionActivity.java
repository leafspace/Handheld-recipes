package cn.cslg.CurriculumDesign.HandheldRecipes;

import java.util.Timer;
import android.os.Bundle;
import java.util.TimerTask;
import android.app.Activity;
import android.widget.Toast;
import android.content.Intent;

/**
 * Created by Administrator on 2017/6/3.
 * LastEdit: 2017-6-3
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class QuestionActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        Toast.makeText(this, "啥也没找到！", 2000).show();
        final Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
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
