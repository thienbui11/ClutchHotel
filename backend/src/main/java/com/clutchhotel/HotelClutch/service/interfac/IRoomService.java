package com.clutchhotel.HotelClutch.service.interfac;


import com.clutchhotel.HotelClutch.dto.Response;
import com.clutchhotel.HotelClutch.dto.RoomDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IRoomService {

    Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description);

    List<String> getAllRoomTypes();

    Response getAllRooms();

    Response deleteRoom(Long roomId);

    Response updateRoom(Long roomId, String description, String roomType, BigDecimal roomPrice, MultipartFile photo);

    Response getRoomById(Long roomId);

    Response getAvailableRoomByDateAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

    Response getAvailableRooms();
}
