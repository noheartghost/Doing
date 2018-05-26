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

import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.GymItem;

/**
 * Created by cherry on 18-5-24.
 */

public class GymItemAdapter extends RecyclerView.Adapter<GymItemAdapter.ViewHolder> {
    private List<GymItem> gymList;

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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gym_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GymItem gymItem = gymList.get(position);
        holder.gymItemImage.setImageResource(gymItem.getImageId());
        holder.gymItemName.setText(gymItem.getName());
        holder.gymItemDescription.setText(gymItem.getDescription());
    }


    @Override
    public int getItemCount() {

        return gymList.size();
    }
}
