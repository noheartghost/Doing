package app.doing.com.doing.handpick;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.customView.SubtitleCustom;
import app.doing.com.doing.handpick.adapter.CoachItemAdapter;
import app.doing.com.doing.handpick.adapter.CourseItemAdapter;
import app.doing.com.doing.handpick.item.CoachItem;
import app.doing.com.doing.handpick.item.CourseItem;
import app.doing.com.doing.handpick.item.GymItem;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.utils.SliderBanner.DoingCardBanner;
import app.doing.com.doing.utils.SliderBanner.DoingCardPagerAdapter;

/*
*
* */
public class GymDetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Handler handler;
    private DoingCardBanner banner;
    private SubtitleCustom subtitleCustomCoach;
    private SubtitleCustom subtitleCustomCourse;
    private RecyclerView recyclerViewCoach;
    private RecyclerView recyclerViewCourse;
    //展示的4个精选教练
    private List<CoachItem> coachItemList = new ArrayList<>();
    //展示的4个精选课程
    private List<CourseItem> courseItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_detail);
        initBanner();
        initRecyclerView();
    }

    private void initBanner(){
        viewPager = findViewById(R.id.gym_detail_banner);
        List<GymListItem> list = new ArrayList<>();
        for(int i = 0;i<3;i++){
            list.add(new GymListItem(R.drawable.gtm_item_pic,i,"宝力豪健身","天津市津南区南开大学津南校区","小标签"));
        }

        DoingCardPagerAdapter adapter = new DoingCardPagerAdapter(this,list);
        viewPager.setAdapter(adapter);

        handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                viewPager.setCurrentItem(msg.what);
                super.handleMessage(msg);
            }
        };

        banner = new DoingCardBanner(list,this,getSupportFragmentManager(),viewPager,handler);
        new Thread(banner).start();


    }

    private void initRecyclerView(){
        getCoachItemList();
        getCourseItemList();

        subtitleCustomCoach = findViewById(R.id.gym_detail_coach_title);
        subtitleCustomCourse = findViewById(R.id.gym_detail_course_title);
        recyclerViewCoach = findViewById(R.id.gym_detail_coach);
        recyclerViewCourse = findViewById(R.id.gym_detail_course);

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
}
