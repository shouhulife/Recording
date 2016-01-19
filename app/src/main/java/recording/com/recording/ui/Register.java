package recording.com.recording.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import recording.com.recording.Model.SharedpreKeyMap;
import recording.com.recording.Model.UserInfo;
import recording.com.recording.R;
import recording.com.recording.base.BaseAppCompatActivity;
import recording.com.recording.utils.DES;
import recording.com.recording.utils.SPUtil;

/**
 * 注册
 * Created by zhangh on 2016/1/18.
 */
public class Register extends BaseAppCompatActivity {

    TextInputLayout usertil, pwdtil, zcpwdtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_register);

        init();
    }

    private void init() {
        usertil = (TextInputLayout) findViewById(R.id.register_til_name);
        pwdtil = (TextInputLayout) findViewById(R.id.register_til_pwd);
        zcpwdtil = (TextInputLayout) findViewById(R.id.register_til_zcpwd);

        usertil.setHint("请输入用户名");
        pwdtil.setHint("请输入密码");
        zcpwdtil.setHint("请再次输入密码");
        TextView tv_title = (TextView) findViewById(R.id.title_name);
        tv_title.setText("注册账户");
        findViewById(R.id.title_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register.this.finish();
            }
        });

        findViewById(R.id.register_btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = usertil.getEditText().getText().toString();
                final String pwd = pwdtil.getEditText().getText().toString();
                String zcpwd = zcpwdtil.getEditText().getText().toString();
                if (user.isEmpty()) {
                    usertil.setError("用户名不能为空");
                    return;
                }else{
                    usertil.setErrorEnabled(false);
                }
                if (user.length() < 4) {
                    usertil.setError("长度请大于6个字符");
                    return;
                }else{
                    usertil.setErrorEnabled(false);
                }
                if (pwd.isEmpty()) {
                    pwdtil.setError("密码不能为空");
                    return;
                }else{
                    pwdtil.setErrorEnabled(false);
                }
                if (pwd.length() < 6) {
                    pwdtil.setError("密码长度请大于6位");
                    return;
                }else{
                    pwdtil.setErrorEnabled(false);
                }
                if (zcpwd.isEmpty()) {
                    zcpwdtil.setError("请输入密码");
                    return;
                }else{
                    zcpwdtil.setErrorEnabled(false);
                }
                if (!zcpwd.equals(pwd)) {
                    zcpwdtil.setError("两次输入密码不同请重新输入");
                    return;
                }else{
                    zcpwdtil.setErrorEnabled(false);
                }

                hideKeyboard();
                BmobQuery<UserInfo> query = new BmobQuery<UserInfo>();
                query.addWhereEqualTo("username",user);
                query.findObjects(Register.this, new FindListener<UserInfo>() {
                    @Override
                    public void onSuccess(List<UserInfo> list) {
                        if(list!=null&&list.size()>0){
                            usertil.setError("你输入的用户名已存在请重新输入");
                        }else{
                            UserInfo userinfo = new UserInfo();
                            userinfo.setUsername(user);
                            Long date = System.currentTimeMillis();
                            userinfo.setDate(date);
                            try {
                                userinfo.setPassword(DES.encryptDES(pwd, getString(R.string.app_info), null));
                            } catch (Exception e) {
                                Snackbar.make(usertil,"注册失败.-",Snackbar.LENGTH_LONG).show();
                                e.printStackTrace();
                                return;
                            }
                            userinfo.save(Register.this, new SaveListener() {
                                @Override
                                public void onSuccess() {
                                    Snackbar.make(usertil,"注册成功",Snackbar.LENGTH_LONG).show();
                                    new SPUtil(Register.this).saveValue(SharedpreKeyMap.LoginName,user);
                                    Register.this.finish();
                                    UIHelper.startAddUserInfo(Register.this);
                                }

                                @Override
                                public void onFailure(int i, String s) {
                                    Snackbar.make(usertil,"注册失败."+s,Snackbar.LENGTH_LONG).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(int i, String s) {
                        UserInfo userinfo = new UserInfo();
                        userinfo.setUsername(user);
                        Long date = System.currentTimeMillis();
                        userinfo.setDate(date);
                        try {
                            userinfo.setPassword(DES.encryptDES(pwd, getString(R.string.app_info), null));
                        } catch (Exception e) {
                            Snackbar.make(usertil,"注册失败.-",Snackbar.LENGTH_LONG).show();
                            e.printStackTrace();
                            return;
                        }
                        userinfo.save(Register.this, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Snackbar.make(usertil, "注册成功", Snackbar.LENGTH_LONG).show();
                                new SPUtil(Register.this).saveValue(SharedpreKeyMap.LoginName, user);
                                Register.this.finish();
                                UIHelper.startAddUserInfo(Register.this);
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Snackbar.make(usertil, "注册失败." + s, Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                });

            }
        });
    }

}
