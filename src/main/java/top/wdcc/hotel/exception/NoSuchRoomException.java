package top.wdcc.hotel.exception;

/**
 * A custom exception for no such room
 * @author wavin
 * @date 2022/4/8
 */
public class NoSuchRoomException extends Exception {
    public NoSuchRoomException(String roomNumber) {
        super("No such room, number:" + roomNumber);
    }
}
