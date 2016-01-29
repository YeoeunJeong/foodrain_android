package baemin.com.foodrain_android.store;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.adapter.FRBaseAdapter;
import baemin.com.foodrain_android.vo.Review;
import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreDetailReviewListViewAdapter extends FRBaseAdapter<Review> {
    public StoreDetailReviewListViewAdapter(Context context, List<Review> reviews) {
        super(context, reviews);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StoreDetailReviewViewHolder holder;

        if (convertView != null) {
            holder = (StoreDetailReviewViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.row_store_detail_review_list_item, parent, false);
            holder = new StoreDetailReviewViewHolder(convertView);
            convertView.setTag(holder);
        }

//        holder.userIv.setImageResource();
        holder.userNicknameTv.setText(list.get(position).getUser().getNickname());
        holder.reviewDateTv.setText(list.get(position).getCreated_at().substring(0, 10));
        holder.ratingBar.setRating(list.get(position).getGrade());
        holder.reviewContentTv.setText(list.get(position).getDetail());

        return convertView;
    }

    public static class StoreDetailReviewViewHolder {
        @Bind(R.id.store_detail_review_user_img)
        ImageView userIv;

        @Bind(R.id.store_detail_review_user_nickname)
        TextView userNicknameTv;

        @Bind(R.id.store_detail_review_date)
        TextView reviewDateTv;

        @Bind(R.id.store_detail_review_ratingbar)
        RatingBar ratingBar;

        @Bind(R.id.store_detail_review_content)
        TextView reviewContentTv;

        public StoreDetailReviewViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
