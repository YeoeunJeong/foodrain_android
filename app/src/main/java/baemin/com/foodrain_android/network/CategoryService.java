package baemin.com.foodrain_android.network;

import java.util.List;

import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CategoryService {
    @GET(Constants.URL_PATH_GET_CATEGORIES)
    Call<List<Category>> getCategories();
}
