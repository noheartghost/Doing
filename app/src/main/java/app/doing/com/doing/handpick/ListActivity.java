package app.doing.com.doing.handpick;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.customView.SelectTabCustom;
import app.doing.com.doing.handpick.adapter.CoachListItemAdapter;
import app.doing.com.doing.handpick.adapter.CourseListItemAdapter;
import app.doing.com.doing.handpick.adapter.GymListItemAdapter;
import app.doing.com.doing.handpick.item.CoachListItem;
import app.doing.com.doing.handpick.item.CourseListItem;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.handpick.item.ListItem;
import app.doing.com.doing.utils.SliderBanner.CardBanner;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{
    private SelectTabCustom listTabNear;
    private SelectTabCustom listTabType;
    private SelectTabCustom listTabScore;
    private SelectTabCustom listTabSelect;

    private List<ListItem> listItemList = new ArrayList<>();
    private List<Integer> listBannerList = new ArrayList<>();
    private ViewPager listViewPager;
    private CardBanner cardBanner;
    private RecyclerView recyclerView;
    private Handler handler;

    //指示当前属于哪个页面：0-5
    private int indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        indicator = intent.getIntExtra("indicator",0);
        initTab();
        initBanner();
        initList();
    }

    private void initTab(){
        listTabNear = findViewById(R.id.list_tab_near);
        listTabType = findViewById(R.id.list_tab_type);
        listTabScore = findViewById(R.id.list_tab_score);
        listTabSelect = findViewById(R.id.list_tab_select);

        listTabNear.setOnClickListener(this);
        listTabType.setOnClickListener(this);
        listTabScore.setOnClickListener(this);
        listTabSelect.setOnClickListener(this);

        tabReset();
    }

    private void tabReset(){
        listTabNear.setSelected(false);
        listTabType.setSelected(false);
        listTabScore.setSelected(false);
        listTabSelect.setSelected(false);
    }

    private void initBanner(){
        listViewPager = findViewById(R.id.list_viewpager);

        listBannerList.add(R.drawable.gtm_item_pic);
        listBannerList.add(R.drawable.pexels_photo);
        listBannerList.add(R.drawable.gtm_item_pic);
        listBannerList.add(R.drawable.gtm_item_pic);

        handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                listViewPager.setCurrentItem(msg.what);
                super.handleMessage(msg);
            }
        };

        cardBanner = new CardBanner(listBannerList,this,getSupportFragmentManager(),listViewPager,handler);
        cardBanner.setChangeTime(5000);
        new Thread(cardBanner).start();
    }

    private ListItem newItem(){
        switch (indicator){
            case 0:
                return new CoachListItem(R.drawable.gtm_item_pic,"教练姓名","田径冠军","特点标签","简单介绍",(float) 4.5);
            case 1:
                return new GymListItem(R.drawable.gtm_item_pic,"宝力豪健身","大悦城南区","天津市津南区海河教育园区同砚路38号",(float) 4.5,6,500);
            case 2:
                return new CourseListItem(R.drawable.gtm_item_pic,"课程姓名",66,"特点标签","简单介绍",(float) 4.5);
            case 3:

            case 4:

            case 5:
                default:


        }
        return null;
    }

    private RecyclerView.Adapter newAdapter(List<ListItem> listItemList){
        switch (indicator){
            case 0:
                return new CoachListItemAdapter(listItemList);
            case 1:
                return new GymListItemAdapter(listItemList);
            case 2:
                return new CourseListItemAdapter(listItemList);
            case 3:

            case 4:

            case 5:
            default:


        }
        return null;
    }

    private RecyclerView.LayoutManager createLayoutManager(){
        switch (indicator){
            case 0:
            case 2:
                return new GridLayoutManager(this,2);
            case 1:
                return new LinearLayoutManager(this);
        }
        return null;
    }

    private void initList(){
        for(int i=0;i<5;i++){
            listItemList.add(newItem());
        }

        recyclerView = findViewById(R.id.recycler_list);
        RecyclerView.LayoutManager layoutManager = createLayoutManager();
        if(null!=layoutManager)recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);

        recyclerView.setAdapter(newAdapter(listItemList));

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onStop() {
        cardBanner.setIsAlive(false);
        super.onStop();
    }

}
