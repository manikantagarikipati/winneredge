package com.winneredge.materialnavigationdrawers.adapter;

/**
 * Created by Manikanta on 12/12/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.winneredge.materialnavigationdrawers.fragments.ToolsFragment;
import com.winneredge.materialnavigationdrawers.fragments.TutorialsFragment;

import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {


    private List<String> mFragmentTitles;
    public HomeViewPagerAdapter(FragmentManager fm,List<String> mFragmentTitles) {
        super(fm);
        this.mFragmentTitles = mFragmentTitles;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                return TutorialsFragment.newInstance();
            case 1:
                return ToolsFragment.newInstance();
            default:
                return TutorialsFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}

