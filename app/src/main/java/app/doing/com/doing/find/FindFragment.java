package app.doing.com.doing.find;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.others.Find;
import app.doing.com.doing.others.FindAdapter;
import app.doing.com.doing.others.SpacesItemDecoration;


public class FindFragment extends Fragment{

    private RecyclerView recyclerView;
    private List<Find> productList;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View findFragment = layoutInflater.inflate(R.layout.find_fragment,container,false);

        initViews(findFragment);



        return findFragment;
    }

    //初始化界面元素
    private void initViews(View view){
        recyclerView= view.findViewById(R.id.recycler);
        //设置layoutManager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //设置adapter
        initData();
        FindAdapter adapter=new FindAdapter(productList);
        recyclerView.setAdapter(adapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);

    }

    //填充数据
    private void initData() {
        productList=new ArrayList<Find>();
        Find p1=new Find(R.drawable.pic1,"我是照片1");
        productList.add(p1);
        Find p2=new Find(R.drawable.pic2,"我是照片2");
        productList.add(p2);
        Find p3=new Find(R.drawable.pic3,"我是照片3");
        productList.add(p3);
        Find p4=new Find(R.drawable.pic4,"我是照片4");
        productList.add(p4);
        Find p5=new Find(R.drawable.pic5,"我是照片5");
        productList.add(p5);
        Find p6=new Find(R.drawable.pic6,"我是照片6");
        productList.add(p6);
        Find p7=new Find(R.drawable.pic7,"我是照片7");
        productList.add(p7);
        Find p8=new Find(R.drawable.pic8,"我是照片8");
        productList.add(p8);
        Find p9=new Find(R.drawable.pic1,"我是照片9");
        productList.add(p9);
        Find p10=new Find(R.drawable.pic2,"我是照片10");
        productList.add(p10);
        Find p11=new Find(R.drawable.pic3,"我是照片11");
        productList.add(p11);
        Find p12=new Find(R.drawable.pic4,"我是照片12");
        productList.add(p12);
        Find p13=new Find(R.drawable.pic5,"我是照片13");
        productList.add(p13);
        Find p14=new Find(R.drawable.pic6,"我是照片14");
        productList.add(p14);
        Find p15=new Find(R.drawable.pic7,"我是照片15");
        productList.add(p15);
        Find p16=new Find(R.drawable.pic8,"我是照片16");
        productList.add(p16);

    }
}
