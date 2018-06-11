package app.doing.com.doing.handpick;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.ViewPagerClass;
import app.doing.com.doing.customView.ImageButtonCustom;
import app.doing.com.doing.handpick.adapter.CoachItemAdapter;
import app.doing.com.doing.handpick.adapter.CourseItemAdapter;
import app.doing.com.doing.handpick.adapter.GymItemAdapter;
import app.doing.com.doing.handpick.item.CoachItem;
import app.doing.com.doing.handpick.item.CourseItem;
import app.doing.com.doing.handpick.item.GymItem;


/**
 * Created by cherry on 18-5-21.
 */

public class HandpickFragment extends Fragment implements View.OnClickListener{
    private ImageButtonCustom gym_ibc;
    private ImageButtonCustom course_ibc;
    private ImageButtonCustom coach_ibc;
    private ViewPagerClass viewPager;
    private List<View> views;

    //展示的4个精选场馆
    private List<GymItem> gymItemList = new ArrayList<>();
    //展示的4个精选教练
    private List<CoachItem> coachItemList = new ArrayList<>();
    //展示的4个精选课程
    private List<CourseItem> courseItemList = new ArrayList<>();


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View handpickFragment = layoutInflater.inflate(R.layout.handpick_fragment,container,false);
        initViews(handpickFragment);

        return handpickFragment;
    }

    private void initViews(View view){
        //初始化Banner
        viewPager = view.findViewById(R.id.viewPager);
        initBanner();
        viewPager.setViewPagerViews(views);

        gym_ibc = view.findViewById(R.id.gym_image);
        course_ibc = view.findViewById(R.id.course_image);
        coach_ibc = view.findViewById(R.id.coach_image);

        gym_ibc.setOnClickListener(this);
        course_ibc.setOnClickListener(this);
        coach_ibc.setOnClickListener(this);

        /*获取精选数据列表*/
        getGymItemList();
        getCoachItemList();
        getCourseItemList();

        /*设置RecyclerView布局*/

        //设置场馆gym
        RecyclerView recyclerView_gym = view.findViewById(R.id.handpick_gym_list);
        GridLayoutManager layoutManager_gym = new GridLayoutManager(getActivity(),2);
        recyclerView_gym.setLayoutManager(layoutManager_gym);

        //设置教练coach
        RecyclerView recyclerView_coach = view.findViewById(R.id.handpick_coach_list);
        LinearLayoutManager layoutManager_coach = new LinearLayoutManager(getActivity());
        layoutManager_coach.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_coach.setLayoutManager(layoutManager_coach);

        //设置课程
        RecyclerView recyclerView_course = view.findViewById(R.id.handpick_course_list);
        LinearLayoutManager layoutManager_course = new LinearLayoutManager(getActivity());
        layoutManager_course.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_course.setLayoutManager(layoutManager_course);

        /*设置适配器*/

        //设置场馆gym
        GymItemAdapter gymItemAdapter = new GymItemAdapter(gymItemList);
        recyclerView_gym.setAdapter(gymItemAdapter);

        //设置教练coach
        CoachItemAdapter coachItemAdapter = new CoachItemAdapter(coachItemList);
        recyclerView_coach.setAdapter(coachItemAdapter);

        //设置课程
        CourseItemAdapter courseItemAdapter = new CourseItemAdapter(courseItemList);
        recyclerView_course.setAdapter(courseItemAdapter);

    }

    //定义轮播图片
    private void initBanner(){
        views = new ArrayList<>();
        ImageView imageView1 = new ImageView(getActivity().getApplicationContext()) ;
        ImageView imageView2 = new ImageView(getActivity().getApplicationContext());
        ImageView imageView3 = new ImageView(getActivity().getApplicationContext());
        ImageView imageView4 = new ImageView(getActivity().getApplicationContext());
        imageView1.setBackgroundResource(R.drawable.gym1);
        views.add(imageView1);
        imageView2.setBackgroundResource(R.drawable.gym2);
        views.add(imageView2);
        imageView3.setBackgroundResource(R.drawable.gym3);
        views.add(imageView3);
        imageView4.setBackgroundResource(R.drawable.gym4);
        views.add(imageView4);
    }

    @Override
    public void onClick(View v) {
        //判断点击的按钮
        Intent intent = new Intent(getActivity(),ListActivity.class);
        switch (v.getId()){
            case R.id.coach_image:
                intent.putExtra("indicator",0);
                break;
            case R.id.gym_image:
                intent.putExtra("indicator",1);
                break;
            case R.id.course_image:
                intent.putExtra("indicator",2);
                break;
            default:
                break;
        }
        startActivity(intent);

    }

    private void getGymItemList(){
        GymItem gymItem1 = new GymItem(R.drawable.gtm_item_pic,"宝力豪健身","特别好");
        gymItemList.add(gymItem1);
        GymItem gymItem2 = new GymItem(R.drawable.gtm_item_pic,"宝力豪健身","特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好");
        gymItemList.add(gymItem2);
        GymItem gymItem3 = new GymItem(R.drawable.gtm_item_pic,"宝力豪健身","特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好");
        gymItemList.add(gymItem3);
        GymItem gymItem4 = new GymItem(R.drawable.gtm_item_pic,"宝力豪健身","特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好");
        gymItemList.add(gymItem4);
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
    public void onStop() {
        viewPager.stop();
        super.onStop();
    }
}