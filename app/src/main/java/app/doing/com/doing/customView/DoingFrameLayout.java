package app.doing.com.doing.customView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by cherry on 18-6-12.
 * 用于控制底部栏的显示和隐藏
 */

public class DoingFrameLayout extends FrameLayout{
    //手指按下时的屏幕y坐标
    private float yDown;
    //在被判定为滚动之前用户手指可以移动的最大值
    private int touchSlop;
    //控制此view的可见与隐藏
    private View view;
    public DoingFrameLayout(@NonNull Context context) {
        super(context);
    }

    public DoingFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 用于设置显示和隐藏的ui布局
     * @param view ui布局
     */
    public void setView(View view){
        this.view = view;
    }

    @Override

    public boolean onInterceptTouchEvent(MotionEvent event){
        //监听滚动事件，控制view的可见与隐藏
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                yDown = event.getRawY();//记录滚动的初始位置
                break;
            case MotionEvent.ACTION_MOVE:
                float yRaw = event.getRawY();
                int distance = (int)(yRaw - yDown);
                //如果手指是上拉状态，显示底部
                if (distance <= -touchSlop){
                    showView();
                }
                //如果手指是下拉状态，隐藏底部
                if (distance > touchSlop){
                    hideView();
                }
                break;
            case MotionEvent.ACTION_UP:
            default:
                //手指抬起，显示底部
                break;
        }
        return false;
    }

    /*隐藏底部菜单*/
    private void hideView(){
        view.setVisibility(View.GONE);
    }

    /*显示底部菜单*/
    private void showView(){
        view.setVisibility(View.VISIBLE);
    }



}
