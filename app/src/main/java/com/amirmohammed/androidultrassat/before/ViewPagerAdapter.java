package com.amirmohammed.androidultrassat.before;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) return new ChatsFragment();
        if (position == 1) return new StatusFragment();
        if (position == 2) return new CallsFragment();
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "Chats";
        if (position == 1) return "Status";
        if (position == 2) return "Calls";
        return super.getPageTitle(position);
    }
}
