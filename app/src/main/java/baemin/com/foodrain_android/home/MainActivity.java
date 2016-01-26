package baemin.com.foodrain_android.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.CategoryService;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.store.StoreListActivity;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Category;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    List<Category> mCategories;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_layout_content)
    View contentLayout;
    @Bind(R.id.content_main_gv_category)
    GridView categoryGridView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @OnClick(R.id.fab)
    void fabOnClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

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
        mCategories = new ArrayList<>();
//        addDumpData();
        // getCategoryList();
        testJson();

        categoryGridView.setAdapter(new FRGridAdapter(MainActivity.this, mCategories));
        categoryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, StoreListActivity.class);
                Log.i(Constants.CATEGORY_ID, mCategories.get(position).getId() + "");
                intent.putExtra(Constants.CATEGORY_ID, String.valueOf(mCategories.get(position).getId()));
                startActivity(intent);
            }
        });
    }

    private void testJson() {
        mCategories.clear();
        String jsonString = "[{\"id\":1, \"name\": \"치킨\" }," +
                "{\"id\":2, \"name\":\"중국집\"}," +
                "{\"id\":3, \"name\":\"피자\"}," +
                "{\"id\":4, \"name\":\"족발/보쌈\"}]";
        mCategories = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            String result = "";
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject cate = jsonArray.getJSONObject(i);
                mCategories.add(new Category(Integer.parseInt(cate.getString("id")), cate.getString("name")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getCategoryList() {
        mCategories.clear();
        CategoryService categoryService = ServiceGenerator.getInstance().getCategories();
        Call<List<Category>> categoryListCall;
        Log.i("test1", "here");
        categoryListCall = categoryService.getCategories();
        categoryListCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Response<List<Category>> response) {
                mCategories = response.body();
                for (Category category : mCategories) {
                    Log.i("test2", category.getName());
//                    mCategories.add(category);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("test3", t.getMessage());
            }
        });

    }


    private void addDumpData() {
        mCategories.clear();
        mCategories.add(new Category(1, "치킨"));
        mCategories.add(new Category(2, "중국"));
        mCategories.add(new Category(3, "피자"));
        mCategories.add(new Category(4, "족발/보쌈"));
        mCategories.add(new Category(5, "한식/분식"));
        mCategories.add(new Category(6, "일식"));
        mCategories.add(new Category(7, "야식"));
        mCategories.add(new Category(8, "배민라이더스"));
        mCategories.add(new Category(9, "결제"));
        mCategories.add(new Category(10, "점심시가안"));
    }

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
        // Handle navigation view item clicks here.
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
