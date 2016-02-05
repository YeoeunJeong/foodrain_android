package baemin.com.foodrain_android.network;

import java.util.List;

import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Category;
import baemin.com.foodrain_android.vo.Review;
import baemin.com.foodrain_android.vo.Reviews;
import baemin.com.foodrain_android.vo.UserWithAccessToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReviewService {
//    @GET(Constants.URL_PATH_GET_REVIEWS)
//    Call<Reviews> getReviews(@Path("id") int id);

    @GET(Constants.URL_PATH_GET_STORE_REVIEWS)
    Call<Reviews> getReviews(@Path("id") int id, @Query("page") int page);

    /*
   access_token
(required)
로그인 된 사용자임을 인증하는 키값입니다.
store_id
(required)
수정하는 리뷰의 매장번호입니다.
grade
(required)
수정하고자하는 평점 값입니다.
detail
(required)
수정하고자하는 글 입니다.
images
(optional)
     */

    @FormUrlEncoded
    @POST(Constants.URL_PATH_POST_REVIEW)
    Call<Review> postReview(
            @Path("id") int id,
            @Field("access_token") String access_token,
            @Field("grade") float grade,
            @Field("detail") String detail
    );
}
