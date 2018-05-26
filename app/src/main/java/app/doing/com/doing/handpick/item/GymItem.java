package app.doing.com.doing.handpick.item;

/**
 * Created by cherry on 18-5-24.
 */

public class GymItem {
    private int imageId;
    private String name;
    private String description;

    public GymItem(int imageId,String name,String description){
        this.imageId = imageId;
        this.name = name;
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
