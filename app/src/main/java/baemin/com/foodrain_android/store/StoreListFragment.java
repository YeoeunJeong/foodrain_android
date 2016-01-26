package baemin.com.foodrain_android.store;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import baemin.com.foodrain_android.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class StoreListFragment extends Fragment {
    Activity activity;

    @OnClick(R.id.fragment_btn)
    public void onClick(View v) {
        Toast.makeText(activity, "test", Toast.LENGTH_SHORT).show();
    }

    private static int total = 0;
    private int mColor;
    private Random mRandom = new Random(System.currentTimeMillis());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = new Bundle();
        if (args != null) {
            mColor = mRandom.nextInt(0xffffff) | 0xff000000;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        ButterKnife.bind(this, view);
        view.setBackgroundColor(mColor);
        Log.i("Fragment", "this is fragment");
        activity = getActivity();

        return view;
    }
}
