package ua.kharkiv.dorozhan.androiddataservice.viewmodel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    CharSequence mTitles[];
    int mNumbOfTabs;

    public ViewPagerAdapter(FragmentManager fragmentManager, CharSequence mTitles[], int mNumbOfTabs) {
        super(fragmentManager);
        this.mTitles = mTitles;
        this.mNumbOfTabs = mNumbOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            SelectFragment selectFragment = new SelectFragment();
            return selectFragment;
        } else {
            InsertFragment insertFragment = new InsertFragment();
            return insertFragment;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return mNumbOfTabs;
    }
}
