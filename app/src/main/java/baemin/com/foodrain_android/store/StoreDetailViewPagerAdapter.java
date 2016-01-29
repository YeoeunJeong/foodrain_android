package baemin.com.foodrain_android.store;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class StoreDetailViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTabTitles = new String[]{"메뉴", "정보", "리뷰"};
    private int mStoreId;

    public StoreDetailViewPagerAdapter(FragmentManager fm, int storeId) {
        super(fm);
        this.mStoreId = storeId;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                Log.i("DetailViewpagerAdapter", "position " + position);
                fragment = new StoreDetailMenuFragment();
                break;
            case 1:
                fragment = new StoreDetailInfoFragment();
                Log.i("DetailViewpagerAdapter", "position " + position);
                break;
            case 2:
                fragment = new StoreDetailReviewFragment(mStoreId); //
                Log.i("DetailViewpagerAdapter", "position " + position);
                break;
            default:
                break;
        }

        return fragment;
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
