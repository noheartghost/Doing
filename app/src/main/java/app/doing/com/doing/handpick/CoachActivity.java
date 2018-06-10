package app.doing.com.doing.handpick;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.adapter.CoachListItemAdapter;
import app.doing.com.doing.handpick.adapter.GymListItemAdapter;
import app.doing.com.doing.handpick.item.CoachListItem;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.utils.SliderBanner.CardBanner;

public class CoachActivity extends AppCompatActivity {
    private List<CoachListItem> coachListItemList = new ArrayList<>();
    private List<Integer> courseBannerList = new ArrayList<>();
    private ViewPager courseViewPager;
    private CardBanner cardBanner;
    private RecyclerView recyclerView;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);

        initBanner();
        getCoachItemList();
    }

    private void initBanner(){
        courseViewPager = findViewById(R.id.coach_viewpager);

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

    private void getCoachItemList(){
        for(int i=0;i<5;i++){
            CoachListItem coachItem1 = new CoachListItem(R.drawable.gtm_item_pic,"教练姓名","田径冠军","特点标签","简单介绍",(float) 4.5);
            coachListItemList.add(coachItem1);
            CoachListItem coachItem2 = new CoachListItem(R.drawable.gtm_item_pic,"教练姓名","田径冠军","特点标签","简单介绍",(float) 4.5);
            coachListItemList.add(coachItem2);
            CoachListItem coachItem3 = new CoachListItem(R.drawable.gtm_item_pic,"教练姓名","田径冠军","特点标签","简单介绍",(float) 4.5);
            coachListItemList.add(coachItem3);
        }

        recyclerView = findViewById(R.id.recycler_coach);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        CoachListItemAdapter coachListItemAdapter = new CoachListItemAdapter(coachListItemList);
        recyclerView.setAdapter(coachListItemAdapter);

    }

    @Override
    protected void onStop() {
        cardBanner.setIsAlive(false);
        super.onStop();
    }
}
