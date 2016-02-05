package baemin.com.foodrain_android.store;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.adapter.FRBaseAdapter;
import baemin.com.foodrain_android.network.ImageGenerator;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.vo.Store;
import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreListViewAdapter extends FRBaseAdapter<Store> {
    public StoreListViewAdapter(Context context, List<Store> stores) {
        super(context, stores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StoreListViewHolder holder;

        if (convertView != null) {
            holder = (StoreListViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.row_store_list_item, parent, false);
            holder = new StoreListViewHolder(convertView);
            convertView.setTag(holder);
        }

        // image는 그냥 test
//        holder.storeIv.setImageResource(R.drawable.main_category_pizza);
        holder.ratingBar.setRating(list.get(position).getGrade_average());
        holder.storeNameTv.setText(list.get(position).getName());
        holder.reviewCountsTv.setText(String.valueOf(list.get(position).getReview_count()));

        if (list.get(position).getImages().size() != 0) {
            Log.i("position", "" + position);

            ImageGenerator.getInstance().createImageService(
                    list.get(position).getImages().get(0).getUrl(), holder.storeIv);
//       ImageGenerator.getInstance().createImageService(
//                    list.get(position).getImages().get(0).getUrl(), holder.storeIv, 0, holder.storeIv.getHeight());
        } else {
//            holder.storeIv.setImageResource(R.drawable.ready);
            ImageGenerator.getInstance().createImageService(
                    null, holder.storeIv);
        }

        return convertView;
    }

    public static class StoreListViewHolder {
        @Bind(R.id.store_list_item_store_img)
        ImageView storeIv;

        @Bind(R.id.store_list_item_store_ratingbar)
        RatingBar ratingBar;

        @Bind(R.id.store_list_item_store_name)
        TextView storeNameTv;

        @Bind(R.id.store_list_item_store_review_cnt)
        TextView reviewCountsTv;

        public StoreListViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
