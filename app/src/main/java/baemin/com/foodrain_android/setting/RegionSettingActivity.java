package baemin.com.foodrain_android.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import baemin.com.foodrain_android.util.SharedPreference;
import baemin.com.foodrain_android.vo.Region;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionSettingActivity extends AppCompatActivity {
    private List<Region> mRegions;

    @Bind(R.id.region_toolbar)
    Toolbar toolbar;

    @Bind(R.id.region_edit)
    EditText regionEt;

    @Bind(R.id.region_no_result)
    TextView noResultTv;

    @Bind(R.id.region_listview)
    ListView listView;

    @OnClick(R.id.region_search_btn)
    public void okOnClick(View view) {
        String inputName = regionEt.getText().toString();
        getSearchedRegionList(inputName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_setting);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    private void getSearchedRegionList(String inputName) {

        RegionService regionService = ServiceGenerator.getInstance().getRegions();

        Call<List<Region>> regionListCall = regionService.getRegions(inputName);
        regionListCall.enqueue(mCallback);

    }

    Callback<List<Region>> mCallback = new Callback<List<Region>>() {
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
                Log.i("RegionSettingAct", "NO RESULT");
                regionEt.setText("");
            } else {
                noResultTv.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                listView.setAdapter(new RegionListViewAdapter(RegionSettingActivity.this, mRegions));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.i("RegionSettingAct", mRegions.get(position).getName());
                        SharedPreference.getInstance(RegionSettingActivity.this).putRegion(mRegions.get(position).getName());
                        finish();
                    }
                });
            }

        }

        @Override
        public void onFailure(Throwable t) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
