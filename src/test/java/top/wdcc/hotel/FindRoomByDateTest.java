package top.wdcc.hotel;

import org.junit.Before;
import org.junit.Test;
import top.wdcc.hotel.dto.Guest;
import top.wdcc.hotel.exception.NoSuchRoomException;
import top.wdcc.hotel.exception.RoomAlreadyBookedException;
import top.wdcc.hotel.service.RoomService;

import java.util.Arrays;

/**
 * find room by date test case
 * @author wavin
 * @date 2022/4/8
 */
public class FindRoomByDateTest {

    @Before
    public void addRoom() {
        System.out.println("Add room");
        for (int i = 0 ; i < 1000; i ++) {
            RoomService.addNewRoom(String.format("%1$4s", String.valueOf(i + 1)).replace(" ", "0"));
        }
        RoomService.listAllRooms();
        System.out.println("Add room done.");
    }

    @Test
    public void findRoom() throws RoomAlreadyBookedException, NoSuchRoomException {
        long l = System.currentTimeMillis();
        Guest guest = new Guest();
        guest.setName("Wavin");
        guest.setDate(l);

        for (int i = 0 ; i < 999; i ++) {
            guest.setRoomNumber(String.format("%1$4s", String.valueOf(i + 1)).replace(" ", "0"));
            RoomService.doBooking(guest);
        }

        System.out.println(Arrays.toString(RoomService.findAvailableRooms(System.currentTimeMillis()).toArray()));

        System.out.println(Arrays.toString(RoomService.findAvailableRooms(l).toArray()));
    }
}
