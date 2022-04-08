package top.wdcc.hotel;

import org.junit.Before;
import org.junit.Test;
import top.wdcc.hotel.dto.Guest;
import top.wdcc.hotel.exception.NoSuchRoomException;
import top.wdcc.hotel.exception.RoomAlreadyBookedException;
import top.wdcc.hotel.service.RoomService;

import java.util.Arrays;

/**
 * @author wavin
 * @date 2022/4/8
 */
public class FindGuestBookings {

    @Before
    public void addRoom() {
        System.out.println("Add room");
        for (int i = 0 ; i < 1000; i ++) {
            RoomService.addNewRoom(String.format("%1$4s", String.valueOf(i+1)).replace(" ", "0"));
        }
        RoomService.listAllRooms();
        System.out.println("Add room done.");
    }

    @Test
    public void findBookings() {
        Guest guest = new Guest();
        guest.setName("Wavin");
        guest.setDate(System.currentTimeMillis());
        guest.setRoomNumber("0101");

        try {
            RoomService.doBooking(guest);
            guest.setRoomNumber("0102");
            RoomService.doBooking(guest);
        } catch (RoomAlreadyBookedException e) {
            // TODO something for room booked
            e.printStackTrace();
        } catch (NoSuchRoomException e) {
            // TODO something for no such room
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(RoomService.findGuestBookings(guest.getName()).toArray()));
    }
}
