package com.hotel.repository;

import com.hotel.entity.Booking;
import com.hotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);

    List<Booking> findByRoomId(Long roomId);

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND " +
           "((b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate) OR " +
           "(b.checkInDate = :checkInDate AND b.checkOutDate = :checkOutDate)) AND " +
           "b.status IN ('PENDING', 'CONFIRMED', 'CHECKED_IN')")
    List<Booking> findConflictingBookings(
            @Param("roomId") Long roomId,
            @Param("checkInDate") Long checkInDate,
            @Param("checkOutDate") Long checkOutDate
    );

    List<Booking> findByStatus(String status);

    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId ORDER BY b.createdAt DESC")
    List<Booking> findUserBookingsWithDetails(@Param("userId") Long userId);
}
