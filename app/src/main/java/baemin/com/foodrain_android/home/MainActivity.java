package baemin.com.foodrain_android.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.CategoryService;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.network.UserService;
import baemin.com.foodrain_android.region.RegionSettingActivity;
import baemin.com.foodrain_android.store.StoreListActivity;
import baemin.com.foodrain_android.store.StoreReviewActivity;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.util.SharedPreference;
import baemin.com.foodrain_android.vo.Category;
import baemin.com.foodrain_android.vo.UserWithAccessToken;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private List<Category> mCategories;
    private UserWithAccessToken mUserWithAccessToken;
    private View mHeaderView;
    private String mAccesToken;
    private EditText mEmailEt;
    private EditText mPasswordEt;
    private TextView mSignInCheckTv;

    @Bind(R.id.main_toolbar)
    Toolbar toolbar;

    @Bind(R.id.content_main_gv_category)
    GridView categoryGridView;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.main_test_in_content_main_xml)
    TextView regionTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setActionBar();

        mCategories = new ArrayList<>();
        navigationView.setNavigationItemSelectedListener(this);


        mAccesToken = SharedPreference.getInstance(this).getStringPreference(Constants.PREF_USER_ACCESS_TOKEN);
        if (mAccesToken.equals("null")) {
            setBeforeSigninNavigationDrawer();
        } else {
            setAfterSigninNavigationDrawer();
        }
        requestCategoryList();
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void setBeforeSigninNavigationDrawer() {
        if (mHeaderView != null) {
            navigationView.removeHeaderView(mHeaderView);
        }
        mHeaderView = navigationView.inflateHeaderView(R.layout.nav_before_sign_in);

        mEmailEt = (EditText) mHeaderView.findViewById(R.id.nav_email_et);
        mPasswordEt = (EditText) mHeaderView.findViewById(R.id.nav_pw_et);
        mSignInCheckTv = (TextView) mHeaderView.findViewById(R.id.nav_check_tv);
        mSignInCheckTv.setVisibility(View.INVISIBLE);

        TextView signUpBtn = (TextView) mHeaderView.findViewById(R.id.nav_sign_up_tv);
        signUpBtn.setOnClickListener(mSignUpOnClick);

        Button signInBtn = (Button) mHeaderView.findViewById(R.id.nav_sign_in_btn);
        signInBtn.setOnClickListener(mSignInOnClick);
    }


    private void setAfterSigninNavigationDrawer() {
        if (mHeaderView != null) {
            navigationView.removeHeaderView(mHeaderView);
        }
        mHeaderView = navigationView.inflateHeaderView(R.layout.nav_after_sign_in);
        TextView emailTv = (TextView) mHeaderView.findViewById(R.id.nav_email_tv);
        TextView nicknameTv = (TextView) mHeaderView.findViewById(R.id.nav_nickname_tv);

        emailTv.setText(SharedPreference.getInstance(MainActivity.this)
                .getStringPreference(Constants.PREF_USER_EMAIL));

        String nickname = SharedPreference.getInstance(MainActivity.this)
                .getStringPreference(Constants.PREF_USER_NICKNAME);
        if (!nickname.equals("null")) {
            nicknameTv.setText(SharedPreference.getInstance(MainActivity.this).getStringPreference(Constants.PREF_USER_EMAIL));
        } else {
            nicknameTv.setVisibility(View.INVISIBLE);
        }
        TextView signOutBtn = (TextView) mHeaderView.findViewById(R.id.nav_sign_out_btn_tv);
        signOutBtn.setOnClickListener(mSignOutOnClick);
    }

    private View.OnClickListener mSignUpOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivityForResult(intent, Constants.REQUEST_CODE_FROM_MAIN_TO_SIGNUP);
        }
    };

    private View.OnClickListener mSignInOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestSignIn(mEmailEt.getText().toString(), mPasswordEt.getText().toString());

        }
    };

    private void requestSignIn(String email, String password) {
        UserService userService = ServiceGenerator.getInstance().getUserService();
        Call<UserWithAccessToken> userWithAccessTokenCall = userService.signIn(email, password);
        userWithAccessTokenCall.enqueue(mUserCallback);
    }

    private Callback<UserWithAccessToken> mUserCallback = new Callback<UserWithAccessToken>() {
        @Override
        public void onResponse(Response<UserWithAccessToken> response) {
            mUserWithAccessToken = response.body();
            if (mUserWithAccessToken != null) {
                setAfterSigninNavigationDrawer();
                SharedPreference.getInstance(MainActivity.this)
                        .putStringPreference(Constants.PREF_USER_ACCESS_TOKEN, mUserWithAccessToken.getAccess_token());
                onBackPressed();
                Toast.makeText(MainActivity.this, "로그인 하였습니다", Toast.LENGTH_SHORT).show();
            } else {
                mSignInCheckTv.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onFailure(Throwable t) {

            Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mSignOutOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreference.getInstance(MainActivity.this).removePreference(Constants.PREF_USER_ACCESS_TOKEN);
            SharedPreference.getInstance(MainActivity.this).removePreference(Constants.PREF_USER_EMAIL);
            SharedPreference.getInstance(MainActivity.this).removePreference(Constants.PREF_USER_NICKNAME);

            setBeforeSigninNavigationDrawer();
            onBackPressed();
            Toast.makeText(MainActivity.this, "로그아웃 하였습니다", Toast.LENGTH_SHORT).show();
        }
    };

    private boolean checkWhiteSpace(String email, String password) {
        boolean result = false;

        if (StringUtils.isWhitespace(email) || StringUtils.isWhitespace(password)) {
            mSignInCheckTv.setVisibility(View.VISIBLE);
        } else {
            mSignInCheckTv.setVisibility(View.INVISIBLE);
            result = true;
        }

        return result;
    }

    @Override
    protected void onResume() {
        super.onResume();
        String regionName = SharedPreference.getInstance(this).getStringPreference(Constants.PREF_REGION_NAME_KEY);
        if (regionName != null && !regionName.equals("null")) {
            regionTv.setVisibility(View.VISIBLE);
            regionTv.setText(regionName);
        } else {
            regionTv.setVisibility(View.GONE);
        }
    }

    private void requestCategoryList() {
        CategoryService categoryService = ServiceGenerator.getInstance().getCategoryService();

        Call<List<Category>> categoryListCall = categoryService.getCategories();
        categoryListCall.enqueue(mCategoriesCallback);
    }

    private Callback<List<Category>> mCategoriesCallback = new Callback<List<Category>>() {
        @Override
        public void onResponse(Response<List<Category>> response) {

            mCategories.addAll(response.body());
//            mCategories = response.body();

            categoryGridView.setAdapter(new CategoryGridAdapter(MainActivity.this, mCategories));
            categoryGridView.setOnItemClickListener(mOnItemClickListener);
        }

        private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, StoreListActivity.class);
                ArrayList<Category> arrayList = (ArrayList) mCategories;
                Bundle bundle = new Bundle();

                bundle.putSerializable(Constants.CATEGORY_SERIALIZABLE, arrayList);
                intent.putExtra(Constants.CATEGORY_BUNDLE, bundle);
                intent.putExtra(Constants.CATEGORY_SELECTED_POSITION, String.valueOf(position));
                intent.putExtra(Constants.CATEGORY_ID, String.valueOf(mCategories.get(position).getId()));

                startActivity(intent);
            }
        };

        @Override
        public void onFailure(Throwable t) {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("서버 연결 실패. 다시 시도해 주십시오")
                    .setPositiveButton("확인", null)
                    .show();
        }
    };

    private void editTextRequestFocus(EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_notice) {
        } else if (id == R.id.nav_reviews) {
        } else if (id == R.id.nav_favorites) {
            Intent intent = new Intent(MainActivity.this, StoreReviewActivity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_region_setting_icon) {
            Intent intent = new Intent(MainActivity.this, RegionSettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_FROM_MAIN_TO_SIGNUP) {
            if (resultCode == MainActivity.RESULT_OK) {
                setAfterSigninNavigationDrawer();
                onBackPressed();
            } else if (resultCode == MainActivity.RESULT_CANCELED) {
                setBeforeSigninNavigationDrawer();
                onBackPressed();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
