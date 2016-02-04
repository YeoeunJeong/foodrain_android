package baemin.com.foodrain_android.region;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.RegionService;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.util.SharedPreference;
import baemin.com.foodrain_android.vo.Region;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionSettingActivity extends AppCompatActivity {
    private ActionBar mActionBar;
    private List<Region> mRegions;
    private Region mRegion;

    @Bind(R.id.region_toolbar)
    Toolbar toolbar;

    @Bind(R.id.region_edit)
    EditText regionEt;

    @Bind(R.id.region_no_result)
    TextView noResultTv;

    @Bind(R.id.region_listview)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_setting);
        ButterKnife.bind(this);

        actionBarSetting();
    }

    private void actionBarSetting() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(true);
        mActionBar.setTitle("내 위치 설정");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick(R.id.region_search_btn)
    public void okOnClick(View view) {
        String inputName = regionEt.getText().toString();
        requestSearchedRegionList(inputName);
    }

    private void requestSearchedRegionList(String inputName) {
        RegionService regionService = ServiceGenerator.getInstance().getRegionService();

        Call<List<Region>> regionListCall = regionService.getRegions(inputName);
        regionListCall.enqueue(mRegionListCallback);
    }

    Callback<List<Region>> mRegionListCallback = new Callback<List<Region>>() {
        @Override
        public void onResponse(Response<List<Region>> response) {
            mRegions = response.body();
            if (mRegions == null) {
                Toast.makeText(RegionSettingActivity.this, "null from server", Toast.LENGTH_SHORT).show();
                return;
            }

            if (mRegions.size() == 0) {
                noResultTv.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                regionEt.setText("");
            } else {
                noResultTv.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                listView.setAdapter(new RegionListViewAdapter(RegionSettingActivity.this, mRegions));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.i("RegionSettingAct", mRegions.get(position).getAddress());
                        setRegionSharedPreferences(
                                mRegions.get(position).getAddress(),
                                mRegions.get(position).getLocation().getLongitude(),
                                mRegions.get(position).getLocation().getLatitude());
                        finish();
                    }
                });
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Log.i("regionFailure getList", t.getMessage().toString());
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.region_setting_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_get_location_icon) {
            double longitude, latitude;

            if (getCurrentLocation() != null) {
                longitude = getCurrentLocation().getLongitude();
                latitude = getCurrentLocation().getLatitude();

                requestRegionSetting(longitude, latitude);
            } else {
                Toast.makeText(RegionSettingActivity.this, "GPS - 현재 위치를 받아오지 못했습니다", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Location getCurrentLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        return location;
    }

    private void requestRegionSetting(double longitude, double latitude) {
        Log.i("RegionSetting", longitude + ", " + latitude);

        RegionService regionService = ServiceGenerator.getInstance().getRegionService();
        Call<List<Region>> regionCall = regionService.getRegions(longitude, latitude);
        regionCall.enqueue(mRegionCallback);
    }

    private Callback<List<Region>> mRegionCallback = new Callback<List<Region>>() {
        @Override
        public void onResponse(Response<List<Region>> response) {
            mRegions = response.body();
            mRegion = mRegions.get(0);

            if (mRegion != null) {
                Toast.makeText(RegionSettingActivity.this, mRegion.getAddress(), Toast.LENGTH_SHORT).show();
                setRegionSharedPreferences(
                        mRegion.getAddress(),
                        mRegion.getLocation().getLongitude(),
                        mRegion.getLocation().getLatitude());
            } else {
                Toast.makeText(RegionSettingActivity.this, "서버 - 주소를 보내지 못함", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Log.i("regionFailure getGPS", t.getMessage().toString());
        }
    };

    private void setRegionSharedPreferences(String address, double longitude, double latitude) {
        SharedPreference
                .getInstance(RegionSettingActivity.this)
                .putStringPreference(Constants.PREF_REGION_NAME_KEY, address);

        SharedPreference
                .getInstance(RegionSettingActivity.this)
                .putDoublePreference(Constants.PREF_REGION_LONGITUDE_KEY, longitude);


        SharedPreference
                .getInstance(RegionSettingActivity.this)
                .putDoublePreference(Constants.PREF_REGION_LATITUDE_KEY, latitude);

        SharedPreference
                .getInstance(RegionSettingActivity.this)
                .putBooleanPreference(Constants.PREF_REGION_STATUS_KEY, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
