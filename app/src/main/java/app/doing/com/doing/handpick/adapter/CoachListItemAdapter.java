package app.doing.com.doing.handpick.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.CoachItem;
import app.doing.com.doing.handpick.item.CoachListItem;
import app.doing.com.doing.handpick.item.ListItem;

/**
 * Created by cherry on 18-6-9.
 *健身教练页list
 */

public class CoachListItemAdapter extends RecyclerView.Adapter<CoachListItemAdapter.ViewHolder> {
    private List<ListItem> listItemList;
    private Context context;
    private Class<?> to;//设置跳转到的Activity

    static class ViewHolder extends RecyclerView.ViewHolder{
        View item;//保存子项最外层布局
        ImageView coachImage;
        TextView coachName;
        TextView coachPride;
        TextView coachTag;
        TextView coachDescription;
        RatingBar coachRatingBar;
        TextView coachRating;

        public ViewHolder(View view){
            super(view);
            item = view;
            coachImage = view.findViewById(R.id.coach_list_imageId);
            coachName = view.findViewById(R.id.coach_list_name);
            coachPride = view.findViewById(R.id.coach_list_pride);
            coachTag = view.findViewById(R.id.coach_list_tag);
            coachDescription = view.findViewById(R.id.coach_list_description);
            coachRatingBar = view.findViewById(R.id.coach_list_ratingbar);
            coachRating = view.findViewById(R.id.coach_list_ratings);
        }
    }

    public CoachListItemAdapter(List<ListItem> listItemList) {
        this.listItemList = listItemList;
    }

    /*
   设置应跳转的activity
    */
    public void setJumpActivity(Class<?> to){
        this.to = to;
    }

    @Override
    public CoachListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.coach_list_item,parent,false);
        final CoachListItemAdapter.ViewHolder holder = new CoachListItemAdapter.ViewHolder(view);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != to){
                    int position = holder.getAdapterPosition();
                    Intent intent = new Intent(context,to);
                    CoachListItem coachListItem = (CoachListItem) listItemList.get(position);
                    intent.putExtra("coachId",coachListItem.getImageId());//传入教练id
                    context.startActivity(intent);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(CoachListItemAdapter.ViewHolder holder, int position) {
        ListItem listItem = listItemList.get(position);
        if(listItem instanceof CoachListItem){
            CoachListItem coachListItem = (CoachListItem) listItem;
            //使用Glide加载图片，当前使用本地资源代替
            Glide.with(context).load(coachListItem.getImageId())
                    .placeholder(R.drawable.square_placeholder).into(holder.coachImage);
            holder.coachName.setText(coachListItem.getName());
            holder.coachPride.setText(coachListItem.getPride());
            holder.coachTag.setText(coachListItem.getTag());
            holder.coachDescription.setText(coachListItem.getDescription());
            holder.coachRatingBar.setRating(coachListItem.getRating());
            DecimalFormat decimalFormat=new DecimalFormat(".0");//格式化为1位小数
            holder.coachRating.setText(decimalFormat.format(coachListItem.getRating()));

        }

    }

    @Override
    public int getItemCount() {
        return listItemList.size();
    }
}
