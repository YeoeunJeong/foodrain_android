package baemin.com.foodrain_android.store;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import baemin.com.foodrain_android.vo.Category;

public class StoreListViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTabTitles;
    private Fragment[] mFragment = null;

    // 테스트
    public StoreListViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mTabTitles = new String[]{"치킨", "중국집", "피자", "칰칰", "냠냠", "룰루", "퇴근", "야근", "옹가도시락", "키친"};
    }

    public StoreListViewPagerAdapter(FragmentManager fm, List<Category> categories) {
        super(fm);
        mTabTitles = new String[categories.size()];
        mFragment = new Fragment[categories.size()];

        int i = 0;
        for (Category category : categories) {
            mTabTitles[i] = category.getName();
            mFragment[i++] = new StoreListFragment(category.getId());
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
