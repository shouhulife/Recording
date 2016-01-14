package recording.com.recording;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import recording.com.recording.adapter.TabLayViewPAdapter;
import recording.com.recording.fragment.Birthday;
import recording.com.recording.fragment.Career;
import recording.com.recording.fragment.Diary;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();

        String titlec = "职业生涯";
        tabs.addTab(tabs.newTab().setText(titlec));
        titles.add(titlec);
        Fragment fragment = new Career();
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
    }

}
