package app.doing.com.doing.utils.SliderBanner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.doing.com.doing.R;

/**
 * Created by cherry on 18-6-13.
 * 仅存放1张图片的banner适配器
 */

public class DoingPagerAdapter extends PagerAdapter {
    private List<Integer> list;
    private Context context;

    public DoingPagerAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //后期改为从网络获取图片
        Glide.with(context).load(list.get(position))
                .placeholder(R.drawable.rectangle_placeholder).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
