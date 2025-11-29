package com.hotel.service.impl;

import com.hotel.dto.RoomDTO;
import com.hotel.dto.RoomSearchDTO;
import com.hotel.entity.Room;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        if (roomRepository.findByRoomNumber(roomDTO.getRoomNumber()).isPresent()) {
            throw new RuntimeException("Room number already exists");
        }

        Room room = Room.builder()
                .roomNumber(roomDTO.getRoomNumber())
                .roomType(Room.RoomType.valueOf(roomDTO.getRoomType()))
                .capacity(roomDTO.getCapacity())
                .pricePerNight(roomDTO.getPricePerNight())
                .description(roomDTO.getDescription())
                .floor(roomDTO.getFloor())
                .status(Room.RoomStatus.AVAILABLE)
                .build();

        Room savedRoom = roomRepository.save(room);
        return convertToDTO(savedRoom);
    }

    @Override
    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (roomDTO.getRoomType() != null) {
            room.setRoomType(Room.RoomType.valueOf(roomDTO.getRoomType()));
        }
        if (roomDTO.getCapacity() != null) {
            room.setCapacity(roomDTO.getCapacity());
        }
        if (roomDTO.getPricePerNight() != null) {
            room.setPricePerNight(roomDTO.getPricePerNight());
        }
        if (roomDTO.getDescription() != null) {
            room.setDescription(roomDTO.getDescription());
        }
        if (roomDTO.getFloor() != null) {
            room.setFloor(roomDTO.getFloor());
        }

        Room updatedRoom = roomRepository.save(room);
        return convertToDTO(updatedRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        roomRepository.delete(room);
    }

    @Override
    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        return convertToDTO(room);
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> searchRooms(RoomSearchDTO searchDTO) {
        List<Room> rooms;

        if (searchDTO.getRoomType() != null && searchDTO.getMinPrice() != null && searchDTO.getMaxPrice() != null) {
            rooms = roomRepository.searchAvailableRooms(
                    searchDTO.getRoomType(),
                    searchDTO.getMinPrice(),
                    searchDTO.getMaxPrice()
            );
        } else if (searchDTO.getCapacity() != null) {
            rooms = roomRepository.findAvailableRoomsByCapacity(searchDTO.getCapacity());
        } else {
            rooms = roomRepository.findAll();
        }

        return rooms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<RoomDTO> getRoomsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        List<RoomDTO> content = roomPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageable, roomPage.getTotalElements());
    }

    @Override
    public boolean isRoomAvailable(Long roomId, Long checkInDate, Long checkOutDate) {
        List<com.hotel.entity.Booking> conflicts = bookingRepository.findConflictingBookings(
                roomId, checkInDate, checkOutDate
        );
        return conflicts.isEmpty();
    }

    @Override
    public void updateRoomStatus(Long roomId, String status) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        room.setStatus(Room.RoomStatus.valueOf(status));
        roomRepository.save(room);
    }

    private RoomDTO convertToDTO(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .roomType(room.getRoomType().toString())
                .capacity(room.getCapacity())
                .pricePerNight(room.getPricePerNight())
                .status(room.getStatus().toString())
                .description(room.getDescription())
                .floor(room.getFloor())
                .build();
    }
}
