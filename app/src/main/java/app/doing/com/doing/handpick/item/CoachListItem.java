package app.doing.com.doing.handpick.item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cherry on 18-6-9.
 */

public class CoachListItem implements ListItem{
    private int imageId;
    private String name;
    private String pride;
    private String tag;
    private String description;
    private int evaluatedNum;
    private float rating;

    //按明星教练初始化
    public CoachListItem(int imageId, String name, String pride, String tag, String description, float rating, int evaluatedNum) {
        this.imageId = imageId;
        this.name = name;
        this.pride = pride;
        this.tag = tag;
        this.description = description;
        this.evaluatedNum = evaluatedNum;
        this.rating = rating;
    }

    //按健身教练初始化
    public CoachListItem(int imageId, String name, String pride, String tag, String description, float rating) {
        this.imageId = imageId;
        this.name = name;
        this.pride = pride;
        this.tag = tag;
        this.description = description;
        this.rating = rating;
    }



    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getPride() {
        return pride;
    }

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }

    public int getEvaluatedNum() {
        return evaluatedNum;
    }

    public float getRating() {
        return rating;
    }
}
