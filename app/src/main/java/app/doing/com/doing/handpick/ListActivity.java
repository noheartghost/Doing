package app.doing.com.doing.handpick;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import app.doing.com.doing.handpick.item.CoachListItem;
import app.doing.com.doing.handpick.item.CourseListItem;
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

/*
列表页，包括健身教练、健身场馆、健身课程，精选场馆，明星教练和最火团课
 */

public class ListActivity extends AppCompatActivity implements View.OnClickListener{
    //列表页附近、类型、评分和筛选4个tab按钮
    private SelectTabCustom listTabNear;
    private SelectTabCustom listTabType;
    private SelectTabCustom listTabScore;
    private SelectTabCustom listTabSelect;

    //存放该列表页的列表内容
    private List<ListItem> listItemList = new ArrayList<>();
    //存放该列表页的banner内容，目前使用假数据，后期从网络端获取
    private List<Integer> listBannerList = new ArrayList<>();
    private ViewPager viewPager;
    //用于设置banner的基本信息，包括切换时间、内容个数等
    private DoingBanner doingBanner;
    private Handler bannerHandler;//负责更新banner
    private RecyclerView recyclerView;
    private Handler listHandler = new Handler();//负责更新list
    private RecyclerView.Adapter mAdapter;//RecyclerView的适配器，由indicator决定具体初始化哪种适配器
    //点击筛选按钮弹出的遮罩层
    private PopupWindow popupWindow;
    private View popupView;

