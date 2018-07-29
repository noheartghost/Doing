package app.doing.com.doing.moment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.AddContentActivity;
import app.doing.com.doing.R;
import app.doing.com.doing.others.MomentListAdapter;

public class MomentFragment extends Fragment {

    private FloatingActionButton fab;

    List<CityItem> cityList;
    RelativeLayout itmel;
    GridView gridView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View momentFragment = layoutInflater.inflate(R.layout.moment_fragment,container,false);
        initViews(momentFragment);


        setData();
        setGridView();

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

        //横向list
        gridView = view.findViewById(R.id.grid);
    }

    /**设置数据*/
    private void setData() {
        cityList = new ArrayList<CityItem>();
        CityItem item = new CityItem();
        item.setCityName("张伟");
        item.setCityCode("25岁");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("李亮");
        item.setCityCode("18岁");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("王元朝");
        item.setCityCode("56岁");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("徐梅");
        item.setCityCode("26岁");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("张海姚");
        item.setCityCode("35岁");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("王琪奇");
        item.setCityCode("19岁");
        cityList.add(item);
        cityList.addAll(cityList);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView() {
        int size = cityList.size();
        int length = 100;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(5); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        GridViewAdapter adapter = new GridViewAdapter(this.getActivity(), cityList);
        gridView.setAdapter(adapter);
    }

    /**GirdView 数据适配器*/
    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<CityItem> list;
        public GridViewAdapter(Context _context, List<CityItem> _list) {
            this.list = _list;
            this.context = _context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.moment_item, null);
            TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
            TextView tvCode = (TextView) convertView.findViewById(R.id.tvCode);
            CityItem city = list.get(position);

            tvCity.setText(city.getCityName());
            tvCode.setText(city.getCityCode());
            return convertView;
        }
    }

    public class CityItem {
        private String cityName;
        private String cityCode;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }
    }
}
