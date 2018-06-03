package app.doing.com.doing.handpick.adapter;

import android.content.Context;
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

/**
 * Created by cherry on 18-5-24.
 */

public class GymItemAdapter extends RecyclerView.Adapter<GymItemAdapter.ViewHolder> {
    private List<GymItem> gymList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView gymItemImage;
        TextView gymItemName;
        TextView gymItemDescription;

        public ViewHolder(View view){
            super(view);
            gymItemImage = view.findViewById(R.id.gym_item_image);
            gymItemName = view.findViewById(R.id.gym_item_name);
            gymItemDescription = view.findViewById(R.id.gym_item_description);
        }
    }

    public GymItemAdapter(List<GymItem> gymList) {
        this.gymList = gymList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.gym_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GymItem gymItem = gymList.get(position);
        //使用Glide加载图片，当前使用本地资源代替
        Glide.with(context).load(gymItem.getImageId()).into(holder.gymItemImage);
        holder.gymItemName.setText(gymItem.getName());
        holder.gymItemDescription.setText(gymItem.getDescription());
    }


    @Override
    public int getItemCount() {

        return gymList.size();
    }
}
