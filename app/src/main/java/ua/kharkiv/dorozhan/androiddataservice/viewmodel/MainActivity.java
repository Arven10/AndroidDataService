package ua.kharkiv.dorozhan.androiddataservice.viewmodel;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import ua.kharkiv.dorozhan.androiddataservice.R;
import ua.kharkiv.dorozhan.androiddataservice.viewmodel.tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    private static final CharSequence TITLES[]={"Select","Insert"};
    private static final int NUMBER_OF_TABS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), TITLES, NUMBER_OF_TABS);
        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(adapter);
        SlidingTabLayout tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabs.setViewPager(mPager);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
}
