package com.devsam.Room_service.Controller;

import com.devsam.Room_service.Enitity.Room;
import com.devsam.Room_service.Repository.RoomRepository;
import com.devsam.Room_service.Service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/rooms")
@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room newRoom = roomService.addRoom(room);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @DeleteMapping("/{roomID}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomID) {
        roomService.deleteRoom(roomID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{roomID}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long roomID, @RequestBody Room room) throws Throwable {
        Room updatedRoom = roomService.updateRoom(roomID, room);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @GetMapping("/{roomID}")
    public ResponseEntity<Room> getRoom(@PathVariable Long roomID) throws Throwable {
        Room room = roomService.getRoom(roomID);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
