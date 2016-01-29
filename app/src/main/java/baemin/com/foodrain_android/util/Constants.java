package baemin.com.foodrain_android.util;

public class Constants {
    private Constants() {
    }

    // URL setting - ServiceGenerator
    public static final String LOCAL_URL = "http://10.10.0.54:9090/api/";
    public static final String BASE_URL = "http://14.63.160.86:3000 ";

    // putExtra key
    public static final String CATEGORY_SELECTED_POSITION = "CATEGORY_SELECTED_POSITION";
    public static final String CATEGORY_SERIALIZABLE = "CATEGORY_SERIALIZABLE";
    public static final String CATEGORY_BUNDLE = "CATEGORY_BUNDLE";
    public static final String CATEGORY_ID = "CATEGORY_ID";
    public static final String STORE_ID = "STORE_ID";

    public static final String URL_PATH_GET_CATEGORIES = "categorycodes.json";
    public static final String URL_PATH_GET_STORES = "stores.json";
    public static final String URL_PATH_GET_REGIONS = "regioncodes.json";
    public static final String URL_PATH_GET_REVIEWS = "stores/{id}/reviews.json";
    public static final String URL_PATH_GET_TEMP_REVIEWS = "reviews.json";


    public static final String PREF = "PREF";
    public static final String PREF_REGION = "PREF_REGION";
}
