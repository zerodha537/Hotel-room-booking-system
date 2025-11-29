package com.hotel.repository;

import com.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumber(String roomNumber);

    List<Room> findByRoomType(String roomType);

    List<Room> findByStatusAndPricePerNightBetween(String status, Double minPrice, Double maxPrice);

    @Query("SELECT r FROM Room r WHERE r.status = 'AVAILABLE' AND r.roomType = :roomType AND r.pricePerNight BETWEEN :minPrice AND :maxPrice")
    List<Room> searchAvailableRooms(
            @Param("roomType") String roomType,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );

    @Query("SELECT r FROM Room r WHERE r.status = 'AVAILABLE' AND r.capacity >= :capacity")
    List<Room> findAvailableRoomsByCapacity(@Param("capacity") Integer capacity);
}
