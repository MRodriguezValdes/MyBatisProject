package org.mybatis;

import java.util.List;

public interface BookingMapper {
    void insertBooking(Booking booking);
    void updateBooking(Booking booking);
    void deleteBookingById(int id);
    void deleteAllBookings();
    List<Booking> getAllBookings();
    Booking getBookingById(int bookingIdToModify);

    int getLastLocationNumber();
}