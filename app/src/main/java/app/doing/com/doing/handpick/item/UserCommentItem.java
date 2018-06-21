package app.doing.com.doing.handpick.item;

/**
 * Created by cherry on 18-6-18.
 */

public class UserCommentItem {
    private String name;
    private String content;
    private int imageId;
    private String date;
    private String time;
    private int thumbsNum;

    public UserCommentItem(String name, String content, int imageId, String date, String time, int thumbsNum) {
        this.name = name;
        this.content = content;
        this.imageId = imageId;
        this.date = date;
        this.time = time;
        this.thumbsNum = thumbsNum;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getImageId() {
        return imageId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getThumbsNum() {
        return thumbsNum;
    }
}
