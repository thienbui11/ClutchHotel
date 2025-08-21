package com.clutchhotel.HotelClutch.service.impl;

import com.clutchhotel.HotelClutch.dto.LoginRequest;
import com.clutchhotel.HotelClutch.dto.Response;
import com.clutchhotel.HotelClutch.dto.UserDTO;
import com.clutchhotel.HotelClutch.entity.User;
import com.clutchhotel.HotelClutch.exception.OurException;
import com.clutchhotel.HotelClutch.repo.UserRepository;
import com.clutchhotel.HotelClutch.service.interfac.IUserService;
import com.clutchhotel.HotelClutch.utils.JWTUtils;
import com.clutchhotel.HotelClutch.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Response register(User user){
        Response response = new Response();

        try {
            if(user.getRole() == null || user.getRole().isBlank()){
                user.setRole("USER");
            }

            if(userRepository.existsByEmail(user.getEmail())){
                throw new OurException(user.getEmail() + " " + "Already Exists");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(savedUser);

            response.setStatusCode(200);
            response.setUser(userDTO);

        }catch (OurException e){
            response.setStatusCode(400);
            response.setMessage(e.getMessage());

        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Saving User" + e.getMessage());
        }

        return response;
    }

    @Override
    public Response login(LoginRequest loginRequest){
        Response response = new Response();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new OurException("user Not found"));
            var token = jwtUtils.generateToken(user);
            response.setToken(token);
            response.setExpirationTime("7 days");
            response.setRole(user.getRole());
            response.setMessage("Logged in Successfully");

            response.setStatusCode(200);

        }catch (OurException e){
            response.setStatusCode(400);
            response.setMessage(e.getMessage());

        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Saving User" + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getAllUsers() {
        Response response = new Response();

        try {
            List<User> users = userRepository.findAll();
            List<UserDTO> userDTOList = Utils.mapUserListEntityToUserListDTO(users);
            response.setUserList(userDTOList);
            response.setMessage("Successfully");

            response.setStatusCode(200);


        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Get All Users" + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getUserBookingHistory(String userId) {
        Response response = new Response();

        try {
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("user Not found"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTOPlusUserBookingsAndRoom(user);

            response.setMessage("Successfully");

            response.setStatusCode(200);
            response.setUser(userDTO);

        }catch (OurException e){
            response.setStatusCode(400);
            response.setMessage(e.getMessage());

        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Get User Booking History" + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getUserById(String userId) {
        Response response = new Response();

        try {
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("user Not found"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);

            response.setMessage("Successfully");

            response.setStatusCode(200);
            response.setUser(userDTO);

        }catch (OurException e){
            response.setStatusCode(400);
            response.setMessage(e.getMessage());

        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Get User Booking History" + e.getMessage());
        }

        return response;
    }

    @Override
    public Response deleteUser(String userId) {
        Response response = new Response();

        try {
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("user Not found"));
            userRepository.deleteById(Long.valueOf(userId));
            response.setMessage("Successfully");

            response.setStatusCode(200);
        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error delete user" + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getMyInfo(String email) {
        Response response = new Response();

        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new OurException("user Not found"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);
            response.setMessage("Successfully");

            response.setStatusCode(200);
            response.setUser(userDTO);
        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error delete user" + e.getMessage());
        }

        return response;
    }

}
