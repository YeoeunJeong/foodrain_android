package baemin.com.foodrain_android.store;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.region.RegionSettingActivity;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.util.SharedPreference;
import baemin.com.foodrain_android.vo.Category;
import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreListActivity extends AppCompatActivity {

    private ActionBar mActionBar;
    private StoreListViewPagerAdapter mStoreListPagerAdapter;
    private Double mLongitude, mLatitude;

    @Bind(R.id.store_list_viewpager)
    ViewPager viewPager;

    @Bind(R.id.store_list_tablayout)
    SlidingTabLayout slidingTabLayout;

    @Bind(R.id.store_list_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        ButterKnife.bind(this);

        setActionBar();

        boolean regionCheck = SharedPreference.getInstance(this).getBooleanPreference(Constants.PREF_REGION_STATUS_KEY);

        if (!regionCheck) {
            new AlertDialog.Builder(StoreListActivity.this)
                    .setMessage("매장 목록을 가져오려면 위치 설정이 필요합니다")
                    .setPositiveButton("위치 설정하기", mDialogClickListener)
                    .setNegativeButton("취소", mDialogClickListener)
                    .show();
        } else {
            mLongitude = SharedPreference.getInstance(this).getDoublePreference(Constants.PREF_REGION_LONGITUDE_KEY);
            mLatitude = SharedPreference.getInstance(this).getDoublePreference(Constants.PREF_REGION_LATITUDE_KEY);

            Intent intent = getIntent();
            setTab(intent);
        }
    }

    private DialogInterface.OnClickListener mDialogClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                startActivity(new Intent(StoreListActivity.this, RegionSettingActivity.class));
            }
            finish();
        }
    };


    private void setActionBar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setTab(Intent intent) {
        List<Category> mCategories;
        int categorySelectedPosition;

        if (intent.getBundleExtra(Constants.CATEGORY_BUNDLE) != null) {
            mCategories = (List) intent.getBundleExtra(Constants.CATEGORY_BUNDLE)
                    .getSerializable(Constants.CATEGORY_SERIALIZABLE);
            categorySelectedPosition = Integer.parseInt(intent.getStringExtra(Constants.CATEGORY_SELECTED_POSITION));

            mStoreListPagerAdapter = new StoreListViewPagerAdapter(getSupportFragmentManager(), mCategories);
            mActionBar.setTitle(mStoreListPagerAdapter.getPageTitle(categorySelectedPosition));

            viewPager.setAdapter(mStoreListPagerAdapter);
            viewPager.setCurrentItem(categorySelectedPosition);
            viewPager.addOnPageChangeListener(mOnPageChangeListener);

            slidingTabLayout.setDistributeEvenly(true);
            slidingTabLayout.setViewPager(viewPager);
        } else {
            Toast.makeText(StoreListActivity.this, "카테고리를 선택해주세요", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mActionBar.setTitle(mStoreListPagerAdapter.getPageTitle(position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if
    }
}
