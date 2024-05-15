package com.devsam.Room_service.Service;

import com.devsam.Room_service.Enitity.Room;

import java.util.List;

public interface RoomService {
    Room addRoom(Room room);

    void deleteRoom(Long roomID);

    Room updateRoom(Long roomID, Room room) throws Throwable;

    Room getRoom(Long roomID) throws Throwable;

    List<Room> getAllRooms();
}
