package app.doing.com.doing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ViewPagerClass viewPager;
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        initView();
        viewPager.setViewPagerViews(views);
    }
    //定义轮播图片
    public void initView(){
        views = new ArrayList<>();
        ImageView imageView1 = new ImageView(getApplicationContext()) ;
        ImageView imageView2 = new ImageView(getApplicationContext());
        ImageView imageView3 = new ImageView(getApplicationContext());
        ImageView imageView4 = new ImageView(getApplicationContext());
        imageView1.setBackgroundResource(R.drawable.gym1);
        views.add(imageView1);
        imageView2.setBackgroundResource(R.drawable.gym2);
        views.add(imageView2);
        imageView3.setBackgroundResource(R.drawable.gym3);
        views.add(imageView3);
        imageView4.setBackgroundResource(R.drawable.gym4);
        views.add(imageView4);
    }
}
