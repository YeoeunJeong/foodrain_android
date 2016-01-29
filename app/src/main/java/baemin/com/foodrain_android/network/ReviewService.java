package baemin.com.foodrain_android.network;

import java.util.List;

import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Category;
import baemin.com.foodrain_android.vo.Reviews;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReviewService {
//    @GET(Constants.URL_PATH_GET_REVIEWS)
//    Call<Reviews> getReviews(@Path("id") int id);

    @GET(Constants.URL_PATH_GET_TEMP_REVIEWS)
    Call<Reviews> getReviews(@Query("store_id") int store_id);
}
