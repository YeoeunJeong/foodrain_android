package baemin.com.foodrain_android.network;

import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Review;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class ServiceGenerator {
    private static ServiceGenerator instance;

    private ServiceGenerator() {
    }

    public static ServiceGenerator getInstance() {
        if (instance == null) {
            instance = new ServiceGenerator();
        }
        return instance;
    }

    private <T> T createService(Class<T> serviceClass) {
        return new Retrofit.Builder()
                .baseUrl(Constants.LOCAL_URL)
//                .baseUrl(Constants.LOCAL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(serviceClass);
    }

    public CategoryService getCategoryService() {
        return createService(CategoryService.class);
    }

    public StoreService getStoreService() {
        return createService(StoreService.class);
    }

    public RegionService getRegionService() {
        return createService(RegionService.class);
    }

    public ReviewService getReviewService() {
        return createService(ReviewService.class);
    }

    public UserService getUserService() {
        return createService(UserService.class);
    }
}
