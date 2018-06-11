package app.doing.com.doing.handpick.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.GymItem;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.handpick.item.ListItem;

/**
 * Created by cherry on 18-6-9.
 */

public class GymListItemAdapter extends RecyclerView.Adapter<GymListItemAdapter.ViewHolder> {
    private List<ListItem> listItemList;
    private Context context;


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView gymImage;
        TextView gymName;
        TextView gymDistrict;
        TextView gymAddress;
        RatingBar gymRatingBar;
        TextView gymRating;
        TextView gymViews;
        TextView gymDistance;


        public ViewHolder(View view){
            super(view);
            gymImage = view.findViewById(R.id.gym_list_imageId);
            gymName = view.findViewById(R.id.gym_list_name);
            gymDistrict = view.findViewById(R.id.gym_list_district);
            gymAddress = view.findViewById(R.id.gym_list_address);
            gymRatingBar = view.findViewById(R.id.gym_list_ratingbar);
            gymRating = view.findViewById(R.id.gym_list_ratings);
            gymViews = view.findViewById(R.id.gym_list_views);
            gymDistance = view.findViewById(R.id.gym_list_distance);

        }
    }

    public GymListItemAdapter(List<ListItem> listItemList) {
        this.listItemList = listItemList;
    }

    @Override

    public GymListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.gym_list_item,parent,false);
        GymListItemAdapter.ViewHolder holder = new GymListItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GymListItemAdapter.ViewHolder holder, int position) {
        ListItem listItem = listItemList.get(position);
        if(listItem instanceof GymListItem){
            GymListItem gymListItem = (GymListItem) listItem;
            //使用Glide加载图片，当前使用本地资源代替
            Glide.with(context).load(gymListItem.getImageId()).into(holder.gymImage);
            holder.gymName.setText(gymListItem.getName());
            holder.gymDistrict.setText("（"+gymListItem.getDistrict()+"）");
            holder.gymAddress.setText(gymListItem.getAddress());
            holder.gymRatingBar.setRating(gymListItem.getRating());
            holder.gymRating.setText(""+gymListItem.getRating());
            holder.gymViews.setText(""+gymListItem.getViews()+"条");
            holder.gymDistance.setText(gymListItem.getDistance()+"m");
        }

    }

    @Override
    public int getItemCount() {
        return listItemList.size();
    }
}
