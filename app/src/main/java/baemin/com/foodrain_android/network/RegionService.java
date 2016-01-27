package baemin.com.foodrain_android.network;

import java.util.List;

import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Region;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RegionService {
    @GET (Constants.URL_PATH_GET_REGIONS)
    Call<List<Region>> getRegions(@Query("name") String name);
}
