package com.example.android.capstone;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;


/**
 * Created by DELL on 12/12/2016.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Discover", "Explore" };
    private Context context;
    private NetworkUtilities networkUtilities;

    public PagerAdapter(FragmentManager fm, Context context,NetworkUtilities networkUtilities) {
        super(fm);
        this.context = context;
        this.networkUtilities=networkUtilities;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (!networkUtilities.isInternetConnectionPresent()){
        return NoInternetFragment.newInstance();
        }
        else {
            if (position == 0)
                return DiscoverFragment.newInstance();
            else
                return ExploreFragment.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }
}
