package com.sinichi.parentingcontrol.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sinichi.parentingcontrol.fragments.DataFragment;
import com.sinichi.parentingcontrol.fragments.LocationFragment;
import com.sinichi.parentingcontrol.fragments.OverviewFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OverviewFragment();
            case 1:
                return new LocationFragment();
            case 2:
                return new DataFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
