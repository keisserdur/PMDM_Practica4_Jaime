package com.example.admin.pmdm_practica4_jaime.fragment;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MiFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private Context c;
    private String tabTitles[] =
            new String[] { "Incoming", "Outgoing",};

    public MiFragmentPagerAdapter(FragmentManager fm,Context c) {
        super(fm);
        this.c=c;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment f = null;

        switch(position) {
            case 0:
                f = Fragment1.newInstance(c);
                break;
            case 1:
                f = Fragment2.newInstance(c);
                break;
        }

        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}