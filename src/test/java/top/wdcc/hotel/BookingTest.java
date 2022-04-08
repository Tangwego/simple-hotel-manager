package top.wdcc.hotel;

import org.junit.Test;
import top.wdcc.hotel.dto.Guest;
import top.wdcc.hotel.exception.NoSuchRoomException;
import top.wdcc.hotel.exception.RoomAlreadyBookedException;
import top.wdcc.hotel.service.RoomService;

/**
 * a booking test case
 * @author wavin
 * @date 2022/4/8
 */
public class BookingTest {

    @Test
    public void booking() {
        RoomService.addNewRoom("1001");
        RoomService.addNewRoom("1002");
        RoomService.listAllRooms();

        Guest guest = new Guest();
        guest.setName("Wavin");
        guest.setDate(System.currentTimeMillis());
        guest.setRoomNumber("1001");

        try {
            RoomService.doBooking(guest);

            guest.setRoomNumber("1004");
            RoomService.doBooking(guest);
        } catch (RoomAlreadyBookedException e) {
            // TODO something for room booked
            e.printStackTrace();
        } catch (NoSuchRoomException e) {
            // TODO something for no such room
            e.printStackTrace();
        }

        RoomService.listAllRooms();
    }
}
