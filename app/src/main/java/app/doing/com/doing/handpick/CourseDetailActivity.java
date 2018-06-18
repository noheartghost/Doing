package app.doing.com.doing.handpick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.adapter.UserCommentAdapter;
import app.doing.com.doing.handpick.item.UserCommentItem;

/*
    课程详情页
 */
public class CourseDetailActivity extends AppCompatActivity {
    /*
    ui控件
     */
    private ImageView image;
    private TextView name;
    private RatingBar ratingBar;
    //课程简介
    private TextView introduction;
    //课程介绍
    private TextView details;
    //用户评论
    private RecyclerView recyclerView;

    /*
    数据
     */
    private String imageId;
    private String nameStr;
    private String introductionStr;
    private String detailsStr;
    private double ratings;
    private List<UserCommentItem> commentList = new ArrayList<>();
    private UserCommentAdapter madapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        initView();
        getData();
        showData();
    }

    private void initView(){
        image = findViewById(R.id.course_detail_image);
        name = findViewById(R.id.course_detail_name);
        ratingBar = findViewById(R.id.course_detail_ratingbar);
        introduction = findViewById(R.id.course_detail_introduction);
        details = findViewById(R.id.course_detail_details);
        recyclerView = findViewById(R.id.course_user_comment);
        recyclerView.setFocusable(false);
    }

    private void getData(){
        imageId = R.drawable.gtm_item_pic+"";
        nameStr = "宝力豪单次体验课程/健身次卡";
        introductionStr = "balalalallalalalalalallalalla";
        detailsStr = "lalalallllllllllllllllllllllllllllllllllllllllbababbabababbab";
        ratings = 3.4;
        commentList.add(new UserCommentItem("南开陈冠希","超nice的健身房。。。。。",R.drawable.gtm_item_pic,"05-04","04:30",29));
        commentList.add(new UserCommentItem("南开陈冠希","超nice的健身房。。。。。",R.drawable.gtm_item_pic,"05-04","04:30",29));
        commentList.add(new UserCommentItem("南开陈冠希","超nice的健身房。。。。。",R.drawable.gtm_item_pic,"05-04","04:30",29));

    }

    private void showData(){
        //获取后台数据

        //展示
        Glide.with(this).load(R.drawable.gtm_item_pic).into(image);
        name.setText(nameStr);
        ratingBar.setRating((float) ratings);
        introduction.setText(introductionStr);
        details.setText(detailsStr);

        madapter = new UserCommentAdapter(commentList);
        recyclerView.setAdapter(madapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
    }
}
