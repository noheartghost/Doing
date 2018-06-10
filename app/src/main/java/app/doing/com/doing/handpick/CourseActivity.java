package app.doing.com.doing.handpick;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.adapter.CoachListItemAdapter;
import app.doing.com.doing.handpick.adapter.CourseListItemAdapter;
import app.doing.com.doing.handpick.item.CoachListItem;
import app.doing.com.doing.handpick.item.CourseListItem;
import app.doing.com.doing.utils.SliderBanner.CardBanner;

public class CourseActivity extends AppCompatActivity {
    private List<CourseListItem> courseListItemList = new ArrayList<>();
    private List<Integer> courseBannerList = new ArrayList<>();
    private ViewPager courseViewPager;
    private CardBanner cardBanner;
    private RecyclerView recyclerView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        initBanner();
        getCourseItemList();

    }

    private void initBanner(){
        courseViewPager = findViewById(R.id.course_viewpager);
        courseBannerList.add(R.drawable.gtm_item_pic);
        courseBannerList.add(R.drawable.pexels_photo);
        courseBannerList.add(R.drawable.gtm_item_pic);
        courseBannerList.add(R.drawable.gtm_item_pic);

        handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                courseViewPager.setCurrentItem(msg.what);
                super.handleMessage(msg);
            }
        };

        cardBanner = new CardBanner(courseBannerList,this,getSupportFragmentManager(),courseViewPager,handler);
        cardBanner.setChangeTime(5000);
        new Thread(cardBanner).start();
    }

    private void getCourseItemList(){
        for(int i=0;i<5;i++){
            CourseListItem courseItem1 = new CourseListItem(R.drawable.gtm_item_pic,"课程姓名",66,"特点标签","简单介绍",(float) 4.5);
            courseListItemList.add(courseItem1);
            CourseListItem courseItem2 = new CourseListItem(R.drawable.gtm_item_pic,"课程姓名",66,"特点标签","简单介绍",(float) 4.5);
            courseListItemList.add(courseItem2);
            CourseListItem courseItem3 = new CourseListItem(R.drawable.gtm_item_pic,"课程姓名",66,"特点标签","简单介绍",(float) 4.5);
            courseListItemList.add(courseItem3);
        }

        recyclerView = findViewById(R.id.recycler_course);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        CourseListItemAdapter courseListItemAdapter = new CourseListItemAdapter(courseListItemList);
        recyclerView.setAdapter(courseListItemAdapter);

    }

    @Override
    protected void onStop() {
        cardBanner.setIsAlive(false);
        super.onStop();
    }
}
