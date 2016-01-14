package recording.com.recording.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import recording.com.recording.R;

/**
 * 启动界面
 * Created by hong on 2016/1/13.
 */
public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UIHelper.startMainUI(SplashScreen.this);
            }
        },2000);
    }
}
