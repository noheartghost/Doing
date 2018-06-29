package app.doing.com.doing.handpick.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.CourseListItem;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.handpick.item.ListItem;

/**
 * Created by cherry on 18-6-12.
 * 最火团课页list
 */


public class SelectedCourseListItemAdapter extends RecyclerView.Adapter<SelectedCourseListItemAdapter.ViewHolder> {
    private List<ListItem> listItemList;
    private Context context;
    private Class<?> to;//设置跳转到的Activity

    static class ViewHolder extends RecyclerView.ViewHolder{
        View item;//保存子项最外层布局
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
            item = view;
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

    /*
    设置应跳转的activity
     */
    public void setJumpActivity(Class<?> to){
        this.to = to;
    }

    @Override
    public SelectedCourseListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.selected_course_list_item,parent,false);
        final SelectedCourseListItemAdapter.ViewHolder holder = new SelectedCourseListItemAdapter.ViewHolder(view);
        //设置点击item项跳转到相应activity界面
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != to){
                    int position = holder.getAdapterPosition();
                    Intent intent = new Intent(context,to);
                    CourseListItem courseListItem = (CourseListItem) listItemList.get(position);
                    intent.putExtra("classid",courseListItem.getImageId());//传入课程id
                    context.startActivity(intent);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(SelectedCourseListItemAdapter.ViewHolder holder, int position) {
        ListItem listItem = listItemList.get(position);
        if(listItem instanceof CourseListItem){
            CourseListItem courseListItem = (CourseListItem) listItem;
            //使用Glide加载图片，当前使用本地资源代替
            Glide.with(context).load(courseListItem.getImageId())
                    .placeholder(R.drawable.square_placeholder).into(holder.courseImage);
            holder.courseName.setText(courseListItem.getName());
            holder.coursePerson.setText(courseListItem.getPersons()+"人参加");
            holder.courseTag.setText(courseListItem.getTag());
            holder.courseDescription.setText(courseListItem.getDescription());
            holder.courseRatingBar.setRating(courseListItem.getRating());
            DecimalFormat decimalFormat=new DecimalFormat(".0");//格式化为1位小数
            holder.courseRating.setText(decimalFormat.format(courseListItem.getRating()));
            holder.courseEvaluatedNum.setText(courseListItem.getEvaluatedNum()+"条");
        }


    }

    @Override
    public int getItemCount() {
        return listItemList.size();
    }
}

