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
import app.doing.com.doing.customView.CircleImageView;
import app.doing.com.doing.handpick.item.UserCommentItem;

/**
 * Created by cherry on 18-6-18.
 * 对应课程详情页list
 */

public class UserCommentAdapter extends RecyclerView.Adapter<UserCommentAdapter.ViewHolder>{
    private List<UserCommentItem> list;
    private Context context;

    public UserCommentAdapter(List<UserCommentItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.course_comment_item,parent,false);
        UserCommentAdapter.ViewHolder holder = new UserCommentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserCommentItem item = list.get(position);
        if(item instanceof  UserCommentItem){
            Glide.with(context).load(item.getImageId()).dontAnimate().into(holder.imageView);
            holder.name.setText(item.getName());
            holder.content.setText(item.getContent());
            holder.date.setText(item.getDate());
            holder.time.setText(item.getTime());
            holder.thumbsNum.setText(item.getThumbsNum()+"");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imageView;
        TextView name;
        TextView content;
        TextView date;
        TextView time;
        TextView thumbsNum;

        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.course_comment_image);
            name = view.findViewById(R.id.course_comment_name);
            content = view.findViewById(R.id.course_comment_content);
            date = view.findViewById(R.id.course_comment_date);
            time = view.findViewById(R.id.course_comment_time);
            thumbsNum = view.findViewById(R.id.course_comment_thumb_num);

        }
    }
}
