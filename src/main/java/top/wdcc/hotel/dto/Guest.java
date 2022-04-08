package top.wdcc.hotel.dto;

import java.io.Serializable;

/**
 * An booking entity for guest information
 * @author wavin
 * @date 2022/4/8
 */
public class Guest implements Serializable {

    private static final long serialVersionUID = -4587315881300324055L;

    /**
     * guest name
     */
    private String name;

    /**
     * room number
     */
    private String roomNumber;

    /**
     * date
     * @desc for simple, use timestamp instead of real date
     */
    private long date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", date=" + date +
                '}';
    }
}
