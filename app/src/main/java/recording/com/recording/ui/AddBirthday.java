package recording.com.recording.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import cn.bmob.v3.listener.SaveListener;
import recording.com.recording.Model.Birthday;
import recording.com.recording.Model.SharedpreKeyMap;
import recording.com.recording.R;
import recording.com.recording.base.BaseAppCompatActivity;
import recording.com.recording.utils.SPUtil;

/**
 * 添加生日记录
 * Created by zhangh on 2016/2/16.
 */
public class AddBirthday extends BaseAppCompatActivity implements View.OnClickListener{
    EditText et_name,et_gx,et_bz;
    TextView tv_date;
    String datestr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_addbirthday);

        init();
        byid();
    }

    private void byid() {
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.addbirth_btn).setOnClickListener(this);
        findViewById(R.id.addbirth_tv_btn_date).setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.addbirth_et_name);
        et_gx = (EditText) findViewById(R.id.addbirth_et_gx);
        tv_date = (TextView) findViewById(R.id.addbirth_tv_date);
        et_bz = (EditText) findViewById(R.id.addbirth_et_bz);

        TextView tv_title = (TextView) findViewById(R.id.title_name);
        tv_title.setText("添加亲友生日");
    }

    private void init() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addbirth_btn:
                String name = new SPUtil(this).getValue(SharedpreKeyMap.LoginName);
                if(name.equals(SharedpreKeyMap.defaults)){
                    Snackbar.make(tv_date, getString(R.string.nologin), Snackbar.LENGTH_LONG).show();
                    return;
                }
                String names = et_name.getText().toString().trim();
                String gxs = et_gx.getText().toString().trim();
                String bz = et_bz.getText().toString().trim();
                if(names.isEmpty()){
                    Snackbar.make(tv_date, "请输入名字", Snackbar.LENGTH_LONG).show();
                }else{
                    if(gxs.isEmpty()){
                        Snackbar.make(tv_date, "请输入关系", Snackbar.LENGTH_LONG).show();
                    }else{
                        if(datestr!=null&&datestr.length()>0){
                            Birthday birthday = new Birthday();
                            birthday.setUserid(name);
                            birthday.setName(names);
                            birthday.setRelationship(gxs);
                            birthday.setBirthday(datestr);
                            birthday.setDate(System.currentTimeMillis());
                            birthday.setRemark(bz);
                            birthday.save(this, new SaveListener() {
                                @Override
                                public void onSuccess() {
                                    Snackbar.make(tv_date, "提交成功", Snackbar.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(int i, String s) {
                                    Snackbar.make(tv_date, "提交失败", Snackbar.LENGTH_LONG).show();
                                }
                            });
                        }else{
                            Snackbar.make(tv_date, "请选择生日", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }
                break;
            case R.id.addbirth_tv_btn_date:

                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd=new DatePickerDialog(this,Datelistener,year,month,day);
                dpd.show();
                break;
            case R.id.title_back:
                this.finish();
                break;
        }
    }

    private DatePickerDialog.OnDateSetListener Datelistener=new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            datestr  = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
            tv_date.setText("生日："+datestr);
        }
    };
}
