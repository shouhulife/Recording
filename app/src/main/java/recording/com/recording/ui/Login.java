package recording.com.recording.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import recording.com.recording.Model.SharedpreKeyMap;
import recording.com.recording.Model.UserInfo;
import recording.com.recording.R;
import recording.com.recording.base.BaseAppCompatActivity;
import recording.com.recording.utils.DES;
import recording.com.recording.utils.SPUtil;

/**
 * 登录
 * Created by zhangh on 2016/1/18.
 */
public class Login extends BaseAppCompatActivity implements OnClickListener{
    TextInputLayout usertil, pwdtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);

        init();
    }

    private void init() {
        findViewById(R.id.login_zc).setOnClickListener(this);
        findViewById(R.id.login_tv_wjmm).setOnClickListener(this);
        findViewById(R.id.login_but_login).setOnClickListener(this);
        usertil = (TextInputLayout) findViewById(R.id.login_til_name);
        pwdtil = (TextInputLayout) findViewById(R.id.login_til_pwd);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.login_zc:
                UIHelper.startRegister(this);
                break;
            case R.id.login_but_login:
                final String user = usertil.getEditText().getText().toString();
                final String pwd = pwdtil.getEditText().getText().toString();
                if(user.isEmpty()){
                    usertil.setError("用户名不能为空");
                }else{
                    usertil.setErrorEnabled(false);
                    if(pwd.isEmpty()){
                        pwdtil.setError("密码不能为空");
                    }else{
                        pwdtil.setErrorEnabled(false);
                        BmobQuery<UserInfo> query = new BmobQuery<>();
                        query.addWhereEqualTo("username",user);
                        query.findObjects(Login.this, new FindListener<UserInfo>() {
                            @Override
                            public void onSuccess(List<UserInfo> list) {
                                if(list!=null&&list.size()>0){
                                    try {
                                        String mm = DES.encryptDES(pwd,getString(R.string.app_info),null);
                                        if(mm.equals(list.get(0).getPassword())){
                                            new SPUtil(Login.this).saveValue(SharedpreKeyMap.LoginName,user);
                                            UIHelper.startMainUI(Login.this);
                                        }else{
                                            pwdtil.setError("密码错误请重新输入");
                                        }
                                    } catch (Exception e) {
                                        pwdtil.setError("密码错误请重新输入");
                                        e.printStackTrace();
                                    }
                                }else{
                                    usertil.setError("用户名不存在，请重新输入");
                                }
                            }

                            @Override
                            public void onError(int i, String s) {
                                usertil.setError(s);
                            }
                        });
                    }
                }
                break;
        }
    }
}
