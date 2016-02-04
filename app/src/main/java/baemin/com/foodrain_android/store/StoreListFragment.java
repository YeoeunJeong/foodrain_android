package baemin.com.foodrain_android.store;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.network.StoreService;
import baemin.com.foodrain_android.region.RegionSettingActivity;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.util.SharedPreference;
import baemin.com.foodrain_android.vo.Store;
import baemin.com.foodrain_android.vo.Stores;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StoreListFragment extends Fragment {
    private Activity mActivity;
    private List<Store> mStoreList;
    private Stores mStores;
    private int mCategoryId;
    private boolean mIsRequesting = false;
    private int mStoreTotalCount = 0;
    private StoreListViewAdapter mStoreListAdapter;
    private int mPage = 1;
    private double mLongitude, mLatitude;

    @Bind(R.id.store_list_listview)
    ListView listView;

    public static StoreListFragment newInstance(int categoryId) {
        StoreListFragment storeListFragment = new StoreListFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.CATEGORY_ID, categoryId);
        storeListFragment.setArguments(bundle);

        return storeListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryId = getArguments().getInt(Constants.CATEGORY_ID, -1);
        mActivity = getActivity();

        this.mLongitude = SharedPreference.getInstance(getContext()).getDoublePreference(Constants.PREF_REGION_LONGITUDE_KEY);
        this.mLatitude = SharedPreference.getInstance(getContext()).getDoublePreference(Constants.PREF_REGION_LATITUDE_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        ButterKnife.bind(this, view);


        mPage = 1;
        mStoreList = new ArrayList<>();
        mStoreListAdapter = new StoreListViewAdapter(mActivity, mStoreList);

        if (mCategoryId != -1) {
            requestStores(mCategoryId, mPage);
        }

        return view;
    }

    private void requestStores(int categoryId, int page) {
        mIsRequesting = true;
        StoreService storeService = ServiceGenerator.getInstance().getStoreService();

        Call<Stores> storesCall = storeService.getStores(categoryId, page, mLongitude, mLatitude);
        storesCall.enqueue(mCallBack);
    }

    private Callback<Stores> mCallBack = new Callback<Stores>() {
        @Override
        public void onResponse(Response<Stores> response) {
            mStores = response.body();

            if (mStoreList.isEmpty()) {
                mStoreList.addAll(mStores.getRows());
                listView.setAdapter(mStoreListAdapter);
            } else {
                mStoreList.addAll(mStores.getRows());
                mStoreListAdapter.notifyDataSetChanged();
            }

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(mActivity, StoreDetailActivity.class);

                    intent.putExtra(Constants.STORE_ID, mStoreList.get(position).getId());
                    intent.putExtra(Constants.STORE_NAME, mStoreList.get(position).getName());

                    startActivity(intent);
                }
            });

            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (!isRequesting() && (mPage < mStoreTotalCount) && (firstVisibleItem + visibleItemCount == totalItemCount)) {
                        requestStores(mCategoryId, ++mPage);
                    }
                }
            });

            mStoreTotalCount = response.body().getTotal();
            mIsRequesting = false;

        }

        @Override
        public void onFailure(Throwable t) {
            mIsRequesting = false;

            new AlertDialog.Builder(mActivity)
                    .setMessage("매장 목록을 가져오지 못했습니다.")
                    .setPositiveButton("확인", null)
                    .show();
        }

    };

    private boolean isRequesting() {
        return mIsRequesting;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }
}
