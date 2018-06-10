package app.doing.com.doing.handpick;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.customView.SelectTabCustom;
import app.doing.com.doing.handpick.adapter.GymListItemAdapter;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.utils.SliderBanner.CardBanner;


public class GymActivity extends AppCompatActivity implements View.OnClickListener{
    private SelectTabCustom  gymTabNear;
    private SelectTabCustom gymTabType;
    private SelectTabCustom gymTabScore;
    private SelectTabCustom gymTabSelect;

    private List<GymListItem> gymListItemList = new ArrayList<>();
    private List<Integer> gymBannerList = new ArrayList<>();
    private ViewPager gymViewPager;
    private CardBanner cardBanner;
    private RecyclerView recyclerView;
    private Handler                                                                                   handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gym);

        initBanner();
        getGymItemList();
        initView();
    }

    private void initView(){
        gymTabNear = findViewById(R.id.gym_tab_near);
        gymTabType = findViewById(R.id.gym_tab_type);
        gymTabScore = findViewById(R.id.gym_tab_score);
        gymTabSelect = findViewById(R.id.gym_tab_select);

        gymTabNear.setOnClickListener(this);
        gymTabType.setOnClickListener(this);
        gymTabScore.setOnClickListener(this);
        gymTabSelect.setOnClickListener(this);
    }

    private void tabReset(){
        gymTabNear.setSelected(false);
        gymTabType.setSelected(false);
        gymTabScore.setSelected(false);
        gymTabSelect.setSelected(false);
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

    @Override
    public void onClick(View v) {
        tabReset();
        switch (v.getId()){
            case R.id.gym_tab_near:
                gymTabNear.setSelected(true);
                break;
            case R.id.gym_tab_type:
                gymTabType.setSelected(true);
                break;
            case R.id.gym_tab_score:
                gymTabScore.setSelected(true);
                break;
            case R.id.gym_tab_select:
                gymTabSelect.setSelected(true);
                break;
                default:
                    break;
        }
    }
}
