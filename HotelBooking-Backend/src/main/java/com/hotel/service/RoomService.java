package com.hotel.service;

import com.hotel.dto.RoomDTO;
import com.hotel.dto.RoomSearchDTO;
import com.hotel.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomService {
    RoomDTO createRoom(RoomDTO roomDTO);
    RoomDTO updateRoom(Long id, RoomDTO roomDTO);
    void deleteRoom(Long id);
    RoomDTO getRoomById(Long id);
    List<RoomDTO> getAllRooms();
    List<RoomDTO> searchRooms(RoomSearchDTO searchDTO);
    Page<RoomDTO> getRoomsWithPagination(int page, int size);
    boolean isRoomAvailable(Long roomId, Long checkInDate, Long checkOutDate);
    void updateRoomStatus(Long roomId, String status);
}
