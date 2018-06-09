package app.doing.com.doing.handpick.item;

/**
 * Created by cherry on 18-6-9.
 */

public class CoachListItem {
    private int imageId;
    private String name;
    private String pride;
    private String tag;
    private String description;
    private float rating;

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

    public float getRating() {
        return rating;
    }

    public CoachListItem(int imageId, String name, String pride, String tag, String description, float rating) {
        this.imageId = imageId;
        this.name = name;
        this.pride = pride;

        this.tag = tag;
        this.description = description;
        this.rating = rating;
    }
}
