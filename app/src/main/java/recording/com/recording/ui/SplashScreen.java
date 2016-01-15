package recording.com.recording.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import recording.com.recording.Model.StartPage;
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
        final ImageView iv = (ImageView) findViewById(R.id.sp_iv_bg);
        final TextView tv_value = (TextView) findViewById(R.id.sp_tv_value);

        Bmob.initialize(this, "1c05091382df8544e12179e22690dc9a");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UIHelper.startMainUI(SplashScreen.this);
            }
        }, 2000);

        BmobQuery<StartPage> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(this, new FindListener<StartPage>() {
            @Override
            public void onSuccess(List<StartPage> list) {
                if (list != null && list.size() > 0) {
                    Glide.with(SplashScreen.this).load(list.get(0).getImgUrl()).into(iv);
                    tv_value.setText(list.get(0).getInspirational());
                } else {
                    StartPage pagedata = new StartPage();
                    pagedata.setImgUrl("http://photo.163.com/xhdshz@foxmail.com/#m=2&aid=299679088&pid=9510650497");
                    pagedata.setInspirational("我们走得太快，灵魂都跟不上了。");
                    pagedata.save(SplashScreen.this);
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }
}
