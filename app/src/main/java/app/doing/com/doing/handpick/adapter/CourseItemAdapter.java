package app.doing.com.doing.handpick.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.CourseItem;

/**
 * Created by cherry on 18-5-25.
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder>  {
    private List<CourseItem> courseItemList;
    private Context context;

    public CourseItemAdapter(List<CourseItem> courseItemList) {
        this.courseItemList = courseItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.course_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CourseItem courseItem = courseItemList.get(position);
        //使用Glide加载图片，当前使用本地资源代替
        Glide.with(context).load(courseItem.getImageId()).into(holder.imageView);
        holder.textViewName.setText(courseItem.getName());
        holder.textViewPerson.setText("共"+courseItem.getPersonCount()+"人参加");
        holder.textViewSchedule.setText(courseItem.getSchedule()+"/"+courseItem.getDuration());
        holder.textViewDescription.setText(courseItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return courseItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewName;
        TextView textViewPerson;
        TextView textViewSchedule;
        TextView textViewDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.course_item_image);
            textViewName = itemView.findViewById(R.id.course_item_name);
            textViewPerson = itemView.findViewById(R.id.course_item_person);
            textViewSchedule = itemView.findViewById(R.id.course_item_schedule);
            textViewDescription = itemView.findViewById(R.id.course_item_description);
        }
    }
}
