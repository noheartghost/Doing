package app.doing.com.doing.find;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.doing.com.doing.R;


/**
 * Created by cherry on 18-5-21.
 */

public class FindFragment extends Fragment{

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View findFragment = layoutInflater.inflate(R.layout.find_fragment,container,false);
        return findFragment;
    }
}