    //默认 url
    private static String contentUrl = "GetGYMRecommendServlet";
    //指示当前属于哪个页面：0-5
    private int indicator;
    //百度定位接口
    private BaiduLocation baiduLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //从intent中获取indicator数据，指示当前属于哪一页面
        Intent intent = getIntent();
        indicator = intent.getIntExtra("indicator",0);
        initNavigator();
        initTab();
        initBanner();
        initList();
    }

    /*
    根据indicator指定Navigator显示内容
     */
    private void initNavigator(){
        NavigatorBarCustom navigatorBarCustom = (NavigatorBarCustom) findViewById(R.id.list_navigator_bar);
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
        //设置导航栏的左右按钮的监听器
        navigatorBarCustom.setLeftImageButtonListener(this);
        navigatorBarCustom.setRightImageButtonListener(this);
    }

    /*
    初始化4个tab按钮，并设置初始状态
     */
    private void initTab(){
        listTabNear = (SelectTabCustom) findViewById(R.id.list_tab_near);
        listTabType = (SelectTabCustom) findViewById(R.id.list_tab_type);
        listTabScore = (SelectTabCustom) findViewById(R.id.list_tab_score);
        listTabSelect = (SelectTabCustom) findViewById(R.id.list_tab_select);

        listTabNear.setOnClickListener(this);
        listTabType.setOnClickListener(this);
        listTabScore.setOnClickListener(this);
        listTabSelect.setOnClickListener(this);

        tabReset();
        listTabNear.setSelected(true);
    }

    /*
    设置4个tab按钮为未选中状态
     */
    private void tabReset(){
        listTabNear.setSelected(false);
        listTabType.setSelected(false);
        listTabScore.setSelected(false);
        listTabSelect.setSelected(false);
    }

    private void initBanner(){
        //设置banner内容，后期使用服务器数据替换
        listBannerList.add(R.drawable.gtm_item_pic);
        listBannerList.add(R.drawable.pexels_photo);
        listBannerList.add(R.drawable.gtm_item_pic);
        listBannerList.add(R.drawable.gtm_item_pic);

        viewPager = (ViewPager) findViewById(R.id.list_viewpager1);
        //设置viewPager能存放的页面最多为3个
        viewPager.setOffscreenPageLimit(3);
        //设置page间距，隔开banner
        viewPager.setPageMargin(30);
        //设置适配器
        DoingPagerAdapter adapter = new DoingPagerAdapter(this,listBannerList);
        viewPager.setAdapter(adapter);

        //设置定时更新viewpager内容
        bannerHandler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                viewPager.setCurrentItem(msg.what);
                super.handleMessage(msg);
            }
        };

        doingBanner = new DoingBanner(listBannerList,this,getSupportFragmentManager(),viewPager,bannerHandler);
        new Thread(doingBanner).start();

    }

    /*
    根据indicator选择初始化的适配器,并设置点击item后跳转到的页面
     */
    private void newAdapter(List<ListItem> listItemList){
        switch (indicator){
            case 0:
                mAdapter = new CoachListItemAdapter(listItemList);
                ((CoachListItemAdapter) mAdapter).setJumpActivity(CoachDetailActivity.class);
                break;
            case 1:
                mAdapter = new GymListItemAdapter(listItemList);
                ((GymListItemAdapter) mAdapter).setJumpActivity(GymDetailActivity.class);
                break;
            case 2:
                mAdapter = new CourseListItemAdapter(listItemList);
                ((CourseListItemAdapter) mAdapter).setJumpActivity(CourseDetailActivity.class);
                break;
            case 3:
                mAdapter = new SelectedCoachListItemAdapter(listItemList);
                ((SelectedCoachListItemAdapter) mAdapter).setJumpActivity(CoachDetailActivity.class);
                break;
            case 4:
                mAdapter = new SelectedGymListItemAdapter(this,listItemList);
                ((SelectedGymListItemAdapter) mAdapter).setJumpActivity(GymDetailActivity.class);
                break;
            case 5:
            default:
                mAdapter = new SelectedCourseListItemAdapter(listItemList);
                ((SelectedCourseListItemAdapter) mAdapter).setJumpActivity(CourseDetailActivity.class);
                break;
        }
    }

    private void initList(){
        //初始化Adapter的数据源
        listItemList = new ArrayList<>();

        //获取recyclerView资源
        recyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        //禁止recyclerView自动获取焦点
        recyclerView.setFocusable(false);
        //获取到数据后填入recyclerView
        newAdapter(listItemList);

        //设置适配器
        recyclerView.setAdapter(mAdapter);

        //初始化recyclerView格式
        RecyclerView.LayoutManager layoutManager;
        int spacePixel = getResources().getDimensionPixelSize(R.dimen.recycler_spacing);
        //健身相应列表使用网格布局，精选相应列表使用线性布局
        switch (indicator){
            case 0:
            case 1:
            case 2:
                layoutManager = new GridLayoutManager(this,2);
                //使用自定义的装饰器设置网格布局的间距
                recyclerView.addItemDecoration(new SpacesItemDecoration((spacePixel),2));
                recyclerView.setPadding(0,28,0,0);
                break;
            case 3:
            case 4:
            case 5:
            default:
                layoutManager = new LinearLayoutManager(this);
                //设置线性布局的间距
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        }
        if(null!=layoutManager)recyclerView.setLayoutManager(layoutManager);

        //向网络获取数据，根据indicator决定查询的参数
        switch (indicator){
            case 0:
                for(int i=0;i<5;i++)
                    listItemList.add(newItem());
                break;
            case 1:
                contentUrl = GYM_URL;
                doGet("type=1");
                break;
            case 2:
                for(int i=0;i<5;i++)
                    listItemList.add(newItem());
                break;
            case 3:
                for(int i=0;i<5;i++)
                    listItemList.add(newItem());
                break;
            case 4:
                contentUrl = SELECTED_GYM_URL;
                //获取推荐场馆前需要先通过百度定位获取用户的经纬度
                //在经纬度获取的回调中获取推荐场馆数据
                baiduLocation = new BaiduLocation(ListActivity.this,getApplicationContext(),new SelectedGymLocationListener());
                break;
            case 5:
            default:
                for(int i=0;i<5;i++)
                    listItemList.add(newItem());
                break;
        }
    }

    /*
    新建线程，向网络发起请求
     */
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
                //选中新tab前重置所有tab状态
                tabReset();
                listTabSelect.setSelected(true);
                //弹出遮罩层
                showPopupWindow();
                break;
            case R.id.list_tab_near:
                //选中新tab前重置所有tab状态
                tabReset();
                listTabNear.setSelected(true);
                //设置附近为排序条件，搜索list内容

                break;
            case R.id.list_tab_type:
                //选中新tab前重置所有tab状态
                tabReset();
                listTabType.setSelected(true);
                //设置类型为排序条件，搜索list内容

                break;
            case R.id.list_tab_score:
                //选中新tab前重置所有tab状态
                tabReset();
                listTabScore.setSelected(true);
                //设置评分为排序条件，搜索list内容

                break;
            case R.id.nav_image_left:
                //点击返回键
                this.finish();
                break;
            case R.id.nav_image_right:
                //点击搜索按钮的处理事件

                break;
        }

    }

    private void showPopupWindow(){
        if(null == popupView){
            popupView = LayoutInflater.from(this).inflate(R.layout.select_popup_window,null);
            //设置遮罩层上按钮的响应事件
            popupView.findViewById(R.id.pop_reset).setOnClickListener(this);//重置按钮
            popupView.findViewById(R.id.pop_submit).setOnClickListener(this);//提交按钮
        }
        if(null == popupWindow){
            popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT,true);
            popupWindow.setContentView(popupView);
            popupWindow.setTouchable(true);
            //使点击popuoWindow外围后，popupWindow关闭
            popupWindow.setBackgroundDrawable(new ColorDrawable());
            //设置popupWindow关闭后的响应事件，设置背景透明度
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1.0f);
                }
            });
        }

        //showAsDropDown表示在筛选按键下方出现弹窗
        popupWindow.showAsDropDown(listTabSelect);
        //设置背景透明度
        backgroundAlpha(0.4f);
    }

    /*
    显示遮罩层后，设置背景透明度
     */
    private void backgroundAlpha(float f){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = f;
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onStop() {
        //在离开此页前关闭banner更新线程
        doingBanner.setIsAlive(false);
        super.onStop();
    }

    /*
    使用OkHttp提交网络请求
     */
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
                    //将传入的string数组转化为GymListItem数组
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


    /*
    向Baidu地图获取位置后的监听函数
     */
    public class SelectedGymLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //获取经纬度后，向后台发起获取精选场馆请求
            double latitude = bdLocation.getLatitude();//获取维度
            double longitude = bdLocation.getLongitude();//获取经度
            doGet("userx="+longitude+"&usery="+latitude);//发起请求

        }
    }

    /*
    根据indicator创建list列表的数据
     */
    private ListItem newItem(){
        switch (indicator){
            case 0:
                return new CoachListItem(R.drawable.gtm_item_pic,"教练姓名","田径冠军","特点标签","简单介绍",(float) 4.5);
            case 1:
              //  return new GymListItem(R.drawable.gtm_item_pic,"宝力豪健身（大悦城南区）","特点标签","描述性文字",(float) 4.5);
            case 2:
                return new CourseListItem(R.drawable.gtm_item_pic,"课程姓名",66,"特点标签","简单介绍",(float) 4.5);
            case 3:
                return new CoachListItem(R.drawable.gtm_item_pic,"教练姓名","田径冠军","特点标签","简单介绍",(float) 4.5,60);
            case 4:
            //    return new GymListItem(R.drawable.gtm_item_pic,"宝力豪健身（大悦城南区）","天津市津南区海河教育园区同砚路",(float) 4.5,600,50);
            case 5:
            default:
                return new CourseListItem(R.drawable.gtm_item_pic,"课程姓名",66,"特点标签","简单介绍",(float) 4.5,50);
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

