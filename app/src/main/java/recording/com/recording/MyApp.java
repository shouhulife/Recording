package recording.com.recording;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by hong on 2016/1/13.
 */
public class MyApp extends Application {
    public MyApp(){
        Bmob.initialize(this,"1c05091382df8544e12179e22690dc9a");
    }
}
