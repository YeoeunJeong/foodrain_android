package baemin.com.foodrain_android.store;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import baemin.com.foodrain_android.R;
import butterknife.ButterKnife;

public class StoreDetailMenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_store_detail_menu, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
