package app.doing.com.doing;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.view.View;
import android.widget.Toast;

import app.doing.com.doing.customView.DoingFrameLayout;
import app.doing.com.doing.customView.ImageButtonCustom;
import app.doing.com.doing.find.FindFragment;
import app.doing.com.doing.handpick.HandpickFragment;
import app.doing.com.doing.me.MeFragment;
import app.doing.com.doing.moment.MomentFragment;

public class MainActivity extends Activity implements View.OnClickListener,View.OnTouchListener{
    private HandpickFragment handpickFragment;
    private FindFragment findFragment;
    private MomentFragment momentFragment;
    private MeFragment meFragment;

    private ImageButtonCustom handpickText;
    private ImageButtonCustom findText;
    private ImageButtonCustom momentText;
    private ImageButtonCustom meText;

    /*对Fragment进行管理*/
    private FragmentManager fragmentManager;

    //手指按下时的屏幕y坐标
    private float yDown;
    //在被判定为滚动之前用户手指可以移动的最大值
    private int touchSlop;

    private ConstraintLayout bottomLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
      

    }

    private void initViews(){
        touchSlop = ViewConfiguration.get(this.getApplicationContext()).getScaledTouchSlop();
        bottomLayout = findViewById(R.id.bottom_tap);

        DoingFrameLayout frameLayout = findViewById(R.id.content);
        // frameLayout.setOnTouchListener(this);
        frameLayout.setView(bottomLayout);

        handpickText = findViewById(R.id.handpick_text);
        findText = findViewById(R.id.find_text);
        momentText = findViewById(R.id.moment_text);
        meText = findViewById(R.id.me_text);
        handpickText.setOnClickListener(this);
        findText.setOnClickListener(this);
        momentText.setOnClickListener(this);
        meText.setOnClickListener(this);
        
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

    private void resetTabIcon(){
        handpickText.setImage(R.drawable.handpick_icon);
        findText.setImage(R.drawable.find_icon);
        momentText.setImage(R.drawable.moment_icon);
        meText.setImage(R.drawable.me_icon);
    }

    /*根据传入的index设置显示的tab*/
    private void setTabSelection(int index){
        resetTabIcon();
        //每次切换fragment时，开启一个新的fragment事物
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch(index){
            case 0:
                handpickText.setImage(R.drawable.handpick_selected_icon);
                if(handpickFragment == null){
                    handpickFragment = new HandpickFragment();

                    transaction.add(R.id.content,handpickFragment);
                }else{
                    transaction.show(handpickFragment);
                }
                break;
            case 1:
                findText.setImage(R.drawable.find_selected_icon);
                if(findFragment == null){
                    findFragment = new FindFragment();
                    transaction.add(R.id.content,findFragment);
                }else{
                    transaction.show(findFragment);
                }
                break;
            case 2:
                momentText.setImage(R.drawable.moment_selected_icon);
                if(momentFragment == null){
                    momentFragment = new MomentFragment();
                    transaction.add(R.id.content,momentFragment);
                }else{
                    transaction.show(momentFragment);
                }
                break;
            case 3:
            default:
                meText.setImage(R.drawable.me_selected_icon);
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
        bottomLayout.setVisibility(View.GONE);
    }

    /*显示底部菜单*/
    private void showBottom(){
        bottomLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("activity","滑动");
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
        return false;
    }


}
