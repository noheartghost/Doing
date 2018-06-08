package app.doing.com.doing.utils.SliderBanner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.List;

/**
 * Created by cherry on 18-6-8.
 */

public class CardBanner implements Runnable{

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private Handler handler;
    private boolean isAlive;
    private boolean isContinue;
    private int position;
    private int size;
    private int changeTime;


    public CardBanner(List<Integer> images, Context context, FragmentManager fragmentManager, ViewPager viewPager,Handler handler){
        position = 1;
        //set the default change time
        changeTime = 3000;
        mCardAdapter = new CardPagerAdapter();
        size = images.size();
        isAlive = true;

        for(Integer imagesId: images){
            mCardAdapter.addCardItem(new CardItem(imagesId));
        }

        mCardShadowTransformer = new ShadowTransformer(viewPager, mCardAdapter);
        this.handler = handler;

        viewPager.setAdapter(mCardAdapter);
        viewPager.setPageTransformer(false, mCardShadowTransformer);
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
