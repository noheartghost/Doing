package app.doing.com.doing.handpick;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.utils.SliderBanner.CardBanner;

public class CoachActivity extends AppCompatActivity {
    private List<Integer> courseBannerList = new ArrayList<>();
    private ViewPager courseViewPager;
    private CardBanner cardBanner;
    private RecyclerView recyclerView;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);

        courseViewPager = findViewById(R.id.coach_viewpager);
        initGymBanner();

    }

    private void initGymBanner(){
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

    @Override
    protected void onStop() {
        cardBanner.setIsAlive(false);
        super.onStop();
    }
}
