package com.devsam.Room_service.Repository;

import com.devsam.Room_service.Enitity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
