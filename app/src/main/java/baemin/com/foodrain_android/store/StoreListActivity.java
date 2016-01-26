package baemin.com.foodrain_android.store;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import baemin.com.foodrain_android.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreListActivity extends AppCompatActivity {
    @Bind(R.id.store_list_viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        ButterKnife.bind(this);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }
}
