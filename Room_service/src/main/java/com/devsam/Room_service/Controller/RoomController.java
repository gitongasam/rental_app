package com.devsam.Room_service.Controller;

import com.devsam.Room_service.Enitity.Room;
import com.devsam.Room_service.Repository.RoomRepository;
import com.devsam.Room_service.Service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @PutMapping("/{roomId}/payment-status")
    public ResponseEntity<Void> updateRoomPaymentStatus(@PathVariable Long roomId, @RequestParam String paymentStatus) {
        roomService.updateRoomPaymentStatus(roomId, paymentStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Geting the room rent amount
   @GetMapping("/{roomId}/rent-amount")
    public BigDecimal getRentAmount(@PathVariable Long roomId) {
        // Logic to fetch rent amount for the specified room ID
        // This could be from your database or any other source
        BigDecimal rentAmount = roomService.getRentAmountByRoomId(roomId);
        return rentAmount;
    }

//    Getting the balance of the room
    @PutMapping("/{roomId}/update-balance")
    public ResponseEntity<Room> updateBalance(@PathVariable Long roomId, @RequestParam BigDecimal balance) {
        Room updatedRoom = roomService.updateRoomBalance(roomId, balance);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }
}
