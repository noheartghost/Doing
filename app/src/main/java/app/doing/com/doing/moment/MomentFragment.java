package app.doing.com.doing.moment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.doing.com.doing.R;


/**
 * Created by cherry on 18-5-21.
 */

public class MomentFragment extends Fragment {

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View momentFragment = layoutInflater.inflate(R.layout.moment_fragment,container,false);
        return momentFragment;
    }
}
