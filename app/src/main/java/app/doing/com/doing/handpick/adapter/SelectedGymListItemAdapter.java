package app.doing.com.doing.handpick.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.CoachListItem;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.handpick.item.ListItem;

/**
 * Created by cherry on 18-6-12.
 */

public class SelectedGymListItemAdapter extends RecyclerView.Adapter<SelectedGymListItemAdapter.ViewHolder>{
    private List<ListItem> listItemList;
    private Context context;

    public SelectedGymListItemAdapter(List<ListItem> listItemList) {
        this.listItemList = listItemList;
    }

    @Override
    public SelectedGymListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.selected_gym_list_item,parent,false);
        SelectedGymListItemAdapter.ViewHolder holder = new SelectedGymListItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItemList.get(position);
        if(listItem instanceof GymListItem){
            GymListItem gymListItem = (GymListItem) listItem;
            //使用Glide加载图片，当前使用本地资源代替
            Glide.with(context).load(gymListItem.getImageId()).into(holder.image);
            holder.name.setText(gymListItem.getName());
            holder.address.setText(gymListItem.getAddress());
            holder.ratingBar.setRating(gymListItem.getRating());
            holder.rating.setText(""+gymListItem.getRating());
            holder.evaluatedNum.setText(gymListItem.getViews()+"条");
            holder.distance.setText(gymListItem.getDistance()+"m");

        }
    }

    @Override
    public int getItemCount() {
        return listItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView address;
        RatingBar ratingBar;
        TextView rating;
        TextView evaluatedNum;
        TextView distance;

        public ViewHolder(View view){
            super(view);
            image = view.findViewById(R.id.selected_gym_list_imageId);
            name = view.findViewById(R.id.selected_gym_list_name);
            address = view.findViewById(R.id.selected_gym_list_address);
            ratingBar = view.findViewById(R.id.selected_gym_list_ratingbar);
            rating = view.findViewById(R.id.selected_gym_list_ratings);
            evaluatedNum = view.findViewById(R.id.selected_gym_list_views);
            distance = view.findViewById(R.id.selected_gym_list_distance);
        }
    }

}
