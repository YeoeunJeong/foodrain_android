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
    Call<Stores> getStores(
            @Query("category") int categoryId,
            @Query("page") int page);

    @GET(Constants.URL_PATH_GET_STORES)
    Call<Stores> getStores(
            @Query("category") int categoryId,
            @Query("page") int page,
            @Query("longitude") double longitude,
            @Query("latitude") double latitude);

    @GET(Constants.URL_PATH_GET_STORE_INFO)
    Call<Store> getStoreInfo(@Path("id") int id);

}
