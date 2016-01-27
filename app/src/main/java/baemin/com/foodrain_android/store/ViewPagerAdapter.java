package baemin.com.foodrain_android.store;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

import baemin.com.foodrain_android.vo.Category;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] tabTitles;
    Fragment[] fragment = null;

    // 테스트
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        tabTitles = new String[]{"치킨", "중국집", "피자", "칰칰", "냠냠", "룰루", "퇴근", "야근", "옹가도시락", "키친"};
    }

    public ViewPagerAdapter(FragmentManager fm, List<Category> categories) {
        super(fm);
        tabTitles = new String[categories.size()];
        fragment = new Fragment[categories.size()];

        int i = 0;
        for (Category category : categories) {
            tabTitles[i] = category.getName();
            fragment[i++] = new StoreListFragment(category.getId());
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragment[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
