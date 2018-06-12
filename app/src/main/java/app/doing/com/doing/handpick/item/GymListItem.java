package app.doing.com.doing.handpick.item;

import android.app.LauncherActivity;

/**
 * Created by cherry on 18-6-9.
 */

public class GymListItem implements ListItem{
    private int imageId;
    private String name;
    private String address;
    private float rating;
    //评论数
    private int views;
    private int distance;
    private String tag;
    private String description;

    //按健身场馆初始化
    public GymListItem(int imageId, String name, String tag, String description,float rating ) {
        this.imageId = imageId;
        this.name = name;
        this.rating = rating;
        this.tag = tag;
        this.description = description;
    }

    //按精选场馆初始化
    public GymListItem(int imageId, String name, String address, float rating, int views, int distance) {
        this.imageId = imageId;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.views = views;
        this.distance = distance;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public float getRating() {
        return rating;
    }

    public int getViews() {
        return views;
    }

    public int getDistance() {
        return distance;
    }

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }


}
