package baemin.com.foodrain_android.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.adapter.FRBaseAdapter;
import baemin.com.foodrain_android.vo.Category;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by elite on 16. 1. 25..
 */
public class FRGridAdapter extends FRBaseAdapter<Category> {
    public FRGridAdapter(Context context, List<Category> categories) {
        super(context, categories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FRGridViewHolder holder;
        if (convertView != null) {
            holder = (FRGridViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.column_category_list, parent, false);
            holder = new FRGridViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.mCategoryTv.setText(mList.get(position).getName());


        return convertView;
    }

    public static class FRGridViewHolder {
        @Bind(R.id.column_category_list_btn_name)
        TextView mCategoryTv;

        public FRGridViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
