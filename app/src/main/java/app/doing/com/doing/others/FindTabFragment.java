package app.doing.com.doing.others;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.AddContentActivity;
import app.doing.com.doing.R;

public class FindTabFragment extends Fragment {
    //private TextView textView;
    private RecyclerView recyclerView;
    private List<Find> productList;
    private FloatingActionButton fab;

    public static FindTabFragment newInstance(int index){
        Bundle bundle = new Bundle();
        bundle.putInt("index", 'A' + index);
        FindTabFragment fragment = new FindTabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_tab_fragment, container, false);

//        textView = view.findViewById(R.id.text);
//        textView.setText(String.valueOf((char) getArguments().getInt("index")));

        recyclerView= view.findViewById(R.id.recycler);
        //设置layoutManager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //设置adapter
        initData();
        FindAdapter findadapter=new FindAdapter(productList);
        recyclerView.setAdapter(findadapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);

        //悬浮按钮
        fab = view.findViewById(R.id.fab_addfind);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddContentActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    //填充数据
    private void initData() {
        productList=new ArrayList<Find>();
        String str = String.valueOf((char) getArguments().getInt("index"));
        Find p1=new Find(R.drawable.pic1,"我是照片1"+str);
        productList.add(p1);
        Find p2=new Find(R.drawable.pic2,"我是照片2"+str);
        productList.add(p2);
        Find p3=new Find(R.drawable.pic3,"我是照片3"+str);
        productList.add(p3);
        Find p4=new Find(R.drawable.pic4,"我是照片4"+str);
        productList.add(p4);
        Find p5=new Find(R.drawable.pic5,"我是照片5"+str);
        productList.add(p5);
        Find p6=new Find(R.drawable.pic6,"我是照片6"+str);
        productList.add(p6);
        Find p7=new Find(R.drawable.pic7,"我是照片7"+str);
        productList.add(p7);
        Find p8=new Find(R.drawable.pic8,"我是照片8"+str);
        productList.add(p8);
        Find p9=new Find(R.drawable.pic1,"我是照片9"+str);
        productList.add(p9);
        Find p10=new Find(R.drawable.pic2,"我是照片10"+str);
        productList.add(p10);
        Find p11=new Find(R.drawable.pic3,"我是照片11"+str);
        productList.add(p11);
        Find p12=new Find(R.drawable.pic4,"我是照片12"+str);
        productList.add(p12);
        Find p13=new Find(R.drawable.pic5,"我是照片13"+str);
        productList.add(p13);
        Find p14=new Find(R.drawable.pic6,"我是照片14"+str);
        productList.add(p14);
        Find p15=new Find(R.drawable.pic7,"我是照片15"+str);
        productList.add(p15);
        Find p16=new Find(R.drawable.pic8,"我是照片16"+str);
        productList.add(p16);

    }
}