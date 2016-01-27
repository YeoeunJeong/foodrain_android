package baemin.com.foodrain_android.store;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Category;
import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreListActivity extends AppCompatActivity {
    @Bind(R.id.store_list_viewpager)
    ViewPager mViewPager;
    @Bind(R.id.store_list_tablayout)
    SlidingTabLayout mSlidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        List<Category> mCategories = (List) intent.getBundleExtra(Constants.CATEGORY_BUNDLE)
                .getSerializable(Constants.CATEGORY_SERIALIZABLE);
        int categorySelectedPosition = Integer.parseInt(intent.getStringExtra(Constants.CATEGORY_SELECTED_POSITION));


        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mCategories));
        mViewPager.setCurrentItem(categorySelectedPosition);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mViewPager);
    }


}
