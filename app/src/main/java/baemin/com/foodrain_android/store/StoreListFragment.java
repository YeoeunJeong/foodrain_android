package baemin.com.foodrain_android.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.network.StoreService;
import baemin.com.foodrain_android.util.Constants;
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

    @Bind(R.id.store_list_listview)
    ListView listView;

    public StoreListFragment(int categoryId) {
        this.mCategoryId = categoryId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        ButterKnife.bind(this, view);

        mActivity = getActivity();

        getStores();
        return view;
    }

    private void getStores() {
        StoreService storeService = ServiceGenerator.getInstance().getStores();

        Intent intent = mActivity.getIntent();

        // page, categoryId, regionId, longitude, latitude
        Call<Stores> storesCall = storeService.getStores(mCategoryId);
        storesCall.enqueue(mCallBack);
    }

    private Callback<Stores> mCallBack = new Callback<Stores>() {
        @Override
        public void onResponse(Response<Stores> response) {
            mStores = response.body();
            mStoreList = mStores.getRows();

            listView.setAdapter(new StoreListViewAdapter(mActivity, mStoreList));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(mActivity, StoreDetailActivity.class);
                    intent.putExtra(Constants.STORE_ID, mStoreList.get(position).getId());
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onFailure(Throwable t) {
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }
}
