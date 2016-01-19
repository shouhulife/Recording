package recording.com.recording.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.bmob.v3.listener.SaveListener;
import recording.com.recording.Model.Career;
import recording.com.recording.Model.SharedpreKeyMap;
import recording.com.recording.R;
import recording.com.recording.base.BaseAppCompatActivity;
import recording.com.recording.utils.SPUtil;

/**
 * 添加生涯记录
 * Created by zhangh on 2016/1/19.
 */
public class AddCareer extends BaseAppCompatActivity implements View.OnClickListener{
    private EditText et_content,et_remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_addcareer);

        init();
    }

    private void init(){
        findViewById(R.id.title_back).setOnClickListener(this);
        TextView tv_title = (TextView) findViewById(R.id.title_name);
        tv_title.setText("添加生涯记录");
        findViewById(R.id.career_btn).setOnClickListener(this);

        et_content = (EditText) findViewById(R.id.career_et_content);
        et_remark = (EditText) findViewById(R.id.career_et_remark);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.title_back:
                finish();
                break;
            case R.id.career_btn:
                String content = et_content.getText().toString().trim();
                String remark = et_remark.getText().toString().trim();
                if(content.isEmpty()){
                    Snackbar.make(et_content,"请输入生涯内容",Snackbar.LENGTH_LONG).show();
                }else{
                    Career career = new Career();
                    career.setDate(System.currentTimeMillis());
                    career.setContent(content);
                    career.setRemark(remark);
                    career.setUserid(new SPUtil(AddCareer.this).getValue(SharedpreKeyMap.LoginName));
                    career.save(AddCareer.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Snackbar.make(et_content,"保存生涯内容成功",Snackbar.LENGTH_LONG).show();
                            et_content.setText("");
                            et_remark.setText("");
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Snackbar.make(et_content,"保存生涯内容失败",Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
                break;
        }
    }
}
