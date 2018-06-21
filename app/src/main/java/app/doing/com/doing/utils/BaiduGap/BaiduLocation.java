package app.doing.com.doing.utils.BaiduGap;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.MainActivity;

/**
 * Created by cherry on 18-6-16.
 * 调用百度定位
 */

public class BaiduLocation {
    public LocationClient mLocationClient;

    public BaiduLocation(Activity activity, Context context, BDAbstractLocationListener locationListener){
        mLocationClient = new LocationClient(context);
        mLocationClient.registerLocationListener(locationListener);

        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(activity,Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(activity,Manifest.permission.READ_PHONE_STATE)
                !=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(activity,permissions,1);
        }else{
            requestLocation();
        }

    }

    public void requestLocation(){
        mLocationClient.start();
    }

}
