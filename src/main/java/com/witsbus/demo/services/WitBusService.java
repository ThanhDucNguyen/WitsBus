package com.witsbus.demo.services;


import com.witsbus.demo.dto.BookingDTO;
import com.witsbus.demo.dto.UserDTO;
import com.witsbus.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WitBusService {

    void booking(BookingDTO bookingDTO, UserDTO userDTO);

    User addUser(UserDTO userDTO);


    List<BookingDTO> getUser();
}
