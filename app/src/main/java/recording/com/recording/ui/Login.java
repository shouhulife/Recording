package recording.com.recording.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.View.OnClickListener;

import recording.com.recording.R;
import recording.com.recording.base.BaseAppCompatActivity;

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
                String user = usertil.getEditText().getText().toString();
                String pwd = pwdtil.getEditText().getText().toString();
                if(user.isEmpty()){
                    usertil.setError("用户名不能为空");
                }else{
                    usertil.setErrorEnabled(false);
                    if(pwd.isEmpty()){
                        pwdtil.setError("密码不能为空");
                    }else{
                        pwdtil.setErrorEnabled(false);

                    }
                }
                break;
        }
    }
}
