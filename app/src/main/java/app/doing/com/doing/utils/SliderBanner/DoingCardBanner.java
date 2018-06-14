package app.doing.com.doing.utils.SliderBanner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.List;

import app.doing.com.doing.handpick.item.GymListItem;

/**
 * Created by cherry on 18-6-14.
 */

/*

 */
public class DoingCardBanner implements Runnable{
    private DoingCardPagerAdapter mpageAdapter;
    private Handler handler;
    private boolean isAlive;
    private int position;
    private int size;
    private int changeTime;

    public DoingCardBanner(List<GymListItem> list,Context context,   FragmentManager fragmentManager, ViewPager viewPager, Handler handler){
        position = 1;
        //set the default change time
        changeTime = 3000;
        mpageAdapter = new DoingCardPagerAdapter(context,list);
        size = list.size();
        isAlive = true;

        this.handler = handler;

        viewPager.setAdapter(mpageAdapter);
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
