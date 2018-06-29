package app.doing.com.doing.moment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import app.doing.com.doing.R;
import app.doing.com.doing.others.MomentListAdapter;


/**
 * Created by cherry on 18-5-21.
 */

public class MomentFragment extends Fragment {

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View momentFragment = layoutInflater.inflate(R.layout.moment_fragment,container,false);
        initViews(momentFragment);
        return momentFragment;
    }

    //初始化视图
    private void initViews(View view){
        ListView listview = view.findViewById(R.id.listview);
        listview.setAdapter(new MomentListAdapter(view.getContext()));
    }
}
