package app.doing.com.doing.moment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import app.doing.com.doing.AddContentActivity;
import app.doing.com.doing.R;
import app.doing.com.doing.others.MomentListAdapter;

public class MomentFragment extends Fragment {

    private FloatingActionButton fab;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View momentFragment = layoutInflater.inflate(R.layout.moment_fragment,container,false);
        initViews(momentFragment);
        return momentFragment;
    }

    //初始化视图
    private void initViews(View view){
        ListView listview = view.findViewById(R.id.listview);
        listview.setAdapter(new MomentListAdapter(view.getContext()));

        fab = view.findViewById(R.id.fab_addmom);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddContentActivity.class);
                startActivity(intent);
            }
        });
    }
}
