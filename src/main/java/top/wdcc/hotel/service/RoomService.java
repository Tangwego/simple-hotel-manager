package top.wdcc.hotel.service;

import top.wdcc.hotel.dto.Guest;
import top.wdcc.hotel.exception.NoSuchRoomException;
import top.wdcc.hotel.exception.RoomAlreadyBookedException;

import java.util.*;

/**
 * Service layer for room manager
 * @author wavin
 * @date 2022/4/8
 */
public class RoomService {

    /**
     * a memory space to save all room info
     */
    private static final Map<String, Guest> rooms = new HashMap<>();

    /**
     * cannot instance this service class
     */
    private RoomService() {

    }

    /**
     * add an new room to map
     * @param roomNumber
     */
    public synchronized static void addNewRoom(String roomNumber) {
        if (roomNumber != null && !roomNumber.equals("")) {
            rooms.put(roomNumber, null);
        }
    }

    /**
     * add a guest and room booking relation
     * @param guest
     * @throws RoomAlreadyBookedException
     */
    public synchronized static void doBooking(Guest guest) throws RoomAlreadyBookedException, NoSuchRoomException {
        if (guest == null || guest.getRoomNumber() == null || guest.getRoomNumber().equals("")) {
            throw new RuntimeException("Cannot booking unknown guest or room!");
        }
        // if map contains key (room number), it means room exists.
        if (rooms.containsKey(guest.getRoomNumber())) {
            if (rooms.get(guest.getRoomNumber()) != null) {
                throw new RoomAlreadyBookedException(guest.getRoomNumber());
            }
            rooms.put(guest.getRoomNumber(), guest);
        } else {
            throw new NoSuchRoomException(guest.getRoomNumber());
        }
    }

    /**
     * find available rooms as a list
     * @param date
     * @return list
     */
    public synchronized static List<String> findAvailableRooms(long date) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Guest> entry: rooms.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getDate() == date) {
                    continue;
                }
            }
            list.add(entry.getKey());
        }
        return list;
    }

    /**
     * find guest bookings
     * @param guestName
     * @return list
     */
    public synchronized static List<String> findGuestBookings(String guestName) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Guest> entry: rooms.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getName().equals(guestName)) {
                    list.add(entry.getKey());
                }
            }
        }
        return list;
    }

    public synchronized static void listAllRooms() {
        System.out.println(String.format("current %d rooms, print all rooms status:", rooms.size()));
        for (Map.Entry<String, Guest> entry: rooms.entrySet()) {
            System.out.println(String.format("%s %s",
                    entry.getKey(),
                    (entry.getValue() != null ? entry.getValue().getName() +" InUse" : "NotInUse")));
        }
        System.out.println();
    }
}
