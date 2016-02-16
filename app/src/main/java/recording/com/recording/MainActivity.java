package recording.com.recording;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import recording.com.recording.adapter.TabLayViewPAdapter;
import recording.com.recording.base.BaseAppCompatActivity;
import recording.com.recording.fragment.Birthday;
import recording.com.recording.fragment.CareerF;
import recording.com.recording.fragment.Diary;
import recording.com.recording.ui.UIHelper;

public class MainActivity extends BaseAppCompatActivity {
    private int index = 0;
    private long mExitTime;// 退出时间判断实现双击退出
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = (TabLayout) findViewById(R.id.tabs);

        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();

        String titlec = "职业生涯";
        tabs.addTab(tabs.newTab().setText(titlec));
        titles.add(titlec);
        Fragment fragment = new CareerF();
        fragments.add(fragment);

        String titled = "日记";
        tabs.addTab(tabs.newTab().setText(titled));
        titles.add(titled);
        Fragment fragmentd = new Diary();
        fragments.add(fragmentd);

        String titleb = "亲友生日";
        tabs.addTab(tabs.newTab().setText(titleb));
        titles.add(titleb);
        Fragment fragmentb = new Birthday();
        fragments.add(fragmentb);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayViewPAdapter adapter = new TabLayViewPAdapter(getSupportFragmentManager(),titles,fragments);

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabsFromPagerAdapter(adapter);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                index = tab.getPosition();
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        final FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.bt_auction);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(index){
                    case 0:
                        UIHelper.startAddCareer(MainActivity.this);
                        break;
                    case 1:
                        break;
                    case 2:
                        UIHelper.startAddBirthday(MainActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Snackbar.make(tabs,getResources().getString(R.string.app_exit),Snackbar.LENGTH_LONG).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                    MyApplication.getInstance().exit();
                }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
