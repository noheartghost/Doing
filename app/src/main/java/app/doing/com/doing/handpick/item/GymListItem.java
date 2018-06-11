package app.doing.com.doing.handpick.item;

import android.app.LauncherActivity;

/**
 * Created by cherry on 18-6-9.
 */

public class GymListItem implements ListItem{
    private int imageId;
    private String name;
    private String district;
    private String address;
    private float rating;
    private int views;
    private int distance;

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
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

    public GymListItem(int imageId, String name, String district, String address, float rating, int views, int distance) {
        this.imageId = imageId;
        this.name = name;

        this.district = district;
        this.address = address;
        this.rating = rating;
        this.views = views;
        this.distance = distance;
    }
}
