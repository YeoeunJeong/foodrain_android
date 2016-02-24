package baemin.com.foodrain_android.store;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.network.StoreService;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Store;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreDetailActivity extends AppCompatActivity {
    private ActionBar mActionBar;
    private int mStoreId;
    private String mStoreName;
    private String mStorePhoneNumber;

    @Bind(R.id.store_detail_toolbar)
    Toolbar toolbar;

    @Bind(R.id.store_detail_viewpager)
    ViewPager viewPager;

    @Bind(R.id.store_detail_tablayout)
    SlidingTabLayout slidingTabLayout;

    @Bind(R.id.store_detail_grade_avg)
    TextView gradeAverageTv;

    @Bind(R.id.store_detail_ratingbar)
    RatingBar ratingBar;

    @Bind(R.id.store_detail_review_count_tv)
    TextView reviewCountTv;

    @OnClick(R.id.store_detail_phone_btn)
    public void mOnClick(View v) {
        if (v.getId() == R.id.store_detail_phone_btn) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mStorePhoneNumber));
//            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mStorePhoneNumber));
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        setStoreIdName(intent);
        setActionBar();

        getStore(mStoreId);
    }

    private void setStoreIdName(Intent intent) {
        mStoreId = intent.getIntExtra(Constants.STORE_ID, 0);
        mStoreName = intent.getStringExtra(Constants.STORE_NAME);
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(true);
        mActionBar.setTitle(mStoreName);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getStore(int storeId) {
        StoreService storeService = ServiceGenerator.getInstance().getStoreService();
        Call<Store> storeCall = storeService.getStoreInfo(storeId);
        storeCall.enqueue(mCallBack);
    }

    private Callback<Store> mCallBack = new Callback<Store>() {
        @Override
        public void onResponse(Response<Store> response) {
            Store store = response.body();
            if (store != null) {
                setBottomBar(store);
                setTab(store);
            } else {
                alertDialog().show();
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Log.e("Fail", t.getMessage());
            t.printStackTrace();
        }
    };

    private void setBottomBar(Store store) {
        float gradeAverage = store.getGrade_average();
        ratingBar.setRating(gradeAverage);
        gradeAverageTv.setText(String.valueOf(gradeAverage));

        reviewCountTv.setText(String.valueOf(store.getReview_count()));
        mStorePhoneNumber = store.getPhone();
    }

    private void setTab(Store store) {
        viewPager.setAdapter(
                new StoreDetailViewPagerAdapter(
                        getSupportFragmentManager(), store));

        slidingTabLayout.setDistributeEvenly(true);
//        slidingTabLayout.setSelectedIndicatorColors(0xFFDCBAC7);
        slidingTabLayout.setViewPager(viewPager);
    }

    private AlertDialog.Builder alertDialog() {
        return new AlertDialog.Builder(StoreDetailActivity.this)
                .setMessage("잠시후 다시 시도해주세요")
                .setPositiveButton("나가기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }
                );
    }
}
