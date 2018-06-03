package app.doing.com.doing.handpick.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.CoachItem;

/**
 * Created by cherry on 18-5-25.
 */

public class CoachItemAdapter extends RecyclerView.Adapter<CoachItemAdapter.ViewHolder>{
    private List<CoachItem> coachList;
    private Context context;

    public CoachItemAdapter(List<CoachItem> coachList){
        this.coachList = coachList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.coach_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CoachItem coachItem = coachList.get(position);
        //使用Glide加载图片，当前使用本地资源代替
        Glide.with(context).load(coachItem.getImageId()).into(holder.coachImage);
    }

    @Override
    public int getItemCount() {
        return coachList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView coachImage;
        public ViewHolder(View itemView) {
            super(itemView);
            coachImage = itemView.findViewById(R.id.coach_item_image);
        }
    }
}
