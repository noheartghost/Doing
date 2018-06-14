package app.doing.com.doing.handpick;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.customView.NavigatorBarCustom;
import app.doing.com.doing.customView.SelectTabCustom;
import app.doing.com.doing.handpick.adapter.CoachListItemAdapter;
import app.doing.com.doing.handpick.adapter.CourseListItemAdapter;
import app.doing.com.doing.handpick.adapter.GymListItemAdapter;
import app.doing.com.doing.handpick.adapter.SelectedCoachListItemAdapter;
import app.doing.com.doing.handpick.adapter.SelectedCourseListItemAdapter;
import app.doing.com.doing.handpick.adapter.SelectedGymListItemAdapter;
import app.doing.com.doing.handpick.item.CoachListItem;
import app.doing.com.doing.handpick.item.CourseListItem;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.handpick.item.ListItem;
import app.doing.com.doing.utils.Decoration.SpacesItemDecoration;
import app.doing.com.doing.utils.SliderBanner.DoingBanner;
import app.doing.com.doing.utils.SliderBanner.DoingPagerAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{
    private SelectTabCustom listTabNear;
    private SelectTabCustom listTabType;
    private SelectTabCustom listTabScore;
    private SelectTabCustom listTabSelect;

    private List<ListItem> listItemList = new ArrayList<>();
    private List<Integer> listBannerList = new ArrayList<>();
    private ViewPager viewPager;
    private DoingBanner doingBanner;
    private RecyclerView recyclerView;
    private Handler handler;

    private static String baseUrl = "http://47.94.0.163:8080/fitness/";
    //默认 url
    private static String contentUrl = "GetGYMRecommendServlet";


    //指示当前属于哪个页面：0-5
    private int indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        indicator = intent.getIntExtra("indicator",0);
        initNavigator();
        initTab();
        initBanner();
        initList();
    }

    private void initNavigator(){
        NavigatorBarCustom navigatorBarCustom = findViewById(R.id.list_navigator_bar);
        switch (indicator){
            case 0:
                navigatorBarCustom.setText_title("健身教练");
                break;
            case 1:
                navigatorBarCustom.setText_title("健身场馆");
                break;
            case 2:
                navigatorBarCustom.setText_title("健身课程");
                break;
            case 3:
                navigatorBarCustom.setText_title("明星教练");
                break;
            case 4:
                navigatorBarCustom.setText_title("精选场馆");
                break;
            case 5:
                default:
                    navigatorBarCustom.setText_title("最火团课");
                break;
        }

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

        listBannerList.add(R.drawable.gtm_item_pic);
        listBannerList.add(R.drawable.pexels_photo);
        listBannerList.add(R.drawable.gtm_item_pic);
        listBannerList.add(R.drawable.gtm_item_pic);

        viewPager = findViewById(R.id.list_viewpager1);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(30);
        DoingPagerAdapter adapter = new DoingPagerAdapter(this,listBannerList);
        viewPager.setAdapter(adapter);

        handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                viewPager.setCurrentItem(msg.what);
                super.handleMessage(msg);
            }
        };

        doingBanner = new DoingBanner(listBannerList,this,getSupportFragmentManager(),viewPager,handler);
        new Thread(doingBanner).start();

    }

    private ListItem newItem(){
        switch (indicator){
            case 0:
                return new CoachListItem(R.drawable.gtm_item_pic,"教练姓名","田径冠军","特点标签","简单介绍",(float) 4.5);
            case 1:
                return new GymListItem(R.drawable.gtm_item_pic,"宝力豪健身（大悦城南区）","特点标签","描述性文字",(float) 4.5);
            case 2:
                return new CourseListItem(R.drawable.gtm_item_pic,"课程姓名",66,"特点标签","简单介绍",(float) 4.5);
            case 3:
                return new CoachListItem(R.drawable.gtm_item_pic,"教练姓名","田径冠军","特点标签","简单介绍",(float) 4.5,60);
            case 4:
                return new GymListItem(R.drawable.gtm_item_pic,"宝力豪健身（大悦城南区）","天津市津南区海河教育园区同砚路",(float) 4.5,600,50);
            case 5:
            default:
                return new CourseListItem(R.drawable.gtm_item_pic,"课程姓名",66,"特点标签","简单介绍",(float) 4.5,50);
        }
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
                return new SelectedCoachListItemAdapter(listItemList);
            case 4:
                return new SelectedGymListItemAdapter(listItemList);
            case 5:
            default:
                return new SelectedCourseListItemAdapter(listItemList);
        }
    }

    private void initList(){
        switch (indicator){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                contentUrl = "GetGYMRecommendServlet";
                break;
            case 4:
                break;
            case 5:
                break;
                default:
        }

        sendRequestWithOkHttp();
        for(int i=0;i<5;i++){
            listItemList.add(newItem());
        }

        recyclerView = findViewById(R.id.recycler_list);
        RecyclerView.LayoutManager layoutManager;
        int spacePixel = getResources().getDimensionPixelSize(R.dimen.recycler_spacing);
        switch (indicator){
            case 0:
            case 1:
            case 2:
                layoutManager = new GridLayoutManager(this,2);
                recyclerView.addItemDecoration(new SpacesItemDecoration((spacePixel),2));
                break;
            case 3:
            case 4:
            case 5:
            default:
                layoutManager = new LinearLayoutManager(this);
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        }
        if(null!=layoutManager)recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(newAdapter(listItemList));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onStop() {
        doingBanner.setIsAlive(false);
        super.onStop();
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(baseUrl+contentUrl)
                            .build();

                    Response response = client.newCall(request).execute();
                    JSONObject responseTex = new JSONObject(response.body().string());
                    if(responseTex.get("status").equals("200")){
                        Log.i("content",""+responseTex.get("gymdetails"));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
