package baemin.com.foodrain_android.store;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.ReviewService;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Review;
import baemin.com.foodrain_android.vo.Reviews;
import baemin.com.foodrain_android.vo.Store;
import baemin.com.foodrain_android.vo.Stores;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreDetailReviewFragment extends Fragment {
    private Activity mActivity;
    private List<Review> mReviewList;
    private Reviews mReviews;
    private int mStoreId;

    @Bind(R.id.store_detail_review_listview)
    ListView listView;

    public StoreDetailReviewFragment() {
    }

    public StoreDetailReviewFragment(int mStoreId) {
        this.mStoreId = mStoreId;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_detail_review, container, false);
        ButterKnife.bind(this, view);
        mActivity = getActivity();

        getReviews();

        return view;
    }

    private void getReviews() {
        ReviewService reviewService = ServiceGenerator.getInstance().gerReviews();
        Call<Reviews> ReviewsCall = reviewService.getReviews(mStoreId);
        Log.i("StoreDetailReviewFrag", "" + this.mStoreId);
        ReviewsCall.enqueue(mCallBack);
    }

    private Callback<Reviews> mCallBack = new Callback<Reviews>() {
        @Override
        public void onResponse(Response<Reviews> response) {
            mReviews = response.body();
            mReviewList = mReviews.getRows();

            listView.setAdapter(new StoreDetailReviewListViewAdapter(mActivity, mReviewList));
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
