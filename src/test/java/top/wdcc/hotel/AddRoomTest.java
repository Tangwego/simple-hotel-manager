package top.wdcc.hotel;

import org.junit.Test;
import top.wdcc.hotel.service.RoomService;

/**
 * add room test case
 * @author wavin
 * @date 2022/4/8
 */
public class AddRoomTest {

    @Test
    public void addRoom(){
        RoomService.addNewRoom("1001");
        RoomService.addNewRoom("1002");
        RoomService.listAllRooms();

    }

}
