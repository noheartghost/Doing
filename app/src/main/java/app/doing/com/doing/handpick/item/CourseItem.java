package app.doing.com.doing.handpick.item;

/**
 * Created by cherry on 18-5-25.
 */

public class CourseItem {
    private int imageId;
    private String name;
    //课程参加人数
    private int personCount;
    //课程时间安排
    private String schedule;
    private String duration;
    private String description;

    public CourseItem(int imageId, String name, int personCount, String schedule, String duration, String description) {
        this.imageId = imageId;
        this.name = name;
        this.personCount = personCount;
        this.schedule = schedule;
        this.duration = duration;
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public int getPersonCount() {
        return personCount;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }
}
