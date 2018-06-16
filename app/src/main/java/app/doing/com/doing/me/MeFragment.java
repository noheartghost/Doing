package app.doing.com.doing.me;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.doing.com.doing.R;


/**
 * Created by cherry on 18-5-21.
 * 我的页面
 */

public class MeFragment extends Fragment {

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View meFragment = layoutInflater.inflate(R.layout.me_fragment,container,false);
        return meFragment;
    }


}