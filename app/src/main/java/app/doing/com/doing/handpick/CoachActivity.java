package app.doing.com.doing.handpick;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.customView.SelectTabCustom;
import app.doing.com.doing.handpick.adapter.CoachListItemAdapter;
import app.doing.com.doing.handpick.adapter.GymListItemAdapter;
import app.doing.com.doing.handpick.item.CoachListItem;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.utils.SliderBanner.CardBanner;

public class CoachActivity extends AppCompatActivity implements View.OnClickListener{
    private SelectTabCustom coachTabNear;
    private SelectTabCustom coachTabType;
    private SelectTabCustom coachTabScore;
    private SelectTabCustom coachTabSelect;

    private List<CoachListItem> coachListItemList = new ArrayList<>();
    private List<Integer> coachBannerList = new ArrayList<>();
    private ViewPager coachViewPager;
    private CardBanner cardBanner;
    private RecyclerView recyclerView;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);

        initBanner();
        getCoachItemList();
        initView();
    }

    private void initView(){
        coachTabNear = findViewById(R.id.coach_tab_near);
        coachTabType = findViewById(R.id.coach_tab_type);
        coachTabScore = findViewById(R.id.coach_tab_score);
        coachTabSelect = findViewById(R.id.coach_tab_select);

        coachTabNear.setOnClickListener(this);
        coachTabType.setOnClickListener(this);
        coachTabScore.setOnClickListener(this);
        coachTabSelect.setOnClickListener(this);
    }

    private void initBanner(){
        coachViewPager = findViewById(R.id.coach_viewpager);

        coachBannerList.add(R.drawable.gtm_item_pic);
        coachBannerList.add(R.drawable.pexels_photo);
        coachBannerList.add(R.drawable.gtm_item_pic);
        coachBannerList.add(R.drawable.gtm_item_pic);

        handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                coachViewPager.setCurrentItem(msg.what);
                super.handleMessage(msg);
            }
        };

        cardBanner = new CardBanner(coachBannerList,this,getSupportFragmentManager(),coachViewPager,handler);
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

    private void tabReset(){
        coachTabNear.setSelected(false);
        coachTabType.setSelected(false);
        coachTabScore.setSelected(false);
        coachTabSelect.setSelected(false);
    }

    @Override
    public void onClick(View v) {
        tabReset();
        switch (v.getId()){
            case R.id.coach_tab_near:
                coachTabNear.setSelected(true);
                break;
            case R.id.coach_tab_type:
                coachTabType.setSelected(true);
                break;
            case R.id.coach_tab_score:
                coachTabScore.setSelected(true);
                break;
            case R.id.coach_tab_select:
                coachTabSelect.setSelected(true);
                break;
            default:
                break;
        }
    }
}
