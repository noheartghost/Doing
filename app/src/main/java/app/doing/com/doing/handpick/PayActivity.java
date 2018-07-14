package app.doing.com.doing.handpick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.R;
import app.doing.com.doing.customView.NavigatorBarCustom;
import app.doing.com.doing.customView.RecordItemCustom;
import app.doing.com.doing.handpick.item.RecordItem;

/*
支付页面
 */

public class PayActivity extends AppCompatActivity implements View.OnClickListener{
    private RecordItem recordItem;
    private TextView gymName;
    private TextView address;
    private RecordItemCustom date;
    private RecordItemCustom person;
    private RecordItemCustom phone;
    private RecordItemCustom time;
    private TextView tickets;
    private TextView price;
    private LinearLayout ticketList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        getRecord();
        initView();
    }

    private void getRecord(){
        //暂时使用本地数据
        List<Integer> tickets = new ArrayList<>();
        tickets.add(10);
        tickets.add(20);
        recordItem = new RecordItem("宝力豪健身（大悦城南区）",
                "天津市津南区海河教育园区同砚路","2018/8/8","TOM","12345678901","18:00前",tickets,(float) 60.5);
    }

    private void initView(){
        //设置导航栏左右键的监听事件
        NavigatorBarCustom navigatorBarCustom = (NavigatorBarCustom) findViewById(R.id.pay_navigator);
        navigatorBarCustom.setLeftImageButtonListener(this);
        navigatorBarCustom.setRightImageButtonListener(this);

        gymName = (TextView) findViewById(R.id.pay_name);
        address = (TextView) findViewById(R.id.pay_address);
        date = (RecordItemCustom) findViewById(R.id.record_date);
        person = (RecordItemCustom) findViewById(R.id.record_person);
        phone = (RecordItemCustom) findViewById(R.id.record_phone);
        time = (RecordItemCustom) findViewById(R.id.record_time);
        tickets = (TextView) findViewById(R.id.record_ticket);
        price = (TextView) findViewById(R.id.record_price);
        ticketList = (LinearLayout) findViewById(R.id.record_ticket_list);

        gymName.setText(recordItem.getName());
        address.setText(recordItem.getAddress());
        date.setContent(recordItem.getDate());
        person.setContent(recordItem.getPerson());
        phone.setContent(recordItem.getPhone());
        time.setContent(recordItem.getTime());

        if(null==recordItem.getTicket()){
            tickets.setText("无可用代金券");
        }else{
            tickets.setText(recordItem.getTicket().get(0)+"¥代金券可用");

            //存在多张代金券需要动态添加view
            int size = recordItem.getTicket().size();
            if(recordItem.getTicket().size()>1){
                for(int i=1;i<size;i++){
                    addTicketItem(recordItem.getTicket().get(i));
                }
            }
        }

        price.setText(""+recordItem.getPrice());

    }

    /*
    添加代金券item项
     */
    private void addTicketItem(int text){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        TextView textView = (TextView) layoutInflater.inflate(R.layout.ticket_item_layout,null,false);
        textView.setText(text+"¥代金券可用");
        textView.setPadding(0,20,0,0);
        ticketList.addView(textView);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nav_image_left:
                this.finish();
                break;
        }
    }
}
