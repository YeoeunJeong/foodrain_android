package baemin.com.foodrain_android.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.adapter.FRBaseAdapter;
import baemin.com.foodrain_android.vo.Category;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryGridAdapter extends FRBaseAdapter<Category> {


    public CategoryGridAdapter(Context context, List<Category> categories) {
        super(context, categories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FRGridViewHolder holder;

        if (convertView != null) {
            holder = (FRGridViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.column_category_list_item, parent, false);
            holder = new FRGridViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.mCategoryTv.setText(list.get(position).getName());
        holder.mCategoryIv.setImageResource(getCategoryImageResource(list.get(position).getId()));

        return convertView;
    }

    private int getCategoryImageResource(int categoryId) {
        int resId;

        switch (categoryId) {
            case 1:
                resId = R.drawable.cate_chicken_1;
                break;
            case 2:
                resId = R.drawable.cate_pizza_1;
                break;
            case 3:
                resId = R.drawable.cate_chinese_1;
                break;
            case 4:
                resId = R.drawable.cate_bossam_1;
                break;
            case 5:
                resId = R.drawable.cate_korean_1;
                break;
            case 6:
                resId = R.drawable.cate_night_1;
                break;
            case 7:
                resId = R.drawable.cate_soup_1;
                break;
            case 8:
                resId = R.drawable.cate_japanese_1;
                break;
            case 9:
                resId = R.drawable.cate_lunchbox_1;
                break;
            case 10:
                resId = R.drawable.cate_fastfood_1;
                break;
            case 11:
                resId = R.drawable.cate_etc_1;
                break;
            default:
                resId = R.drawable.ready;
        }
        return resId;
    }

    public static class FRGridViewHolder {
        @Bind(R.id.column_category_list_iv)
        ImageView mCategoryIv;

        @Bind(R.id.column_category_list_btn)
        TextView mCategoryTv;

        public FRGridViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
