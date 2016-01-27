package baemin.com.foodrain_android.setting;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.adapter.FRBaseAdapter;
import baemin.com.foodrain_android.vo.Region;
import butterknife.Bind;
import butterknife.ButterKnife;

public class RegionListViewAdapter extends FRBaseAdapter<Region> {
    public RegionListViewAdapter(Context context, List<Region> regions) {
        super(context, regions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RegionListViewHolder holder;

        if (convertView != null) {
            holder = (RegionListViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.row_region_list_item, parent, false);
            holder = new RegionListViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.regionNameTv.setText(list.get(position).getName());
        return convertView;
    }

    public static class RegionListViewHolder {
        @Bind(R.id.region_list_item_name)
        TextView regionNameTv;

        @Bind(R.id.region_list_item_cancel_btn)
        ImageButton cancelBtn;

        public RegionListViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
