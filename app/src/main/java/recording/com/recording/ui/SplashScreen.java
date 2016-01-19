package recording.com.recording.ui;

import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import recording.com.recording.Model.SharedpreKeyMap;
import recording.com.recording.Model.StartPage;
import recording.com.recording.R;
import recording.com.recording.utils.SPUtil;

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
                String name = new SPUtil(SplashScreen.this).getValue(SharedpreKeyMap.LoginName);
                if(!SharedpreKeyMap.defaults.equals(name)){
                    UIHelper.startMainUI(SplashScreen.this);
                }else{
                    UIHelper.startLogin(SplashScreen.this);
                }
                SplashScreen.this.finish();
            }
        }, 3000);

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
                    pagedata.setImgUrl("http://img2.ph.126.net/QC227TLdA9L2j5cyNbIZQA==/6630630160073910536.jpg");
                    pagedata.setInspirational("我们走得太快，灵魂都跟不上了。");
                    pagedata.save(SplashScreen.this);
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
