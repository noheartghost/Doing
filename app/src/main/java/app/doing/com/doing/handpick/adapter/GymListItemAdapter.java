package app.doing.com.doing.handpick.adapter;

import android.app.Activity;
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
import app.doing.com.doing.handpick.ListActivity;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.handpick.item.ListItem;

import static app.doing.com.doing.utils.GlobalVariable.GlobalVariable.BASE_URL;
import static app.doing.com.doing.utils.GlobalVariable.GlobalVariable.GYM_PHOTO;

/**
 * Created by cherry on 18-6-9.
 * 对应健身场馆页list
 */

public class GymListItemAdapter extends RecyclerView.Adapter<GymListItemAdapter.ViewHolder> {
    private List<ListItem> listItemList;
    private Context context;
    private Class<?> to;//设置跳转到的Activity

    static class ViewHolder extends RecyclerView.ViewHolder{
        View gymItem;//保存子项最外层布局
        ImageView gymImage;
        TextView gymName;
        TextView gymTag;
        TextView gymDescription;
        RatingBar gymRatingBar;
        TextView gymRating;

        public ViewHolder(View view){
            super(view);
            gymItem = view;
            gymImage = view.findViewById(R.id.gym_list_imageId);
            gymName = view.findViewById(R.id.gym_list_name);
            gymTag = view.findViewById(R.id.gym_list_tag);
            gymDescription = view.findViewById(R.id.gym_list_description);
            gymRatingBar = view.findViewById(R.id.gym_list_ratingbar);
            gymRating = view.findViewById(R.id.gym_list_ratings);
        }
    }

    public GymListItemAdapter(List<ListItem> listItemList) {
        this.listItemList = listItemList;
    }

    /*
    设置应跳转的activity
     */
    public void setJumpActivity(Class<?> to){
        this.to = to;
    }

    @Override

    public GymListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.gym_list_item,parent,false);
        final GymListItemAdapter.ViewHolder holder = new GymListItemAdapter.ViewHolder(view);
        //设置点击item项跳转到相应activity界面
        holder.gymItem.setOnClickListener(new View.OnClickListener() {
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
    public void onBindViewHolder(GymListItemAdapter.ViewHolder holder, int position) {
        ListItem listItem = listItemList.get(position);
        if(listItem instanceof GymListItem){
            GymListItem gymListItem = (GymListItem) listItem;
            //使用Glide加载图片，当前使用本地资源代替
            Glide.with(context).load(BASE_URL+GYM_PHOTO+"?gymid="+gymListItem.getImageId())
                    .placeholder(R.drawable.square_placeholder).into(holder.gymImage);
            holder.gymName.setText(gymListItem.getName());
            holder.gymTag.setText(gymListItem.getTag());
            holder.gymDescription.setText(gymListItem.getDescription());
            DecimalFormat decimalFormat=new DecimalFormat(".0");//格式化为1位小数
            holder.gymRatingBar.setRating((float) gymListItem.getRating());
            holder.gymRating.setText(decimalFormat.format(gymListItem.getRating()));
            holder.gymItem.setTag(position);//将position保存在gymItem的Tag中，点击时获取
        }

    }

    @Override
    public int getItemCount() {
        return listItemList.size();
    }
}
