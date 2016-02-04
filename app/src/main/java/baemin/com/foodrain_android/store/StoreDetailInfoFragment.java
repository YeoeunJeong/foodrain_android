package baemin.com.foodrain_android.store;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.network.StoreService;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Store;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StoreDetailInfoFragment extends Fragment {

    private Activity mActivity;
    private int mStoreId;
    private Store mStore;

    @Bind(R.id.store_detail_info_viewpager)
    ViewPager viewPager;

    @Bind(R.id.store_detail_info_start_time)
    TextView startTimeTv;

    @Bind(R.id.store_detail_info_end_time)
    TextView endTimeTv;

    @Bind(R.id.store_detail_info_holiday)
    TextView holidayTv;

    @Bind(R.id.store_detail_info_address)
    TextView addressTv;

    @Bind(R.id.store_detail_info_phone)
    TextView phoneTv;

    @Bind(R.id.store_detail_info_detail)
    TextView detailTv;

    public static StoreDetailInfoFragment newInstance(Store store) {
        StoreDetailInfoFragment storeDetailInfoFragment = new StoreDetailInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.STORE_SERIALIZABLE, store);
        storeDetailInfoFragment.setArguments(bundle);

        return storeDetailInfoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStore = (Store) getArguments().getSerializable(Constants.STORE_SERIALIZABLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_detail_info, container, false);
        ButterKnife.bind(this, view);

        if (mStore != null) {
            setStoreInfo(mStore, savedInstanceState);
        }
        return view;
    }

    private void setStoreInfo(Store store, Bundle savedInstanceState) {
        startTimeTv.setText(store.getStart_time());
        endTimeTv.setText(store.getEnd_time());
        holidayTv.setText(store.getHoliday());
        addressTv.setText(store.getAddress());
        phoneTv.setText(store.getPhone());
        detailTv.setText(store.getDetail());

        if (store.getImages().size() != 0) {
            viewPager.setVisibility(View.VISIBLE);
            viewPager.setAdapter(new StoreDetailInfoViewPagerAdapter(getLayoutInflater(savedInstanceState), store.getImages()));
        }
    }

}
