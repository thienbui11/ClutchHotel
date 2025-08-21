package com.clutchhotel.HotelClutch.repo;

import com.clutchhotel.HotelClutch.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingConfirmationCode(String comfirmationCode);
}
