package app.doing.com.doing;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewConfiguration;

import app.doing.com.doing.customView.DoingFrameLayout;
import app.doing.com.doing.customView.ImageButtonCustom;
import app.doing.com.doing.find.FindFragment;
import app.doing.com.doing.handpick.HandpickFragment;
import app.doing.com.doing.me.MeFragment;
import app.doing.com.doing.moment.MomentFragment;

public class MainActivity extends Activity implements View.OnClickListener{
    //精选、发现、圈子和我的四个碎片
    private HandpickFragment handpickFragment;
    private FindFragment findFragment;
    private MomentFragment momentFragment;
    private MeFragment meFragment;

    //四个碎片对应的入口按钮
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
    //底部栏，便于控制底部栏的显示和隐藏
    private ConstraintLayout bottomLayout;
    //使用自定义Fragment控件，用于监听屏幕滚动事件并设置底部栏的显示与隐藏
    private DoingFrameLayout frameLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化布局元素
        initViews();
        setTabSelection(0);
    }

    private void initViews(){
        touchSlop = ViewConfiguration.get(this.getApplicationContext()).getScaledTouchSlop();
        bottomLayout = findViewById(R.id.bottom_tap);
        fragmentManager = getFragmentManager();
        frameLayout = findViewById(R.id.content);
        //设置显示与隐藏的ui控件为底部栏
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

    /*
    设置所有按钮为未选中时样式
     */
    private void resetTabIcon(){
        handpickText.setImage(R.drawable.handpick_icon);
        handpickText.setTextUnSelected();
        findText.setImage(R.drawable.find_icon);
        findText.setTextUnSelected();
        momentText.setImage(R.drawable.moment_icon);
        momentText.setTextUnSelected();
        meText.setImage(R.drawable.me_icon);
        meText.setTextUnSelected();
    }

    /*根据传入的index设置显示的tab*/
    private void setTabSelection(int index){
        //每次选中新按钮都需要重设一遍样式
        resetTabIcon();
        //每次切换fragment时，开启一个新的fragment事物
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //隐藏原有fragment
        hideFragments(transaction);
        switch(index){
            case 0:
                //设置该项为选中
                handpickText.setImage(R.drawable.handpick_selected_icon);
                handpickText.setTextSelected();
                if(handpickFragment == null){
                    handpickFragment = new HandpickFragment();
                    transaction.add(R.id.content,handpickFragment);
                }else{
                    transaction.show(handpickFragment);
                }
                break;
            case 1:
                //设置该项为选中
                findText.setImage(R.drawable.find_selected_icon);
                findText.setTextSelected();
                if(findFragment == null){
                    findFragment = new FindFragment();
                    transaction.add(R.id.content, findFragment);
                }else{
                    transaction.show(findFragment);
                }
                break;
            case 2:
                //设置该项为选中
                momentText.setImage(R.drawable.moment_selected_icon);
                momentText.setTextSelected();
                if(momentFragment == null){
                    momentFragment = new MomentFragment();
                    transaction.add(R.id.content,momentFragment);
                }else{
                    transaction.show(momentFragment);
                }
                break;
            case 3:
            default:
                //设置该项为选中
                meText.setImage(R.drawable.me_selected_icon);
                meText.setTextSelected();
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

}
