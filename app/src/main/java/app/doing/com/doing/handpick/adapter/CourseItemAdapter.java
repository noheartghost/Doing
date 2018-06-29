package app.doing.com.doing.handpick.adapter;

import android.content.Context;
import android.content.Intent;
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
import app.doing.com.doing.handpick.item.CourseListItem;

/**
 * Created by cherry on 18-5-25.
 * 精选页课程list
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder>  {
    private List<CourseItem> courseItemList;
    private Context context;
    private Class<?> to;//设置跳转到的Activity

    public CourseItemAdapter(List<CourseItem> courseItemList) {
        this.courseItemList = courseItemList;
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
                .inflate(R.layout.course_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != to){
                    int position = holder.getAdapterPosition();
                    Intent intent = new Intent(context,to);
                    CourseItem courseItem = (CourseItem) courseItemList.get(position);
                    intent.putExtra("classid",courseItem.getImageId());//传入课程id
                    context.startActivity(intent);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CourseItem courseItem = courseItemList.get(position);
        //使用Glide加载图片，当前使用本地资源代替
        Glide.with(context).load(courseItem.getImageId())
                .placeholder(R.drawable.square_placeholder).into(holder.imageView);
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
        View item;//保存子项最外层布局
        ImageView imageView;
        TextView textViewName;
        TextView textViewPerson;
        TextView textViewSchedule;
        TextView textViewDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView;
            imageView = itemView.findViewById(R.id.course_item_image);
            textViewName = itemView.findViewById(R.id.course_item_name);
            textViewPerson = itemView.findViewById(R.id.course_item_person);
            textViewSchedule = itemView.findViewById(R.id.course_item_schedule);
            textViewDescription = itemView.findViewById(R.id.course_item_description);
        }
    }
}
