package baemin.com.foodrain_android.network;

import baemin.com.foodrain_android.util.Constants;
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

    //    public SalesStocksService getSalesStocksService() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(IP_ADDR)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit.create(SalesStocksService.class);
//    }
    public <T> T createService(Class<T> serviceClass) {
        return new Retrofit.Builder()
                .baseUrl(Constants.LOCAL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(serviceClass);
    }

    public CategoryService getCategories() {
        return createService(CategoryService.class);
    }
}
