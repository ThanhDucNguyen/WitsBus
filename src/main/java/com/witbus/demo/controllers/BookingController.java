package com.witbus.demo.controllers;

import com.witbus.demo.dto.Response;

import com.witbus.demo.dto.BookingDTO;

import com.witbus.demo.services.WitBusService;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookingController {
    private WitBusService witBusService;

    public BookingController(WitBusService witBusService) {
        this.witBusService = witBusService;
    }

    @PostMapping(value = "/booking")
    public @ResponseBody
    Response addBooking(@RequestBody BookingDTO bookingDTO){
        witBusService.booking(bookingDTO);
        Response<BookingDTO> response = new Response<>(true,bookingDTO,"Successful!");
        response.getData();
        return response;
    }
}
