package baemin.com.foodrain_android.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.network.UserService;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.util.SharedPreference;
import baemin.com.foodrain_android.vo.User;
import baemin.com.foodrain_android.vo.UserWithAccessToken;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private ActionBar mActionBar;
    private UserWithAccessToken mUserWithAccessToken;

    @Bind(R.id.sign_up_toolbar)
    Toolbar toolbar;
    @Bind(R.id.sign_up_email_et)
    EditText emailEt;
    @Bind(R.id.sign_up_pw_et)
    EditText passwordEt;
    @Bind(R.id.sign_up_pw_check_et)
    EditText passwordCheckEt;
    @Bind(R.id.sign_up_nickname_et)
    EditText nicknameEt;
    @Bind(R.id.sign_up_phone1_et)
    EditText phone1Et;
    @Bind(R.id.sign_up_phone2_et)
    EditText phone2Et;
    @Bind(R.id.sign_up_phone3_et)
    EditText phone3Et;
    @Bind(R.id.sign_up_radio_group)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        setActionBar();
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(true);
        mActionBar.setTitle("회원 가입");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick(R.id.sign_up_ok_btn)
    public void signOnClick() {
        String phoneNum =
                phone1Et.getText().toString()
                        + phone2Et.getText().toString()
                        + phone3Et.getText().toString();


        if (checkEmail() && checkPassword() && checkPhoneType(phoneNum)) {
            requestSignUp(
                    emailEt.getText().toString(),
                    passwordEt.getText().toString(),
                    nicknameEt.getText().toString(),
                    phoneNum,
                    getCheckedGender()
            );
        }
    }

    private void requestSignUp(String email, String password, String nickname, String phone, int gender) {
        UserService userService = ServiceGenerator.getInstance().getUserService();
        Call<UserWithAccessToken> userWithAccessTokenCall = userService.signUp(
                email, password, checkStringNull(nickname), checkStringNull(phone), gender);

        userWithAccessTokenCall.enqueue(mCallback);
    }

    private Callback<UserWithAccessToken> mCallback = new Callback<UserWithAccessToken>() {
        @Override
        public void onResponse(Response<UserWithAccessToken> response) {
            mUserWithAccessToken = response.body();
            if (mUserWithAccessToken != null) {
                SharedPreference.getInstance(SignUpActivity.this).putStringPreference(
                        Constants.PREF_USER_ACCESS_TOKEN,
                        mUserWithAccessToken.getAccess_token());

                SharedPreference.getInstance(SignUpActivity.this).putStringPreference(
                        Constants.PREF_USER_NICKNAME,
                        mUserWithAccessToken.getUser().getNickname());

                SharedPreference.getInstance(SignUpActivity.this).putStringPreference(
                        Constants.PREF_USER_EMAIL,
                        mUserWithAccessToken.getUser().getEmail()
                );

                Toast.makeText(SignUpActivity.this, "반갑습니다", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                setResult(RESULT_CANCELED);
            }
        }

        @Override
        public void onFailure(Throwable t) {

            Log.i("signup fail", t.getMessage().toString());
        }
    };


    private boolean checkEmail() {
        if (emailEt.getText().toString().equals("")) {
            Toast.makeText(SignUpActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
            editTextRequestFocus(emailEt);
            return false;
        } else {
            return checkEmailType();
        }
    }

    private boolean checkEmailType() {
        String email = emailEt.getText().toString();
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        } else {
            Toast.makeText(SignUpActivity.this, "이메일을 형식을 확인해주세요", Toast.LENGTH_SHORT).show();
            editTextRequestFocus(emailEt);
            return false;
        }
    }

    private boolean checkPassword() {
        String password1 = passwordEt.getText().toString();
        String password2 = passwordCheckEt.getText().toString();

        if (StringUtils.isWhitespace(password1)) {
            Toast.makeText(SignUpActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            editTextRequestFocus(passwordEt);
            return false;
        } else if (password2.equals("")) {
            Toast.makeText(SignUpActivity.this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
            editTextRequestFocus(passwordCheckEt);
            return false;
        } else {
            return checkPasswordCorrect();
        }

    }

    private boolean checkPasswordCorrect() {
        String password1 = passwordEt.getText().toString();
        String password2 = passwordCheckEt.getText().toString();
        if (!password1.equals(password2)) {
            Toast.makeText(SignUpActivity.this, "비밀번호가 맞지 않습니다. 다시 입력해주세요", Toast.LENGTH_SHORT).show();
            passwordEt.setText("");
            passwordCheckEt.setText("");
            editTextRequestFocus(passwordEt);

            return false;
        } else {
            return true;
        }
    }

    private boolean checkPhoneType(String cellphoneNumber) {
        if (cellphoneNumber.equals("")) {
            return true;
        } else {
            String regex = "^\\s*(010|011|012|013|014|015|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(cellphoneNumber);
            if (m.matches()) {
                return true;
            } else {
                phone1Et.setText("");
                phone2Et.setText("");
                phone3Et.setText("");
                Toast.makeText(SignUpActivity.this, "전화번호를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                editTextRequestFocus(phone1Et);
                return false;
            }
        }
    }

    private int getCheckedGender() {
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int idx = radioGroup.indexOfChild(radioButton);

        if (radioGroup.indexOfChild(radioButton) == 0) {
            idx = 1;
        } else if (radioGroup.indexOfChild(radioButton) == 1) {
            idx = 2;
        } else if (radioGroup.indexOfChild(radioButton) == 2) {
            idx = 0;
        }

        return idx;
    }


    private void editTextRequestFocus(EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    private String checkStringNull(String string) {
        if (string.equals("")) {
            return null;
        } else {
            return string;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        new AlertDialog.Builder(SignUpActivity.this)
                .setMessage("정말 나가시겠습니까?")
                .setPositiveButton("나가기", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                })
                .setNegativeButton("머물기", null)
                .show();
    }
}
