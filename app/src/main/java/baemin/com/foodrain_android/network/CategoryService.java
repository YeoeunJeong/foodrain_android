package baemin.com.foodrain_android.network;

import java.util.List;

import baemin.com.foodrain_android.vo.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by elite on 16. 1. 25..
 */
public interface CategoryService {
    @GET("categorycodes.json")
    Call<List<Category>> getCategories();
}
