package baemin.com.foodrain_android.home;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.network.UserService;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.util.SharedPreference;
import baemin.com.foodrain_android.vo.AccessToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaunchActivity extends AppCompatActivity {
    private AccessToken mAccessToken;
    //    private GoogleApiClient mGoogleApiClient;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        removeRegionPreferences();
        requestUpdateAccessToken();
        mHandler.postDelayed(mUpdateTimeTask, 1000);


    }

    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };


    private void removeRegionPreferences() {
        SharedPreference
                .getInstance(this)
                .removePreference(Constants.PREF_REGION_NAME_KEY);

        SharedPreference
                .getInstance(this)
                .removePreference(Constants.PREF_REGION_LONGITUDE_KEY);

        SharedPreference
                .getInstance(this)
                .removePreference(Constants.PREF_REGION_LATITUDE_KEY);

        SharedPreference
                .getInstance(this)
                .removePreference(Constants.PREF_REGION_STATUS_KEY);
    }

    private boolean requestUpdateAccessToken() {

        String accessToken = SharedPreference
                .getInstance(this)
                .getStringPreference(Constants.PREF_USER_ACCESS_TOKEN);

        if (accessToken.equals("null")) {
            return false;
        } else {
            UserService userService = ServiceGenerator.getInstance().getUserService();
            Call<AccessToken> accessTokenCall = userService.updateAuthentication(accessToken);
            accessTokenCall.enqueue(mCallback);
            return true;
        }
    }

    private Callback<AccessToken> mCallback = new Callback<AccessToken>() {
        @Override
        public void onResponse(Response<AccessToken> response) {
            mAccessToken = response.body();
            SharedPreference
                    .getInstance(LaunchActivity.this)
                    .removePreference(Constants.PREF_USER_ACCESS_TOKEN);

            SharedPreference.getInstance(LaunchActivity.this)
                    .putStringPreference(Constants.PREF_USER_ACCESS_TOKEN, mAccessToken.getAccess_token());
        }

        @Override
        public void onFailure(Throwable t) {

        }
    };

    private void createGoogleAPIClient() {
        // Create an instance of GoogleAPIClient.
//        if (mGoogleApiClient == null) {
//            mGoogleApiClient = new GoogleApiClient.Builder(this)
//                    .addConnectionCallbacks(this)
//                    .addOnConnectionFailedListener(this)
//                    .addApi(LocationServices.API)
//                    .build();
//        }
    }

}
