package baemin.com.foodrain_android.store;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.ReviewService;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.util.SharedPreference;
import baemin.com.foodrain_android.vo.Review;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreReviewActivity extends AppCompatActivity {
    private int mStoreId;
    private Review mReview;

    @Bind(R.id.store_review_et)
    EditText reviewEt;

    @Bind(R.id.store_review_ratingBar)
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_review);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mStoreId = intent.getIntExtra(Constants.STORE_ID, -1);

        if (mStoreId == -1) {
            finish();
        }
    }

    @OnClick(R.id.store_review_ok_btn)
    public void reviewOkOnClick() {
        float grade = ratingBar.getRating();
        String detail = reviewEt.getText().toString();
        if (checkWhiteSpace(detail)) {
            requestPostReview(grade, detail);
        }
    }


    private void requestPostReview(float grade, String detail) {
        ReviewService reviewService = ServiceGenerator.getInstance().getReviewService();
        Call<Review> reviewCall = reviewService.postReview(
                mStoreId,
                SharedPreference.getInstance(StoreReviewActivity.this).getStringPreference(Constants.PREF_USER_ACCESS_TOKEN),
                grade,
                detail
        );
        reviewCall.enqueue(mCallback);
    }

    private Callback<Review> mCallback = new Callback<Review>() {
        @Override
        public void onResponse(Response<Review> response) {
            mReview = response.body();
            if (mReview == null) {
                Toast.makeText(StoreReviewActivity.this, "리뷰 작성 실패", Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED);
            } else {
                setResult(RESULT_OK);
            }
            finish();
        }

        @Override
        public void onFailure(Throwable t) {

        }
    };

    private boolean checkWhiteSpace(String detail) {
        boolean result = false;

        if (StringUtils.isWhitespace(detail)) {
            Toast.makeText(StoreReviewActivity.this, "리뷰를 작성해주세요", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String review = reviewEt.getText().toString();
        if (!StringUtils.isWhitespace(review) && review.length() > 0) {
            new AlertDialog.Builder(StoreReviewActivity.this)
                    .setMessage("작성중인 리뷰가 있습니다. 정말 나가시겠습니까?")
                    .setPositiveButton("나가기", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            setResult(RESULT_CANCELED);
                            finish();
                        }
                    })
                    .setNegativeButton("머물기", null)
                    .show();
        }
    }
}
