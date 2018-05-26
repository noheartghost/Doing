package app.doing.com.doing.handpick.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.CourseItem;

/**
 * Created by cherry on 18-5-25.
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder>  {
    private List<CourseItem> courseItemList;

    public CourseItemAdapter(List<CourseItem> courseItemList) {
        this.courseItemList = courseItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CourseItem courseItem = courseItemList.get(position);
        holder.imageView.setImageResource(courseItem.getImageId());
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
