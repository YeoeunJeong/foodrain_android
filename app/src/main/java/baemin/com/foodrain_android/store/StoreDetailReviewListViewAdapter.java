package baemin.com.foodrain_android.store;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.adapter.FRBaseAdapter;
import baemin.com.foodrain_android.network.ImageGenerator;
import baemin.com.foodrain_android.vo.Image;
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

        holder.userNicknameTv.setText(list.get(position).getUser().getNickname());
        holder.reviewDateTv.setText(list.get(position).getCreated_at());
        holder.ratingBar.setRating(list.get(position).getGrade());
        holder.reviewContentTv.setText(list.get(position).getDetail());


        // user image
        if (list.get(position).getUser().getImage() != null) {
            ImageGenerator.getInstance().createImageService(
                    list.get(position).getUser().getImage().getUrl(),
                    holder.userIv);
        } else {
            holder.userIv.setImageResource(R.drawable.ready);
        }

        // review image
        if (list.get(position).getImages().size() != 0) {
            for (Image image : list.get(position).getImages()) {
                holder.reviewIvLayout.addView(setReviewImage(image));
            }
        }

        return convertView;
    }

    private ImageView setReviewImage(Image image) {
        ImageView reviewIv = new ImageView(context);
        ImageGenerator.getInstance().createImageService(image.getUrl(), reviewIv);
        reviewIv.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                ));
        return reviewIv;
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

        @Bind(R.id.store_detail_review_img_layout)
        LinearLayout reviewIvLayout;

        @Bind(R.id.store_detail_review_content)
        TextView reviewContentTv;

        public StoreDetailReviewViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
