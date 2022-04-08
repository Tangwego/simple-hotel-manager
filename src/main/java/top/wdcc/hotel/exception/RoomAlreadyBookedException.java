package top.wdcc.hotel.exception;

/**
 * A custom exception for room booked
 * @author wavin
 * @date 2022/4/8
 */
public class RoomAlreadyBookedException extends Exception {
    public RoomAlreadyBookedException(String roomNumber) {
        super("Room " + roomNumber + " is already booked, please select another one.");
    }
}
