package app.doing.com.doing.utils.SliderBanner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by cherry on 18-6-13.
 * DoingBanner用于设置banner的基本信息，包括切换时间、内容个数等
 * 同时规定该banner线程的活动内容
 */

public class DoingBanner implements Runnable{

    private DoingPagerAdapter mpageAdapter;
    private Handler handler;
    //判断当前banner是否处于活动状态
    private boolean isAlive;
    private boolean isContinue;
    private int position;
    private int size;
    private int changeTime;


    public DoingBanner(List<Integer> images, Context context, FragmentManager fragmentManager, ViewPager viewPager, Handler handler){
        position = 1;
        //set the default change time
        changeTime = 3000;
        mpageAdapter = new DoingPagerAdapter(context, images);
        size = images.size();
        isAlive = true;

        this.handler = handler;

        viewPager.setAdapter(mpageAdapter);
        viewPager.setOffscreenPageLimit(3);
    }

    public void setChangeTime(int changeTime){
        this.changeTime = changeTime;
    }

    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    @Override
    public void run() {
        while(isAlive){

            handler.sendEmptyMessage(position);
            position = (position+1)%size;
            try {
                Thread.sleep(changeTime);
            } catch (InterruptedException e) {
            }
        }
    }
}
