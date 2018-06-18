package app.doing.com.doing.handpick;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

import org.json.JSONException;
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
import app.doing.com.doing.handpick.item.ListItem;
import app.doing.com.doing.utils.BaiduGap.BaiduLocation;
import app.doing.com.doing.utils.Decoration.SpacesItemDecoration;
import app.doing.com.doing.utils.ParseJSONWithJSONObject.ParseJSONWithJSONObject;
import app.doing.com.doing.utils.SliderBanner.DoingBanner;
import app.doing.com.doing.utils.SliderBanner.DoingPagerAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static app.doing.com.doing.utils.GlobalVariable.GlobalVariable.BASE_URL;
import static app.doing.com.doing.utils.GlobalVariable.GlobalVariable.GYM_URL;
import static app.doing.com.doing.utils.GlobalVariable.GlobalVariable.SELECTED_GYM_URL;

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
    private Handler bannerHandler;//负责更新banner
    private Handler listHandler = new Handler();//负责更新list
    private RecyclerView.Adapter mAdapter;
    private PopupWindow popupWindow;
    private View popupView;

    //默认 url
    private static String contentUrl = "GetGYMRecommendServlet";
    //指示当前属于哪个页面：0-5
    private int indicator;
    private BaiduLocation baiduLocation;

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

        bannerHandler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                viewPager.setCurrentItem(msg.what);
                super.handleMessage(msg);
            }
        };

        doingBanner = new DoingBanner(listBannerList,this,getSupportFragmentManager(),viewPager,bannerHandler);
        new Thread(doingBanner).start();

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
                return new SelectedGymListItemAdapter(this,listItemList);
            case 5:
            default:
                return new SelectedCourseListItemAdapter(listItemList);
        }
    }

    private void initList(){
        //初始化Adapter的数据源
        listItemList = new ArrayList<>();

        //获取recyclerView资源
        recyclerView = findViewById(R.id.recycler_list);
        //禁止recyclerView自动获取焦点
        recyclerView.setFocusable(false);
        //获取到数据后填入recyclerView
        mAdapter = newAdapter(listItemList);
        //设置适配器
        recyclerView.setAdapter(mAdapter);

        //初始化recyclerView格式
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

        //向网络获取数据
        switch (indicator){
            case 0:
                break;
            case 1:
                contentUrl = GYM_URL;
                doGet("type=1");
                break;
            case 2:
                break;
            case 3:

                break;
            case 4:
                contentUrl = SELECTED_GYM_URL;
                baiduLocation = new BaiduLocation(ListActivity.this,getApplicationContext(),new SelectedGymLocationListener());
                break;
            case 5:
                break;
                default:
        }
    }

    private boolean doGet(final String arguments){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    sendRequestWithOkHttp(arguments);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.list_tab_select:
                showPopupWindow();
                break;

        }

    }

    private void showPopupWindow(){
        if(null == popupView)popupView = LayoutInflater.from(this).inflate(R.layout.select_popup_window,null);
        if(null == popupWindow){
            popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT,true);
            popupWindow.setContentView(popupView);
            popupWindow.setTouchable(true);
            //popupWindow.setOutsideTouchable(false);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
        }

        popupWindow.showAsDropDown(listTabSelect);
        backgroundAlpha(0.4f);
    }

    //遮罩层，设置屏幕透明度
    private void backgroundAlpha(float f){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = f;
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onStop() {
        doingBanner.setIsAlive(false);
        super.onStop();
    }

    private void sendRequestWithOkHttp(final String arguments){
        //1.获取OkHttpClient
        OkHttpClient client = new OkHttpClient();
        //2.构造Request
        Request request = new Request.Builder()
                .url(BASE_URL+contentUrl+"?"+arguments)//type=1，按降序排序
                .build();
        //3.将Request封装成call
        Call call = client.newCall(request);
        //4.执行call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("TAG","获取数据失败");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject responseTex = new JSONObject(response.body().string());
                    ParseJSONWithJSONObject.parseListItem(listItemList,responseTex.get("gymdetails")+"",indicator);

                    //使用handler方案在Ui线程中更新Adapter的list
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    };
                    listHandler.post(runnable);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    public class SelectedGymLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //获取经纬度后，向后台发起获取精选场馆请求
            double latitude = bdLocation.getLatitude();//获取维度
            double longitude = bdLocation.getLongitude();//获取经度
            doGet("userx="+longitude+"&usery="+latitude);

        }
    }

    /*
        请求经纬度所需权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length > 0){
                    for(int  result: grantResults){
                        if(result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    baiduLocation.requestLocation();
                }else{
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:
        }
    }

}

//    private ListItem newItem(){
//        switch (indicator){
//            case 0:
//                return new CoachListItem(R.drawable.gtm_item_pic,"教练姓名","田径冠军","特点标签","简单介绍",(float) 4.5);
//            case 1:
//                return new GymListItem(R.drawable.gtm_item_pic,"宝力豪健身（大悦城南区）","特点标签","描述性文字",(float) 4.5);
//            case 2:
//                return new CourseListItem(R.drawable.gtm_item_pic,"课程姓名",66,"特点标签","简单介绍",(float) 4.5);
//            case 3:
//                return new CoachListItem(R.drawable.gtm_item_pic,"教练姓名","田径冠军","特点标签","简单介绍",(float) 4.5,60);
//            case 4:
//                return new GymListItem(R.drawable.gtm_item_pic,"宝力豪健身（大悦城南区）","天津市津南区海河教育园区同砚路",(float) 4.5,600,50);
//            case 5:
//            default:
//                return new CourseListItem(R.drawable.gtm_item_pic,"课程姓名",66,"特点标签","简单介绍",(float) 4.5,50);
//        }
//    }