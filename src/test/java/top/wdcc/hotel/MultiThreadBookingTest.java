package top.wdcc.hotel;

import org.junit.Before;
import org.junit.Test;
import top.wdcc.hotel.dto.Guest;
import top.wdcc.hotel.exception.NoSuchRoomException;
import top.wdcc.hotel.exception.RoomAlreadyBookedException;
import top.wdcc.hotel.service.RoomService;

import java.util.concurrent.*;

/**
 * a booking test case for multithread
 * @author wavin
 * @date 2022/4/8
 */
public class MultiThreadBookingTest {
    private static final int COUNT = 1000;
    private static final ExecutorService SERVICE = Executors.newCachedThreadPool();

    @Before
    public void addRoom() {
        System.out.println("Add room");
        for (int i = 0 ; i < COUNT; i ++) {
            RoomService.addNewRoom(String.format("%1$4s", String.valueOf(i)).replace(" ", "0"));
        }
        RoomService.listAllRooms();
        System.out.println("Add room done.");
    }

    @Test
    public void booking() {
        System.out.println("Ready to book");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(COUNT / 2);
        for (int i = 0 ; i < COUNT; i ++) {
            if (i > COUNT / 2) {
                break;
            }
            Guest guest = new Guest();
            guest.setRoomNumber(String.format("%1$4s", String.valueOf(i)).replace(" ", "0"));
            guest.setDate(System.currentTimeMillis());
            guest.setName("Guest" + i);
            SERVICE.submit(()-> {
                try {
                    RoomService.doBooking(guest);
                    cyclicBarrier.await();
                } catch (RoomAlreadyBookedException e) {
                    e.printStackTrace();
                } catch (NoSuchRoomException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        RoomService.listAllRooms();
        System.out.println("Booked");
    }
}
