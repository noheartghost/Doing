package app.doing.com.doing.handpick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.customView.NavigatorBarCustom;
import app.doing.com.doing.handpick.adapter.UserCommentAdapter;
import app.doing.com.doing.handpick.item.UserCommentItem;

/*
    课程详情页
 */
public class CourseDetailActivity extends AppCompatActivity implements View.OnClickListener{
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
    private TextView purchase;
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
    //存放用户评论
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
        //设置导航栏左右键的响应事件
        NavigatorBarCustom navigatorBarCustom = findViewById(R.id.course_detail_navigator);
        navigatorBarCustom.setLeftImageButtonListener(this);
        navigatorBarCustom.setRightImageButtonListener(this);

        image = findViewById(R.id.course_detail_image);
        name = findViewById(R.id.course_detail_name);
        ratingBar = findViewById(R.id.course_detail_ratingbar);
        introduction = findViewById(R.id.course_detail_introduction);
        details = findViewById(R.id.course_detail_details);
        recyclerView = findViewById(R.id.course_user_comment);
        recyclerView.setFocusable(false);
        purchase = findViewById(R.id.course_purchase);

        //设置点击购买按钮后跳转到订单详情页
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailActivity.this,PayActivity.class);
                intent.putExtra("courseid","课程id");
                startActivity(intent);
            }
        });
    }

    /*
    获取课程数据
     */
    private void getData(){
        imageId = R.drawable.gtm_item_pic+"";
        nameStr = "宝力豪单次体验课程/健身次卡";
        introductionStr = "瑜珈高级课程 Adv. Yoga 高级水平的瑜珈，对柔韧要求更高，并有一定的瑜珈初级水平。 \n" +
                "力量伸展 Power Stretch 此运动来于瑜珈伸展，结合力量和平衡。运动能健美形体，减轻压力";
        detailsStr = "瑜珈高级课程 Adv. Yoga 高级水平的瑜珈，对柔韧要求更高，并有一定的瑜珈初级水平。 \n" +
                "力量伸展 Power Stretch 此运动来于瑜珈伸展，结合力量和平衡。运动能健美形体，减轻压力";
        ratings = 4;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nav_image_left:
                this.finish();
                break;
        }
    }
}
