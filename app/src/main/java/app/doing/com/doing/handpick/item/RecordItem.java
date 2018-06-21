package app.doing.com.doing.handpick.item;

import java.util.List;

/**
 * Created by cherry on 18-6-10.
 */

public class RecordItem {
    private String name;
    private String address;
    private String date;
    private String person;
    private String phone;
    private String time;
    //可能存在多张代金券
    private List<Integer> ticket;
    private float price;

    public RecordItem(String name, String address, String date, String person, String phone, String time, List<Integer> ticket, float price) {
        this.name = name;
        this.address = address;
        this.date = date;
        this.person = person;
        this.phone = phone;
        this.time = time;
        this.ticket = ticket;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getPerson() {
        return person;
    }

    public String getPhone() {
        return phone;
    }

    public String getTime() {
        return time;
    }

    public List<Integer>  getTicket() {
        return ticket;
    }

    public float getPrice() {
        return price;
    }
}
