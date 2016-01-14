package recording.com.recording.ui;

import android.content.Context;
import android.content.Intent;

import recording.com.recording.MainActivity;

/**
 * Created by hong on 2016/1/13.
 */
public class UIHelper {
    /** 启动首页 */
    public static void startMainUI(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
