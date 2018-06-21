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
import app.doing.com.doing.handpick.item.ListItem;

/**
 * Created by cherry on 18-6-12.
 * 最火课程
 */


public class SelectedCourseListItemAdapter extends RecyclerView.Adapter<SelectedCourseListItemAdapter.ViewHolder> {
    private List<ListItem> listItemList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView courseImage;
        TextView courseName;
        TextView coursePerson;
        TextView courseTag;
        TextView courseDescription;
        RatingBar courseRatingBar;
        TextView courseRating;
        TextView courseEvaluatedNum;

        public ViewHolder(View view){
            super(view);
            courseImage = view.findViewById(R.id.selected_course_list_imageId);
            courseName = view.findViewById(R.id.selected_course_list_name);
            coursePerson = view.findViewById(R.id.selected_course_list_person);
            courseTag = view.findViewById(R.id.selected_course_list_tag);
            courseDescription = view.findViewById(R.id.selected_course_list_description);
            courseRatingBar = view.findViewById(R.id.selected_course_list_ratingbar);
            courseRating = view.findViewById(R.id.selected_course_list_ratings);
            courseEvaluatedNum = view.findViewById(R.id.selected_course_list_evaluatedNum);
        }
    }

    public SelectedCourseListItemAdapter(List<ListItem> listItemList) {
        this.listItemList = listItemList;
    }

    @Override
    public SelectedCourseListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.selected_course_list_item,parent,false);
        SelectedCourseListItemAdapter.ViewHolder holder = new SelectedCourseListItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SelectedCourseListItemAdapter.ViewHolder holder, int position) {
        ListItem listItem = listItemList.get(position);
        if(listItem instanceof CourseListItem){
            CourseListItem courseListItem = (CourseListItem) listItem;
            //使用Glide加载图片，当前使用本地资源代替
            Glide.with(context).load(courseListItem.getImageId()).into(holder.courseImage);
            holder.courseName.setText(courseListItem.getName());
            holder.coursePerson.setText(courseListItem.getPersons()+"人参加");
            holder.courseTag.setText(courseListItem.getTag());
            holder.courseDescription.setText(courseListItem.getDescription());
            holder.courseRatingBar.setRating(courseListItem.getRating());
            holder.courseRating.setText(""+courseListItem.getRating());
            holder.courseEvaluatedNum.setText(courseListItem.getEvaluatedNum()+"条");
        }


    }

    @Override
    public int getItemCount() {
        return listItemList.size();
    }
}

