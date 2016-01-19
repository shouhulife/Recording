package recording.com.recording.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import recording.com.recording.Model.SharedpreKeyMap;
import recording.com.recording.Model.UserInfo;
import recording.com.recording.R;
import recording.com.recording.base.BaseAppCompatActivity;
import recording.com.recording.utils.SPUtil;

/**
 * 添加手机号和邮箱账号
 * Created by zhangh on 2016/1/19.
 */
public class AddPandE extends BaseAppCompatActivity implements View.OnClickListener {
    TextInputLayout til_phone,til_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_register_emailphone);

        init();
    }

    private void init(){
        til_phone = (TextInputLayout) findViewById(R.id.register_til_phone);
        til_email = (TextInputLayout) findViewById(R.id.register_til_email);
        findViewById(R.id.register_btn_register).setOnClickListener(this);
        TextView tv_title = (TextView) findViewById(R.id.title_name);
        tv_title.setText("完善信息");
        findViewById(R.id.title_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn_register:
                final String phone = til_phone.getEditText().getText().toString();
                final String email = til_email.getEditText().getText().toString();
                if(phone.isEmpty()){
                    til_phone.setError("手机号不能为空");
                }else{
                    til_phone.setErrorEnabled(false);
                    if(!yanzheng(1,phone)){
                        til_phone.setError("手机号码格式错误");
                    }else{
                        til_phone.setErrorEnabled(false);
                        if(email.isEmpty()){
                            til_email.setError("邮箱号不能为空");
                        }else{
                            til_email.setErrorEnabled(false);
                            if(!yanzheng(0,email)){
                                til_email.setError("邮箱格式错误");
                            }else{
                                til_email.setErrorEnabled(false);
                                BmobQuery<UserInfo> query = new BmobQuery<>();
                                query.addWhereEqualTo("username",new SPUtil(AddPandE.this).getValue(SharedpreKeyMap.LoginName));
                                query.findObjects(AddPandE.this, new FindListener<UserInfo>() {
                                    @Override
                                    public void onSuccess(List<UserInfo> list) {
                                        if(list!=null&&list.size()>0){
                                            UserInfo info = new UserInfo();
                                            info.setPhone(phone);
                                            info.setEmail(email);
                                            info.update(AddPandE.this, list.get(0).getObjectId(), new UpdateListener() {
                                                @Override
                                                public void onSuccess() {
                                                    Snackbar.make(til_phone,"保存信息成功",Snackbar.LENGTH_LONG).show();
                                                    UIHelper.startMainUI(AddPandE.this);
                                                }

                                                @Override
                                                public void onFailure(int i, String s) {
                                                    Snackbar.make(til_phone,"保存信息失败!"+s.toString(),Snackbar.LENGTH_LONG).show();
                                                }
                                            });
                                        }else{
                                            Snackbar.make(til_phone,"账号不存在请重新登录",Snackbar.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onError(int i, String s) {
                                        Snackbar.make(til_phone,s.toString(),Snackbar.LENGTH_LONG).show();
                                    }
                                });

                            }
                        }
                    }
                }
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }


    /** 手机号和邮箱验证 */
    public boolean yanzheng(int k, String str){
        boolean b = false;
        Pattern pattern = null;
        Matcher matcher = null;
        switch(k){
            case 0:
                pattern = Pattern
                        .compile("^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[-.])+[A-Za-z0-9]{2,5}$");
                matcher = pattern.matcher(str);
                if (matcher.matches()) {
                    b = true;
                } else {
                    b = false;
                }
                break;
            case 1:
                pattern = Pattern.compile("^1[3|5|7|8|][0-9]{9}$");
                matcher = pattern.matcher(str);
                if (matcher.matches()) {
                    b = true;
                } else {
                    b = false;
                }
                break;
        }
        return b;
    }
}
