package baemin.com.foodrain_android.store;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import baemin.com.foodrain_android.vo.Store;

public class StoreDetailViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTabTitles = new String[3];
    private Fragment[] mFragment;

    public StoreDetailViewPagerAdapter(FragmentManager fm, Store store) {
        super(fm);
        mTabTitles = getTabTitles(store.getReview_count());

        mFragment = new Fragment[getCount()];
        mFragment[0] = new StoreDetailMenuFragment().newInstance(store);
        mFragment[1] = new StoreDetailInfoFragment().newInstance(store);
        mFragment[2] = new StoreDetailReviewFragment().newInstance(store.getId(), store.getReview_count());
    }

    private String[] getTabTitles(int reviewCount) {
        String[] titles = new String[getCount()];
        titles[0] = "메뉴";
        titles[1] = "정보";
        titles[2] = "리뷰(" + reviewCount +")";

        return titles;
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
