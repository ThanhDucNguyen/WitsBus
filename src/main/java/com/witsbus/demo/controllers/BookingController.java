package com.witsbus.demo.controllers;

import com.witsbus.demo.dto.BookingDTO;
import com.witsbus.demo.dto.Response;
import com.witsbus.demo.dto.UserDTO;
import com.witsbus.demo.models.User;
import com.witsbus.demo.services.WitBusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {
    private WitBusService witBusService;

    public BookingController(WitBusService witBusService) {
        this.witBusService = witBusService;
    }

    @PostMapping(value = "/booking")
    public @ResponseBody
    Response addBooking(@RequestBody BookingDTO bookingDTO, UserDTO userDTO){
        //witBusService.addUser(userDTO);
        witBusService.booking(bookingDTO,userDTO);
        Response<BookingDTO> response = new Response<>(true,bookingDTO,"Successful!");
        response.getData();
        return response;
    }
    @PostMapping(value = "/user")
    public @ResponseBody
    User addUser(@RequestBody UserDTO userDTO){
        return witBusService.addUser(userDTO);
    }
    @GetMapping(value = "/user")
    public @ResponseBody
    List<BookingDTO> setUser(){
        return witBusService.getUser();
    }
}
