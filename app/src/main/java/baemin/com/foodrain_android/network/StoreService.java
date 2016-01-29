package baemin.com.foodrain_android.network;

import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Store;
import baemin.com.foodrain_android.vo.Stores;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StoreService {
    /*
    page=1&    		       //페이지 	(null이거나 0일 경우 전체)
    category=1&                 //카테고리
    region=11&
    longitude=127.111211&
    latitude=37.135612
    */

    @GET(Constants.URL_PATH_GET_STORES)
    Call<Stores> getTotalStores();

    @GET(Constants.URL_PATH_GET_STORES)
    Call<Stores> getStores(@Query("category") int categoryId);

    @GET(Constants.URL_PATH_GET_STORES)
    Call<Store> getStoreDetail(@Path("id") int id);

//    @GET(Constants.URL_PATH_GET_STORES)
//    Call<Stores> getStores(
//            @Query("page") String page
//            , @Query("category") String categoryId
//            , @Query("region") String regionId
//            , @Query("longitude") String longitude
//            , @Query("latitude") String latitude
//    );
}
