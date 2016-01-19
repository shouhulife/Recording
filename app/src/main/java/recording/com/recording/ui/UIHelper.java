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
    /** 启动登录页 */
    public static void startLogin(Context context){
        context.startActivity(new Intent(context,Login.class));
    }
    /** 启动注册页 */
    public static void startRegister(Context context){
        context.startActivity(new Intent(context,Register.class));
    }
    /** 启动完善个人资料页面 */
    public static void startAddUserInfo(Context context){
        context.startActivity(new Intent(context,AddPandE.class));
    }
    /** 添加职业生涯记录页面 */
    public static void startAddCareer(Context context){
        context.startActivity(new Intent(context, AddCareer.class));
    }
}
