package app.doing.com.doing.utils.ParseJSONWithJSONObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.doing.com.doing.handpick.item.CoachListItem;
import app.doing.com.doing.handpick.item.GymListItem;
import app.doing.com.doing.handpick.item.ListItem;

/**
 * Created by cherry on 18-6-15.
 * 工具类，将JSon转换为Object
 */

public final class ParseJSONWithJSONObject {
    public static final int COACH = 0;
    public static final int GYM = 1;
    public static final int COURSE = 2;
    public static final int SELECTED_COACH = 3;
    public static final int SELECTED_GYM = 4;
    public static final int SELECTED_COURSE = 5;

    private  ParseJSONWithJSONObject(){

    }

    public static void parseListItem(List<ListItem> list, String jsonData,int type){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            int length = jsonArray.length();
            switch (type){
                case COACH:


                case GYM:
                    //按健身场馆初始化数据
                    for(int i=0;i<length;i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        list.add(new GymListItem(
                                jsonObject.getString("gymid"),
                                jsonObject.getString("name"),
                                jsonObject.getString("tags"),
                                jsonObject.getString("introduce"),
                                Float.parseFloat(jsonObject.getString("score"))));
                    }
                case COURSE:

                case SELECTED_COACH:

                case SELECTED_GYM:
                    //按推荐场馆初始化数据
                    for(int i=0;i<length;i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        list.add(new GymListItem(
                                jsonObject.getString("gymid"),
                                jsonObject.getString("gymname"),
                                jsonObject.getString("location"),
                                jsonObject.getDouble("score"),
                                jsonObject.getInt("commentnum"),
                                jsonObject.getInt("distance")));
                    }
                case SELECTED_COURSE:

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
