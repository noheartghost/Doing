package app.doing.com.doing.utils.SliderBanner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.item.GymListItem;

/**
 * Created by cherry on 18-6-14.
 */

public class DoingCardPagerAdapter extends PagerAdapter{
    private List<GymListItem> list;
    private Context context;
    private LayoutInflater inflater;

    public DoingCardPagerAdapter( Context context,List<GymListItem> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
        View view = inflater.inflate(R.layout.gym_banner_item,container,false);
        //获取布局资源id
        ImageView imageView = view.findViewById(R.id.gym_banner_image);
        TextView name = view.findViewById(R.id.gym_banner_name);
        TextView address = view.findViewById(R.id.gym_banner_address);
        TextView tag = view.findViewById(R.id.gym_banner_tag);
        RatingBar ratingBar = view.findViewById(R.id.gym_banner_ratingbar);
        //设置内容
        GymListItem gymListItem = list.get(position);
        Glide.with(context).load(gymListItem.getImageId()).into(imageView);
        name.setText(gymListItem.getName());
        address.setText(gymListItem.getAddress());
        tag.setText(gymListItem.getTag());
       // ratingBar.setRating(gymListItem.getRating());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
