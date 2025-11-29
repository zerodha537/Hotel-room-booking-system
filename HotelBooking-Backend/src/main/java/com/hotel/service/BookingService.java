package com.hotel.service;

import com.hotel.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO, Long userId);
    BookingDTO updateBooking(Long id, BookingDTO bookingDTO);
    void cancelBooking(Long id);
    BookingDTO getBookingById(Long id);
    List<BookingDTO> getUserBookings(Long userId);
    List<BookingDTO> getAllBookings();
    boolean validateBookingDates(BookingDTO bookingDTO);
    boolean checkRoomAvailability(Long roomId, Long checkInDate, Long checkOutDate);
}
