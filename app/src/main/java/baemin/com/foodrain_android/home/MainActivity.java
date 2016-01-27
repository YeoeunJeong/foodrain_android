package baemin.com.foodrain_android.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.CategoryService;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.setting.RegionSettingActivity;
import baemin.com.foodrain_android.store.StoreListActivity;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.util.SharedPreference;
import baemin.com.foodrain_android.vo.Category;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Intent intent;
    private List<Category> mCategories;

    @Bind(R.id.main_toolbar)
    Toolbar toolbar;

    @Bind(R.id.main_layout_content)
    View contentLayout;

    @Bind(R.id.content_main_gv_category)
    GridView categoryGridView;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.main_test_in_content_main_xml)
    TextView regionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        getCategoryList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String regionName = SharedPreference.getInstance(MainActivity.this).getRegion();
        if (regionName != null) {
            regionTv.setText(regionName);
        }
    }

    private void getCategoryList() {
        CategoryService categoryService = ServiceGenerator.getInstance().getCategories();

        Call<List<Category>> categoryListCall = categoryService.getCategories();
        categoryListCall.enqueue(mCallback);
    }

    private Callback<List<Category>> mCallback = new Callback<List<Category>>() {
        @Override
        public void onResponse(Response<List<Category>> response) {
            mCategories = response.body();

            categoryGridView.setAdapter(new CategoryGridAdapter(MainActivity.this, mCategories));
            categoryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, StoreListActivity.class);
                    ArrayList<Category> arrayList = (ArrayList) mCategories;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.CATEGORY_SERIALIZABLE, arrayList);
                    intent.putExtra(Constants.CATEGORY_BUNDLE, bundle);
                    intent.putExtra(Constants.CATEGORY_SELECTED_POSITION, String.valueOf(position));
                    intent.putExtra(Constants.CATEGORY_ID, String.valueOf(mCategories.get(position).getId()));

                    startActivity(intent);
                }
            });
        }

        @Override
        public void onFailure(Throwable t) {
            Log.i("test3", t.getMessage());
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_favorites) {
        } else if (id == R.id.nav_notice) {
        } else if (id == R.id.nav_reviews) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_location_icon) {
            Intent intent = new Intent(MainActivity.this, RegionSettingActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
