package baemin.com.foodrain_android.store;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.ImageGenerator;
import baemin.com.foodrain_android.network.ServiceGenerator;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.util.WindowSize;
import baemin.com.foodrain_android.vo.Image;
import baemin.com.foodrain_android.vo.Menu;
import baemin.com.foodrain_android.vo.Store;
import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreDetailMenuFragment extends Fragment {
    private Activity mActivity;
    private Store mStore;
    private List<Menu> mMenus;

    private int width;
    @Bind(R.id.store_detail_menu_layout)
    LinearLayout menuLayout;

    //    public static StoreDetailMenuFragment newInstance(List<Menu> menus) {
    public static StoreDetailMenuFragment newInstance(Store store) {
        StoreDetailMenuFragment storeDetailMenuFragment = new StoreDetailMenuFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.STORE_SERIALIZABLE, store);
        storeDetailMenuFragment.setArguments(bundle);

        return storeDetailMenuFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStore = (Store) getArguments().getSerializable(Constants.STORE_SERIALIZABLE);
        mMenus = mStore.getMenus();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_detail_menu, container, false);
        ButterKnife.bind(this, view);

        mActivity = getActivity();
        width = new WindowSize(mActivity).getWindowWidth();

        if (mMenus.size() == 0) {
            ImageView noticeIv = new ImageView(getContext());
            noticeIv.setImageResource(R.drawable.ready_menu);
            noticeIv.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
            menuLayout.addView(noticeIv);

//            TextView noticeTv = new TextView(getContext());
//            noticeTv.setText("메뉴 이미지 준비중입니다. 개봉박두!");
//            noticeTv.setLayoutParams(
//                    new LinearLayout.LayoutParams(
//                            LinearLayout.LayoutParams.WRAP_CONTENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT));
//            menuLayout.addView(noticeTv);
        } else {
            for (Menu menu : mMenus) {
                addMenuImage(menu);
            }
        }
        return view;
    }

    @Bind(R.id.store_detail_menu_iv)
    ImageView stdMenuIv;

    private void addMenuImage(Menu menu) {
        ImageView menuIv = new ImageView(getContext());

//        ImageGenerator.getInstance().createMenuImageService(menu.getUrl(), stdMenuIv);
        menuIv.setLayoutParams(
                new ViewGroup.LayoutParams(
////                        700, 700
//                        ((View) getView().getParent()).getWidth(),
//                        ((View) getView().getParent()).getHeight()
                        width,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
//        ImageGenerator.getInstance().createImageService(menu.getUrl(), menuIv);
        ImageGenerator.getInstance().createMenuImageService(width, menu.getUrl(), menuIv);
//        ImageGenerator.getInstance().createImageService(menu.getUrl(), menuIv, ((View) getView().getParent()).getWidth(), 0);
        menuLayout.addView(menuIv);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }
}
