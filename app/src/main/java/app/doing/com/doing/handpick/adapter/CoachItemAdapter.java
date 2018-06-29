package app.doing.com.doing.handpick.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.CoachItem;
import app.doing.com.doing.handpick.item.GymItem;

/**
 * Created by cherry on 18-5-25.
 * 精选页教练list
 */

public class CoachItemAdapter extends RecyclerView.Adapter<CoachItemAdapter.ViewHolder>{
    private List<CoachItem> coachList;
    private Context context;
    private Class<?> to;//设置跳转到的Activity

    public CoachItemAdapter(List<CoachItem> coachList){
        this.coachList = coachList;
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
        View view = LayoutInflater.from(context).inflate(R.layout.coach_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != to){
                    int position = viewHolder.getAdapterPosition();
                    Intent intent = new Intent(context,to);
                    CoachItem coachItem = (CoachItem) coachList.get(position);
                    intent.putExtra("coachId",coachItem.getImageId());//传入教练id
                    context.startActivity(intent);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CoachItem coachItem = coachList.get(position);
        //使用Glide加载图片，当前使用本地资源代替
        Glide.with(context).load(coachItem.getImageId())
                .placeholder(R.drawable.square_placeholder).into(holder.coachImage);
    }

    @Override
    public int getItemCount() {
        return coachList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View item;//保存子项最外层布局
        ImageView coachImage;

        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView;
            coachImage = itemView.findViewById(R.id.coach_item_image);
        }
    }
}
