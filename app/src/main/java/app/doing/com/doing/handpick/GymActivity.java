package app.doing.com.doing.handpick;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.handpick.adapter.GymItemAdapter;
import app.doing.com.doing.handpick.item.GymItem;

public class GymActivity extends Activity {

    private List<GymItem> gymItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

        getGymItemList();
        RecyclerView recyclerView = findViewById(R.id.recycler_gym);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getApplication(),2);
        recyclerView.setLayoutManager(layoutManager);

        GymItemAdapter gymItemAdapter = new GymItemAdapter(gymItemList);
        recyclerView.setAdapter(gymItemAdapter);

    }

    private void getGymItemList(){
        for(int i=0;i<5;i++){
            GymItem gymItem1 = new GymItem(R.drawable.gtm_item_pic,"宝力豪健身","特别好");
            gymItemList.add(gymItem1);
            GymItem gymItem2 = new GymItem(R.drawable.gtm_item_pic,"宝力豪健身","特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好");
            gymItemList.add(gymItem2);
            GymItem gymItem3 = new GymItem(R.drawable.gtm_item_pic,"宝力豪健身","特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好特别好");
            gymItemList.add(gymItem3);
        }

    }
}
