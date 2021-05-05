package com.example.realmadrid.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private int numPage = 4;

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, int behavior) {
        super(fragmentManager, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentAdd();
            case 2:
                return new FragmentSearch();
            case 3:
                return new FragmentNotifications();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
