package com.devsam.Room_service.Service;

import com.devsam.Room_service.Enitity.Room;
import com.devsam.Room_service.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public Room addRoom(Room room) {
        return (Room) roomRepository.save(room);
    }

    public void deleteRoom(Long roomID) {
        roomRepository.deleteById(roomID);
    }

    public Room updateRoom(Long roomID, Room room) throws Throwable {
        Room existingRoom = (Room) roomRepository.findById(roomID)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomID));

        // Update existing room with new data
        existingRoom.setTenantId(room.getTenantId());
        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setRentAmount(room.getRentAmount());
        existingRoom.setPaymentStatus(room.getPaymentStatus());

        return (Room) roomRepository.save(existingRoom);
    }

    public Room getRoom(Long roomID) throws Throwable {
        return (Room) roomRepository.findById(roomID)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomID));
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
