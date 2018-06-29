package app.doing.com.doing.handpick.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import app.doing.com.doing.customView.SelectTabCustom;
import app.doing.com.doing.handpick.item.CoachListItem;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.handpick.item.ListItem;

import static android.content.ContentValues.TAG;
import static app.doing.com.doing.utils.GlobalVariable.GlobalVariable.BASE_URL;
import static app.doing.com.doing.utils.GlobalVariable.GlobalVariable.GYM_PHOTO;

/**
 * Created by cherry on 18-6-12.
 * 精选场馆的适配器
 */

public class SelectedGymListItemAdapter extends RecyclerView.Adapter<SelectedGymListItemAdapter.ViewHolder>{
    private List<ListItem> listItemList;
    private Context context;
    private Class<?> to;//设置跳转到的Activity

    public SelectedGymListItemAdapter(Context context, List<ListItem> listItemList) {
        Log.d(TAG, "Constructor called");
        this.context = context;
        this.listItemList = listItemList;
    }

    /*
    设置应跳转的activity
     */
    public void setJumpActivity(Class<?> to){
        this.to = to;
    }

    @Override
    public SelectedGymListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //context = parent.getContext();
        Log.d(TAG,"onCreateViewHolder called");
        View view = LayoutInflater.from(context)
                .inflate(R.layout.selected_gym_list_item,parent,false);
        final SelectedGymListItemAdapter.ViewHolder holder = new SelectedGymListItemAdapter.ViewHolder(view);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != to){
                    int position = holder.getAdapterPosition();
                    Intent intent = new Intent(context,to);
                    GymListItem gymListItem = (GymListItem) listItemList.get(position);
                    intent.putExtra("gymId",gymListItem.getImageId());//传入场馆id
                    context.startActivity(intent);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder called");
        ListItem listItem = listItemList.get(position);
        if(listItem instanceof GymListItem){
            GymListItem gymListItem = (GymListItem) listItem;
            //使用Glide加载图片，当前使用本地资源代替
            Glide.with(context).load(BASE_URL+ GYM_PHOTO+"?gymid="+gymListItem.getImageId())
                    .placeholder(R.drawable.square_placeholder).into(holder.image);
            holder.name.setText(gymListItem.getName());
            holder.address.setText(gymListItem.getAddress());
            holder.ratingBar.setRating((float) gymListItem.getRating());
            DecimalFormat decimalFormat=new DecimalFormat(".0");//格式化为1位小数
            holder.rating.setText(decimalFormat.format(gymListItem.getRating()));
            holder.evaluatedNum.setText(gymListItem.getViews()+"条");
            holder.distance.setText(gymListItem.getDistance()+"m");

        }
    }

    @Override
    public int getItemCount() {
        Log.d("SelectedGym:size",listItemList.size()+"");
        return listItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View item;//保存子项最外层布局
        ImageView image;
        TextView name;
        TextView address;
        RatingBar ratingBar;
        TextView rating;
        TextView evaluatedNum;
        TextView distance;

        public ViewHolder(View view){
            super(view);
            item = view;
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
