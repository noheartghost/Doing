package app.doing.com.doing.handpick;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.adapter.GymItemAdapter;
import app.doing.com.doing.handpick.item.GymItem;
import app.doing.com.doing.utils.SliderBanner.CardBanner;


public class GymActivity extends AppCompatActivity {

    private List<GymItem> gymItemList = new ArrayList<>();
    private List<Integer> gymBannerList = new ArrayList<>();
    private ViewPager gymViewPager;
    private CardBanner cardBanner;
    private RecyclerView recyclerView;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

        gymViewPager = findViewById(R.id.gym_viewpager);
        initGymBanner();

        getGymItemList();
        recyclerView = findViewById(R.id.recycler_gym);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getApplication(),2);
        recyclerView.setLayoutManager(layoutManager);

        GymItemAdapter gymItemAdapter = new GymItemAdapter(gymItemList);
        recyclerView.setAdapter(gymItemAdapter);
    }



    private void initGymBanner(){
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
            GymItem gymItem1 = new GymItem(R.drawable.gtm_item_pic,"宝力豪健身","特别好");
            gymItemList.add(gymItem1);
            GymItem gymItem2 = new GymItem(R.drawable.gtm_item_pic,"宝力豪健身","特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好");
            gymItemList.add(gymItem2);
            GymItem gymItem3 = new GymItem(R.drawable.gtm_item_pic,"宝力豪健身","特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好");
            gymItemList.add(gymItem3);
        }

    }

    @Override
    protected void onStop() {
        cardBanner.setIsAlive(false);
        super.onStop();
    }
}
