package com.example.android.capstone.ui.adapter;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import com.example.android.capstone.ui.ExploreFragment;
import com.example.android.capstone.ui.DiscoverFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Discover", "Explore" };
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return DiscoverFragment.newInstance();
        else
            return ExploreFragment.newInstance();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }
}
