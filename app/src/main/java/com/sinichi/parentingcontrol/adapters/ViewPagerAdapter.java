package com.sinichi.parentingcontrol.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sinichi.parentingcontrol.R;
import com.sinichi.parentingcontrol.activities.MainActivity;
import com.sinichi.parentingcontrol.fragments.ChatFragment;
import com.sinichi.parentingcontrol.fragments.LocationFragment;
import com.sinichi.parentingcontrol.fragments.OverviewFragment;
import com.sinichi.parentingcontrol.fragments.ProfileFragment;

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
                return new ChatFragment();
            case 3:
                return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

}
