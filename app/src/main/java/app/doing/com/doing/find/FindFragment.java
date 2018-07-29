package app.doing.com.doing.find;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.others.FindFragmentAdapter;
import app.doing.com.doing.others.FindTabFragment;



public class FindFragment extends Fragment {

    private String[] titles = new String[]{"推荐", "运动", "饮食", "器械"};
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FindFragmentAdapter ffadapter;

    //ViewPage选项卡页面列表
    private List<Fragment> mFragments;
    private List<String> mTitles;


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){

        View findFragment = layoutInflater.inflate(R.layout.find_fragment,container,false);

        initViews(findFragment);

        return findFragment;
    }

    //初始化界面元素
    private void initViews(View view){

        //标签页初始化
        mViewPager = view.findViewById(R.id.viewpager);
        mTabLayout = view.findViewById(R.id.tablayout);

        mTitles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mTitles.add(titles[i]);
        }
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            mFragments.add(FindTabFragment.newInstance(i));
        }
        ffadapter = new FindFragmentAdapter(getFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(ffadapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来

    }

}
