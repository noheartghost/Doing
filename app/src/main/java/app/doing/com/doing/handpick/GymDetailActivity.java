package app.doing.com.doing.handpick;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.customView.NavigatorBarCustom;
import app.doing.com.doing.customView.SubtitleCustom;
import app.doing.com.doing.handpick.adapter.CoachItemAdapter;
import app.doing.com.doing.handpick.adapter.CourseItemAdapter;
import app.doing.com.doing.handpick.item.CoachItem;
import app.doing.com.doing.handpick.item.CourseItem;
import app.doing.com.doing.utils.SliderBanner.DoingBanner;
import app.doing.com.doing.utils.SliderBanner.DoingPagerAdapter;

/*
* 场馆详情页
* */
public class GymDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager viewPager;
    private Handler handler;
    private DoingBanner banner;
    private SubtitleCustom subtitleCustomCoach;
    private SubtitleCustom subtitleCustomCourse;
    private RecyclerView recyclerViewCoach;
    private RecyclerView recyclerViewCourse;
    //展示的4个精选教练
    private List<CoachItem> coachItemList = new ArrayList<>();
    //展示的4个精选课程
    private List<CourseItem> courseItemList = new ArrayList<>();
    //场馆信息
    private TextView gymName;
    private RatingBar gymRating;
    private TextView gymAddress;
    private TextView gymTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_detail);
        initView();

    }

    private void initView(){
        //设置导航栏左右键的响应事件
        NavigatorBarCustom navigatorBarCustom = (NavigatorBarCustom) findViewById(R.id.gym_detail_nav);
        navigatorBarCustom.setLeftImageButtonListener(this);
        navigatorBarCustom.setRightImageButtonListener(this);

        viewPager = (ViewPager) findViewById(R.id.gym_detail_banner);
        //模拟数据，后期向网络获取
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<3;i++){
            list.add(R.drawable.gtm_item_pic);
        }

        //初始化banner的适配器
        DoingPagerAdapter adapter = new DoingPagerAdapter(this,list);
        viewPager.setAdapter(adapter);

        //使用handler方案定时更新banner内容
        handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                viewPager.setCurrentItem(msg.what);
                super.handleMessage(msg);
            }
        };

        banner = new DoingBanner(list,this,getSupportFragmentManager(),viewPager,handler);
        new Thread(banner).start();

        //获得ui布局
        gymName = (TextView) findViewById(R.id.gym_banner_name);
        gymAddress = (TextView) findViewById(R.id.gym_banner_address);
        gymRating = (RatingBar) findViewById(R.id.gym_banner_ratingbar);
        gymTag = (TextView) findViewById(R.id.gym_banner_tag);

        //设置数据，后期从网络获取
        gymName.setText("宝力豪健身(大悦城南区)");
        gymRating.setRating(4);
        gymAddress.setText("天津市津南区海河教育园区同砚路38号");
        gymTag.setText("小标签   小标签   小标签");

        initRecyclerView();
    }

    /*
    初始化RecyclerView,后期参照ListActivity中健身场馆获取网络数据
     */
    private void initRecyclerView(){
        getCoachItemList();
        getCourseItemList();

        subtitleCustomCoach = (SubtitleCustom) findViewById(R.id.gym_detail_coach_title);
        subtitleCustomCourse = (SubtitleCustom) findViewById(R.id.gym_detail_course_title);
        recyclerViewCoach = (RecyclerView) findViewById(R.id.gym_detail_course);

        CoachItemAdapter coachItemAdapter = new CoachItemAdapter(coachItemList);
        recyclerViewCoach.setAdapter(coachItemAdapter);

        CourseItemAdapter courseItemAdapter = new CourseItemAdapter(courseItemList);
        recyclerViewCourse.setAdapter(courseItemAdapter);

        LinearLayoutManager layoutManager_coach = new LinearLayoutManager(this);
        layoutManager_coach.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewCoach.setLayoutManager(layoutManager_coach);

        LinearLayoutManager layoutManager_course = new LinearLayoutManager(this);
        layoutManager_course.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCourse.setLayoutManager(layoutManager_course);


    }
    @Override
    protected void onStop() {
        //在离开此页前关闭banner更新线程
        banner.setIsAlive(false);
        super.onStop();
    }

    private void getCoachItemList(){
        CoachItem coachItem1 = new CoachItem(R.drawable.gtm_item_pic);
        coachItemList.add(coachItem1);
        CoachItem coachItem2 = new CoachItem(R.drawable.gtm_item_pic);
        coachItemList.add(coachItem2);
        CoachItem coachItem3 = new CoachItem(R.drawable.gtm_item_pic);
        coachItemList.add(coachItem3);
        CoachItem coachItem4 = new CoachItem(R.drawable.gtm_item_pic);
        coachItemList.add(coachItem4);
    }

    private void getCourseItemList(){
        CourseItem courseItem1 = new CourseItem(R.drawable.gtm_item_pic,"基础拳击课",666,"每周三、周五","2个月","基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课");
        courseItemList.add(courseItem1);
        CourseItem courseItem2 = new CourseItem(R.drawable.gtm_item_pic,"基础拳击课",666,"每周三、周五","2个月","基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课");
        courseItemList.add(courseItem2);
        CourseItem courseItem3 = new CourseItem(R.drawable.gtm_item_pic,"基础拳击课",666,"每周三、周五","2个月","基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课");
        courseItemList.add(courseItem3);
        CourseItem courseItem4 = new CourseItem(R.drawable.gtm_item_pic,"基础拳击课",666,"每周三、周五","2个月","基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课基础拳击课");
        courseItemList.add(courseItem4);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.nav_image_left:
                this.finish();
                break;
        }
    }
}
