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

/**
 * Created by cherry on 18-6-9.
 */

public class CoachListItemAdapter extends RecyclerView.Adapter<CoachListItemAdapter.ViewHolder>{
    private List<CoachListItem> coachListItemList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView coachImage;
        TextView coachName;
        TextView coachPride;
        TextView coachTag;
        TextView coachDescription;
        RatingBar coachRatingBar;
        TextView coachRating;

        public ViewHolder(View view){
            super(view);
            coachImage = view.findViewById(R.id.coach_list_imageId);
            coachName = view.findViewById(R.id.coach_list_name);
            coachPride = view.findViewById(R.id.coach_list_pride);
            coachTag = view.findViewById(R.id.coach_list_tag);
            coachDescription = view.findViewById(R.id.coach_list_description);
            coachRatingBar = view.findViewById(R.id.coach_list_ratingbar);
            coachRating = view.findViewById(R.id.coach_list_ratings);
        }
    }

    public CoachListItemAdapter(List<CoachListItem> coachListItemList) {
        this.coachListItemList = coachListItemList;
    }

    @Override
    public CoachListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.coach_list_item,parent,false);
        CoachListItemAdapter.ViewHolder holder = new CoachListItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CoachListItemAdapter.ViewHolder holder, int position) {
        CoachListItem coachListItem = coachListItemList.get(position);
        //使用Glide加载图片，当前使用本地资源代替
        Glide.with(context).load(coachListItem.getImageId()).into(holder.coachImage);
        holder.coachName.setText(coachListItem.getName());
        holder.coachPride.setText(coachListItem.getPride());
        holder.coachTag.setText(coachListItem.getTag());
        holder.coachDescription.setText(coachListItem.getDescription());
        holder.coachRatingBar.setRating(coachListItem.getRating());
        holder.coachRating.setText(""+coachListItem.getRating());

    }

    @Override
    public int getItemCount() {
        return coachListItemList.size();
    }
}
