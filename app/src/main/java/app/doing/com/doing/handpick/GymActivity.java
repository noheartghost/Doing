package app.doing.com.doing.handpick;

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
import app.doing.com.doing.handpick.adapter.GymItemAdapter;
import app.doing.com.doing.handpick.adapter.GymListItemAdapter;
import app.doing.com.doing.handpick.item.GymItem;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.utils.SliderBanner.CardBanner;


public class GymActivity extends AppCompatActivity {

    private List<GymListItem> gymListItemList = new ArrayList<>();
    private List<Integer> gymBannerList = new ArrayList<>();
    private ViewPager gymViewPager;
    private CardBanner cardBanner;
    private RecyclerView recyclerView;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

        initBanner();
        getGymItemList();

    }



    private void initBanner(){
        gymViewPager = findViewById(R.id.gym_viewpager);

        gymBannerList.add(R.drawable.gtm_item_pic);
        gymBannerList.add(R.drawable.pexels_photo);
        gymBannerList.add(R.drawable.gtm_item_pic);
        gymBannerList.add(R.drawable.gtm_item_pic);

        handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                gymViewPager.setCurrentItem(msg.what);
                super.handleMessage(msg);
            }
        };

        cardBanner = new CardBanner(gymBannerList,this,getSupportFragmentManager(),gymViewPager,handler);
        cardBanner.setChangeTime(5000);
        new Thread(cardBanner).start();
    }

    private void getGymItemList(){
        for(int i=0;i<5;i++){
            GymListItem gymItem1 = new GymListItem(R.drawable.gtm_item_pic,"宝力豪健身","大悦城南区","天津市津南区海河教育园区同砚路38号",(float) 4.5,6,500);
            gymListItemList.add(gymItem1);
            GymListItem gymItem2 = new GymListItem(R.drawable.gtm_item_pic,"宝力豪健身","大悦城南区","天津市津南区海河教育园区同砚路38号",(float) 4.5,6,500);
            gymListItemList.add(gymItem2);
            GymListItem gymItem3 = new GymListItem(R.drawable.gtm_item_pic,"宝力豪健身","大悦城南区","天津市津南区海河教育园区同砚路38号",(float) 4.5,6,500);
            gymListItemList.add(gymItem3);
        }

        recyclerView = findViewById(R.id.recycler_gym);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        GymListItemAdapter gymListItemAdapter = new GymListItemAdapter(gymListItemList);
        recyclerView.setAdapter(gymListItemAdapter);

    }

    @Override
    protected void onStop() {
        cardBanner.setIsAlive(false);
        super.onStop();
    }
}
