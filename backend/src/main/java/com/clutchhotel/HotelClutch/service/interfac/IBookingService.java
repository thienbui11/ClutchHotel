package com.clutchhotel.HotelClutch.service.interfac;

import com.clutchhotel.HotelClutch.dto.Response;
import com.clutchhotel.HotelClutch.entity.Booking;
import com.clutchhotel.HotelClutch.entity.Room;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);
}
