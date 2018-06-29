package app.doing.com.doing.handpick.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.GymItem;
import app.doing.com.doing.handpick.item.GymListItem;

/**
 * Created by cherry on 18-5-24.
 * 精选页场馆list
 */

public class GymItemAdapter extends RecyclerView.Adapter<GymItemAdapter.ViewHolder> {
    private List<GymItem> gymList;
    private Context context;
    private Class<?> to;//设置跳转到的Activity

    static class ViewHolder extends RecyclerView.ViewHolder{
        View item;//保存子项最外层布局
        ImageView gymItemImage;
        TextView gymItemName;
        TextView gymItemDescription;

        public ViewHolder(View view){
            super(view);
            item = view;
            gymItemImage = view.findViewById(R.id.gym_item_image);
            gymItemName = view.findViewById(R.id.gym_item_name);
            gymItemDescription = view.findViewById(R.id.gym_item_description);
        }
    }

    public GymItemAdapter(List<GymItem> gymList) {
        this.gymList = gymList;
    }

    /*
    设置应跳转的activity
     */
    public void setJumpActivity(Class<?> to){
        this.to = to;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.gym_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != to){
                    int position = holder.getAdapterPosition();
                    Intent intent = new Intent(context,to);
                    GymItem gymItem = (GymItem) gymList.get(position);
                    intent.putExtra("gymId",gymItem.getImageId());//传入场馆id
                    context.startActivity(intent);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GymItem gymItem = gymList.get(position);
        //使用Glide加载图片，当前使用本地资源代替
        Glide.with(context).load(gymItem.getImageId())
                .placeholder(R.drawable.rectangle_placeholder).into(holder.gymItemImage);
        holder.gymItemName.setText(gymItem.getName());
        holder.gymItemDescription.setText(gymItem.getDescription());
    }


    @Override
    public int getItemCount() {

        return gymList.size();
    }
}
