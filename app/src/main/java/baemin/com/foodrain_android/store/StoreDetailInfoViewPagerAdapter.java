package baemin.com.foodrain_android.store;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.network.ImageGenerator;
import baemin.com.foodrain_android.vo.Image;
import baemin.com.foodrain_android.vo.Menu;

public class StoreDetailInfoViewPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private List<Image> mImages;

    public StoreDetailInfoViewPagerAdapter(LayoutInflater mLayoutInflater, List<Image> images) {
        this.mLayoutInflater = mLayoutInflater;
        this.mImages = images;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = mLayoutInflater.inflate(R.layout.viewpager_childview_store_info, null);
        ImageView imgView = (ImageView) view.findViewById(R.id.viewpager_childview_iv);
        ImageGenerator.getInstance().createImageService(mImages.get(position).getUrl(), imgView);

        container.addView(view);
        return view;
    }


    @Override
    public int getCount() {
        return mImages.size();
    }

    //instantiateItem() 메소드에서 리턴된 Ojbect가 View가  맞는지 확인하는 메소드
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //화면에 보이지 않은 View는파괴를 해서 메모리를 관리함.
    //첫번째 파라미터 : ViewPager
    //두번째 파라미터 : 파괴될 View의 인덱스(가장 처음부터 0,1,2,3...)
    //세번째 파라미터 : 파괴될 객체(더 이상 보이지 않은 View 객체)
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //ViewPager에서 보이지 않는 View는 제거
        //세번째 파라미터가 View 객체 이지만 데이터 타입이 Object여서 형변환 실시
        container.removeView((View) object);
    }
}
