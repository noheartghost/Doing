package app.doing.com.doing.handpick.item;

/**
 * Created by cherry on 18-6-9.
 */

public class CourseListItem implements ListItem{
    private int imageId;
    private String name;
    private int persons;
    private String tag;
    private String description;
    private float rating;

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public int getPersons() {
        return persons;
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

    public CourseListItem(int imageId, String name, int persons, String tag, String description, float rating) {
        this.imageId = imageId;
        this.name = name;
        this.persons = persons;
        this.tag = tag;
        this.description = description;
        this.rating = rating;

    }
}
