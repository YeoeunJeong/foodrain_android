package baemin.com.foodrain_android.store;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.ReviewService;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.util.SharedPreference;
import baemin.com.foodrain_android.vo.Review;
import baemin.com.foodrain_android.vo.Reviews;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreDetailReviewFragment extends Fragment {
    private Activity mActivity;
    private List<Review> mReviewList;
    private Reviews mReviews;
    private int mStoreId;
    private StoreDetailReviewListViewAdapter mReviewListAdapter;
    private boolean mIsRequesting = false;
    private int mStoreTotalCount = 0;
    private int mPage = 1;

    @Bind(R.id.store_detail_review_listview)
    ListView listView;

    @Bind(R.id.store_detail_review_btn)
    Button reviewBtn;

    public static StoreDetailReviewFragment newInstance(int storeId, int reviewCount) {
        StoreDetailReviewFragment storeDetailReviewFragment = new StoreDetailReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.STORE_ID, storeId);
        bundle.putInt(Constants.STORE_REVIEW_CNT, reviewCount);

        storeDetailReviewFragment.setArguments(bundle);
        return storeDetailReviewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_detail_review, container, false);
        ButterKnife.bind(this, view);
        mActivity = getActivity();

        mReviewList = new ArrayList<>();
        mReviewListAdapter = new StoreDetailReviewListViewAdapter(mActivity, mReviewList);
        mPage = 1;
        setReviewBtn(getArguments().getInt(Constants.STORE_REVIEW_CNT, -1));
        mStoreId = getArguments().getInt(Constants.STORE_ID, -1);

        if (mStoreId != -1) {
            requestReviews(mStoreId, mPage);
        }

        return view;
    }

    private void setReviewBtn(int reviewCount) {
        if (reviewCount != -1) {
            reviewBtn.setText((reviewCount + 1) + "번째 리뷰의 주인공이 되어주세요 :) ");
        }
    }

    private void requestReviews(int storeId, int page) {
        mIsRequesting = true;
        Toast.makeText(mActivity, page + "페이지 로딩", Toast.LENGTH_SHORT).show();
        ReviewService reviewService = ServiceGenerator.getInstance().getReviewService();
        Call<Reviews> reviewsCall = reviewService.getReviews(storeId, page);
        reviewsCall.enqueue(mCallBack);
    }

    private Callback<Reviews> mCallBack = new Callback<Reviews>() {
        @Override
        public void onResponse(Response<Reviews> response) {
            mReviews = response.body();
//            mReviewList = mReviews.getRows();

            if (mReviewList.isEmpty()) {
                mReviewList.addAll(mReviews.getRows());
                listView.setAdapter(mReviewListAdapter);
            } else {
                mReviewList.addAll(mReviews.getRows());
                mReviewListAdapter.notifyDataSetChanged();
            }

            listView.setAdapter(mReviewListAdapter);
            mReviewListAdapter.notifyDataSetChanged();
            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (!isRequesting() && (mPage < mStoreTotalCount) && (firstVisibleItem + visibleItemCount == totalItemCount)) {
                        requestReviews(mStoreId, ++mPage);
                    }
                }
            });

            mStoreTotalCount = response.body().getTotal();
            mIsRequesting = false;
        }

        @Override
        public void onFailure(Throwable t) {
            mIsRequesting = false;

            Log.i("review onFailure", t.getMessage().toString());

            new AlertDialog.Builder(mActivity)
                    .setMessage("리뷰 목록을 가져오지 못했습니다.")
                    .setPositiveButton("확인", null)
                    .show();
        }
    };

    private boolean isRequesting() {
        return mIsRequesting;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.store_detail_review_btn)
    public void mOnClick(View v) {
        if (v.getId() == R.id.store_detail_review_btn) {
            String accessToken = SharedPreference.getInstance(mActivity).getStringPreference(Constants.PREF_USER_ACCESS_TOKEN);
            Log.i("detailReviewFragment", accessToken);
            if (!accessToken.equals("null")) {
                Intent intent = new Intent(mActivity, StoreReviewActivity.class);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                startActivityForResult(intent, Constants.REQUEST_CODE_FROM_STORE_DETAIL_REVIEW_FRAGMENT);
            } else {
                new AlertDialog.Builder(mActivity)
                        .setMessage("로그인해주세요")
                        .setPositiveButton("확인", null)
//                        .setPositiveButton("로그인하러가기", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                mActivity.finish();
//                                //
//                            }
//                        })
//                        .setNegativeButton("취소", null)
                        .show();

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_FROM_STORE_DETAIL_REVIEW_FRAGMENT) {
            if (resultCode == mActivity.RESULT_OK) {
                requestReviews(mStoreId, mPage);
            } else if (resultCode == mActivity.RESULT_CANCELED) {

            }
        }
    }
}
