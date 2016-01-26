package baemin.com.foodrain_android.network;

import java.util.List;

import baemin.com.foodrain_android.vo.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by elite on 16. 1. 25..
 */
public interface StoreService {
    /*page=1&    		       //페이지 	(null이거나 0일경우 전체)
    categorycode_id=1&                 //카테고리
    region_id=11
    */

    @GET("/stores.json")
    Call<List<Category>> getStoreList(@Query("page") String page,
                                       @Query("categorycode_id") String categoryId,
                                       @Query("region_id") String regionId);
}
