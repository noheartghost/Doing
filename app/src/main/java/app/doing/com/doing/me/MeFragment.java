package app.doing.com.doing.me;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import app.doing.com.doing.R;
import app.doing.com.doing.customView.CircleImageView;


/**
 * Created by cherry on 18-5-21.
 * 我的页面
 */

public class MeFragment extends Fragment {
    private CircleImageView circleImageView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View meFragment = layoutInflater.inflate(R.layout.me_fragment,container,false);
        circleImageView = meFragment.findViewById(R.id.me_head_potrait);
        Glide.with(this).load(R.drawable.gtm_item_pic).dontAnimate().into(circleImageView);
        return meFragment;
    }


}