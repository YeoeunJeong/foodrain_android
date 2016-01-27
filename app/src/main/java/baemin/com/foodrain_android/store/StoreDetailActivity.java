package baemin.com.foodrain_android.store;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import baemin.com.foodrain_android.R;
import butterknife.OnClick;

public class StoreDetailActivity extends AppCompatActivity {

    @OnClick(R.id.fab)
    void fabOnClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
    }
}
