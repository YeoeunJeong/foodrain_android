package baemin.com.foodrain_android.store;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Category;
import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreListActivity extends AppCompatActivity {
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
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        List<Category> mCategories;
        int categorySelectedPosition;
        if (intent.getBundleExtra(Constants.CATEGORY_BUNDLE) != null) {
            mCategories = (List) intent.getBundleExtra(Constants.CATEGORY_BUNDLE)
                    .getSerializable(Constants.CATEGORY_SERIALIZABLE);
            categorySelectedPosition = Integer.parseInt(intent.getStringExtra(Constants.CATEGORY_SELECTED_POSITION));

            viewPager.setAdapter(new StoreListViewPagerAdapter(getSupportFragmentManager(), mCategories));
            viewPager.setCurrentItem(categorySelectedPosition);
            slidingTabLayout.setDistributeEvenly(true);
            slidingTabLayout.setViewPager(viewPager);
        } else {
            Toast.makeText(StoreListActivity.this, "카테고리를 선택해주세요", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


}
