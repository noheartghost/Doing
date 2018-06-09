package app.doing.com.doing.handpick.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.CourseListItem;

/**
 * Created by cherry on 18-6-9.
 */

public class CourseListItemAdapter extends RecyclerView.Adapter<CourseListItemAdapter.ViewHolder>{
    private List<CourseListItem> courseListItemList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView courseImage;
        TextView courseName;
        TextView coursePerson;
        TextView courseTag;
        TextView courseDescription;
        RatingBar courseRatingBar;
        TextView courseRating;

        public ViewHolder(View view){
            super(view);
            courseImage = view.findViewById(R.id.course_list_imageId);
            courseName = view.findViewById(R.id.course_list_name);
            coursePerson = view.findViewById(R.id.course_list_person);
            courseTag = view.findViewById(R.id.course_list_tag);
            courseDescription = view.findViewById(R.id.course_list_description);
            courseRatingBar = view.findViewById(R.id.course_list_ratingbar);
            courseRating = view.findViewById(R.id.course_list_ratings);
        }
    }

    public CourseListItemAdapter(List<CourseListItem> courseListItemList) {
        this.courseListItemList = courseListItemList;
    }

    @Override
    public CourseListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.course_list_item,parent,false);
        CourseListItemAdapter.ViewHolder holder = new CourseListItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CourseListItemAdapter.ViewHolder holder, int position) {
        CourseListItem courseListItem = courseListItemList.get(position);
        //使用Glide加载图片，当前使用本地资源代替
        Glide.with(context).load(courseListItem.getImageId()).into(holder.courseImage);
        holder.courseName.setText(courseListItem.getName());
        holder.coursePerson.setText(courseListItem.getPersons()+"人参加");
        holder.courseTag.setText(courseListItem.getTag());
        holder.courseDescription.setText(courseListItem.getDescription());
        holder.courseRatingBar.setRating(courseListItem.getRating());
        holder.courseRating.setText(""+courseListItem.getRating());

    }

    @Override
    public int getItemCount() {
        return courseListItemList.size();
    }
}

