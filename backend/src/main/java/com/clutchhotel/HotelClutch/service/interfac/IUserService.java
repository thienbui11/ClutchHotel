package com.clutchhotel.HotelClutch.service.interfac;

import com.clutchhotel.HotelClutch.dto.LoginRequest;
import com.clutchhotel.HotelClutch.dto.Response;
import com.clutchhotel.HotelClutch.entity.User;

public interface IUserService {

    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response getUserById(String userId);

    Response deleteUser(String userId);

    Response getMyInfo(String email);
}
