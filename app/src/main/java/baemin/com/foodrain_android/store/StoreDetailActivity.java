package baemin.com.foodrain_android.store;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.util.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreDetailActivity extends AppCompatActivity {
    @Bind(R.id.store_detail_toolbar)
    Toolbar toolbar;

    @Bind(R.id.store_detail_viewpager)
    ViewPager viewPager;

    @Bind(R.id.store_detail_tablayout)
    SlidingTabLayout slidingTabLayout;


//    @OnClick(R.id.fab)
//    void fabOnClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_36dp);
//        getSupportActionBar().setTitle("매장 상세");

        Intent intent = getIntent();
        Log.i("StoreDetailActivity", "" + intent.getIntExtra(Constants.STORE_ID, 0));

        viewPager.setAdapter(new StoreDetailViewPagerAdapter(getSupportFragmentManager(), intent.getIntExtra(Constants.STORE_ID, 0)));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(0xFFDCBAC7);
        slidingTabLayout.setTextColor(getResources().getColorStateList(R.drawable.tab_selector));
        slidingTabLayout.setViewPager(viewPager);
    }


}
