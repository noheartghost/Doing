package app.doing.com.doing;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.find.FindFragment;
import app.doing.com.doing.handpick.HandpickFragment;
import app.doing.com.doing.me.MeFragment;
import app.doing.com.doing.moment.MomentFragment;

public class MainActivity extends Activity implements View.OnClickListener,View.OnTouchListener{
    private HandpickFragment handpickFragment;
    private FindFragment findFragment;
    private MomentFragment momentFragment;
    private MeFragment meFragment;

    private View handpickText;
    private View findText;
    private View momentText;
    private View meText;

    /*对Fragment进行管理*/
    private FragmentManager fragmentManager;

    //手指按下时的屏幕y坐标
    private float yDown;
    //在被判定为滚动之前用户手指可以移动的最大值
    private int touchSlop;

    private ViewPagerClass viewPager;
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
      
        //初始化Banner
        viewPager = findViewById(R.id.viewPager);
        initBanner();
        viewPager.setViewPagerViews(views);
    }

    private void initViews(){
        FrameLayout frameLayout = findViewById(R.id.content);
        frameLayout.setOnTouchListener(this);
        touchSlop = ViewConfiguration.get(this.getApplicationContext()).getScaledTouchSlop();

        handpickText = findViewById(R.id.handpick_text);
        findText = findViewById(R.id.find_text);
        momentText = findViewById(R.id.moment_text);
        meText = findViewById(R.id.me_text);
        handpickText.setOnClickListener(this);
        findText.setOnClickListener(this);
        momentText.setOnClickListener(this);
        meText.setOnClickListener(this);
        
    }
    //定义轮播图片
    private void initBanner(){
        views = new ArrayList<>();
        ImageView imageView1 = new ImageView(getApplicationContext()) ;
        ImageView imageView2 = new ImageView(getApplicationContext());
        ImageView imageView3 = new ImageView(getApplicationContext());
        ImageView imageView4 = new ImageView(getApplicationContext());
        imageView1.setBackgroundResource(R.drawable.gym1);
        views.add(imageView1);
        imageView2.setBackgroundResource(R.drawable.gym2);
        views.add(imageView2);
        imageView3.setBackgroundResource(R.drawable.gym3);
        views.add(imageView3);
        imageView4.setBackgroundResource(R.drawable.gym4);
        views.add(imageView4);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.handpick_text:
                setTabSelection(0);
                break;
            case R.id.find_text:
                setTabSelection(1);
                break;
            case R.id.moment_text:
                setTabSelection(2);
                break;
            case R.id.me_text:
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    /*根据传入的index设置显示的tab*/
    private void setTabSelection(int index){
        //每次切换fragment时，开启一个新的fragment事物
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch(index){
            case 0:
                if(handpickFragment == null){
                    handpickFragment = new HandpickFragment();

                    transaction.add(R.id.content,handpickFragment);
                }else{
                    transaction.show(handpickFragment);
                }
                break;
            case 1:
                if(findFragment == null){
                    findFragment = new FindFragment();
                    transaction.add(R.id.content,findFragment);
                }else{
                    transaction.show(findFragment);
                }
                break;
            case 2:
                if(momentFragment == null){
                    momentFragment = new MomentFragment();
                    transaction.add(R.id.content,momentFragment);
                }else{
                    transaction.show(momentFragment);
                }
                break;
            case 3:
            default:
                if(meFragment == null){
                    meFragment = new MeFragment();
                    transaction.add(R.id.content,meFragment);
                }else{
                    transaction.show(meFragment);
                }
                break;
        }
        transaction.commit();
    }

    /*用于将原有fragment隐藏起来*/
    private void hideFragments(FragmentTransaction transaction){
        if(handpickFragment != null){
            transaction.hide(handpickFragment);
        }
        if(findFragment != null){
            transaction.hide(findFragment);
        }
        if(momentFragment != null){
            transaction.hide(momentFragment);
        }
        if(meFragment != null){
            transaction.hide(meFragment);
        }
    }

    /*隐藏底部菜单*/
    private void hideBottom(){
        handpickText.setVisibility(View.GONE);
        findText.setVisibility(View.GONE);
        momentText.setVisibility(View.GONE);
        meText.setVisibility(View.GONE);
    }

    /*显示底部菜单*/
    private void showBottom(){
        handpickText.setVisibility(View.VISIBLE);
        findText.setVisibility(View.VISIBLE);
        momentText.setVisibility(View.VISIBLE);
        meText.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                yDown = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float yRaw = event.getRawY();
                int distance = (int)(yRaw - yDown);
                //如果手指是上拉状态，显示底部
                if (distance <= -touchSlop){
                    showBottom();
                }
                //如果手指是下拉状态，隐藏底部
                if (distance > touchSlop){
                    hideBottom();
                }
                break;
            case MotionEvent.ACTION_UP:
            default:
                //手指抬起，显示底部
                break;
        }
        return true;
    }
}
