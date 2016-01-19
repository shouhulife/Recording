package recording.com.recording.utils;

import android.content.Context;
import android.content.SharedPreferences;

import recording.com.recording.Model.SharedpreKeyMap;

/**
 * SharedPreferences 帮助类
 * Created by zhangh on 2016/1/19.
 */
public class SPUtil {
    private SharedPreferences sp;

    public SPUtil(Context context){
        sp = context.getSharedPreferences(SharedpreKeyMap.sharedpreName, Context.MODE_PRIVATE);
    }

    /** 保存数据 */
    public void saveValue(String key,String value){
         sp.edit().putString(key,value).commit();
    }

    /** 获取数据 */
    public String getValue(String key){
        return sp.getString(key,SharedpreKeyMap.defaults);
    }
}
