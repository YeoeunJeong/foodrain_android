package baemin.com.foodrain_android.network;

import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.AccessToken;
import baemin.com.foodrain_android.vo.UserWithAccessToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {
    @FormUrlEncoded
    @POST(Constants.URL_PATH_POST_USER_SIGNUP)
    Call<UserWithAccessToken> signUp(
            @Field("email") String email
            , @Field("password") String password
            , @Field("nickname") String nickname
            , @Field("phone") String phone
            , @Field("gender") int gender
    );

    @FormUrlEncoded
    @POST(Constants.URL_PATH_POST_USER_SIGNIN)
    Call<UserWithAccessToken> signIn(
            @Field("email") String email
            , @Field("password") String password
    );

    @FormUrlEncoded
    @POST(Constants.URL_PATH_POST_USER_AUTHENTICATION)
    Call<AccessToken> updateAuthentication(
            @Field("access_token") String access_token
    );
}
