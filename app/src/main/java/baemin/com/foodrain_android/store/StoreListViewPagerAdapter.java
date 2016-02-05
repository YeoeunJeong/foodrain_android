package baemin.com.foodrain_android.store;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import baemin.com.foodrain_android.vo.Category;

public class StoreListViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTabTitles;
    private Fragment[] mFragment = null;

    public StoreListViewPagerAdapter(FragmentManager fm, List<Category> categories) {
        super(fm);
        mTabTitles = new String[categories.size()];
        mFragment = new Fragment[categories.size()];

        int i = 0;
        for (Category category : categories) {
            mTabTitles[i] = category.getName();
            mFragment[i++] = StoreListFragment.newInstance(category.getId());
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
