package app.doing.com.doing.handpick;

import android.app.Fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.doing.com.doing.R;
import app.doing.com.doing.customView.ImageButtonCustom;


/**
 * Created by cherry on 18-5-21.
 */

public class HandpickFragment extends Fragment implements View.OnClickListener{
    private ImageButtonCustom gym_ibc;
    private ImageButtonCustom course_ibc;
    private ImageButtonCustom coach_ibc;


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View handpickFragment = layoutInflater.inflate(R.layout.handpick_fragment,container,false);
        initViews(handpickFragment);

        return handpickFragment;
    }

    private void initViews(View view){
        gym_ibc = view.findViewById(R.id.gym_image);
        course_ibc = view.findViewById(R.id.course_image);
        coach_ibc = view.findViewById(R.id.coach_image);

        gym_ibc.setOnClickListener(this);
        course_ibc.setOnClickListener(this);
        coach_ibc.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //判断点击的按钮
        Intent intent;
        switch (v.getId()){
            case R.id.gym_image:
                intent = new Intent(getActivity(),GymListActivity.class);
                startActivity(intent);
                break;
            case R.id.course_image:
                intent = new Intent(getActivity(),CourseListActivity.class);
                startActivity(intent);
                break;
            case R.id.coach_image:
                intent = new Intent(getActivity(),CoachListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}