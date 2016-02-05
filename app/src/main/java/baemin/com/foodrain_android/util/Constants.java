package baemin.com.foodrain_android.util;

public class Constants {
    private Constants() {
    }

    // URL setting - ServiceGenerator
    public static final String LOCAL_URL = "http://10.10.0.58:9090/api/";
    public static final String BASE_URL = "http://14.63.160.86:3000 ";

    // StoreListViewAdapter, StoreDetailReviewListViewAdapter, StoreDetailMenuFragment
    public static final String IMAGE_LOCAL_URL = "http://10.10.0.161:3000/";
    public static final String IMAGE_URL = "http://10.10.0.58:9090/";

    // putExtra key
    public static final String CATEGORY_SELECTED_POSITION = "CATEGORY_SELECTED_POSITION";
    public static final String CATEGORY_BUNDLE = "CATEGORY_BUNDLE";
    public static final String CATEGORY_ID = "CATEGORY_ID";
    public static final String STORE_ID = "STORE_ID";
    public static final String STORE_NAME = "STORE_NAME";
    public static final String STORE_REVIEW_CNT = "STORE_REVIEW_CNT";

    // putSerializable key
    public static final String CATEGORY_SERIALIZABLE = "CATEGORY_SERIALIZABLE";
    public static final String STORE_SERIALIZABLE = "STORE_SERIALIZABLE";
    public static final String MENU_SERIALIZABLE = "MENU_SERIALIZABLE";

    // service Query
    public static final String URL_PATH_GET_CATEGORIES = "categories.json";
    public static final String URL_PATH_GET_STORES = "stores.json";
    public static final String URL_PATH_GET_STORE_INFO = "stores/{id}.json";
    public static final String URL_PATH_GET_REGIONS = "regions.json";
    public static final String URL_PATH_GET_STORE_REVIEWS = "stores/{id}/reviews.json";
//    public static final String URL_PATH_GET_TEMP_REVIEWS = "reviews.json";
    public static final String URL_PATH_POST_REVIEW = "stores/{id}/reviews.json";
    public static final String URL_PATH_POST_USER_SIGNUP = "users/signup.json";
    public static final String URL_PATH_POST_USER_SIGNIN = "users/signin.json";
    public static final String URL_PATH_POST_USER_AUTHENTICATION = "users/authentication.json";


    public static final String PREF_NAME = "PREF";
    public static final String PREF_REGION_NAME_KEY = "PREF_REGION_NAME";
    public static final String PREF_REGION_LONGITUDE_KEY = "PREF_REGION_LONGITUDE";
    public static final String PREF_REGION_LATITUDE_KEY = "PREF_REGION_LATITUDE";
    public static final String PREF_REGION_STATUS_KEY = "PREF_REGION_STATUS";
    public static final String PREF_USER_NICKNAME = "PREF_USER_NICKNAME";
    public static final String PREF_USER_EMAIL = "PREF_USER_EMAIL";
    public static final String PREF_USER_ACCESS_TOKEN = "PREF_USER_ACCESS_TOKEN";

    // startActivityForResult requestCode
    public static final int REQUEST_CODE_FROM_REVIEW_LIST_TO_POST = 1;
    public static final int REQUEST_CODE_FROM_MAIN_TO_SIGNUP = 2;

    //message
    public static final String TOAST_ERROR_MESSAGE = "잠시 후 다시 시도해 주세요";
}
