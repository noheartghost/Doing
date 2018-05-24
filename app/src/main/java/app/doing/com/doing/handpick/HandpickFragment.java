package app.doing.com.doing.handpick;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.doing.com.doing.R;


/**
 * Created by cherry on 18-5-21.
 */

public class HandpickFragment extends Fragment {


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View handpickFragment = layoutInflater.inflate(R.layout.handpick_fragment,container,false);

        return handpickFragment;
    }


}