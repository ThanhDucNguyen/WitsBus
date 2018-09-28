package com.witbus.demo.services;

import com.witbus.demo.dao.models.*;
import com.witbus.demo.dao.repository.*;
import com.witbus.demo.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WitBusServiceImpl implements WitBusService {
    @Autowired
    private BusRepository busRepository;
    private BusOwnerRepository bus_ownerRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;
    private BookingRepository bookingRepository;

    public WitBusServiceImpl(BusOwnerRepository bus_ownerRepository, BusRepository busRepository, SeatRepository seatRepository, UserRepository userRepository, BookingRepository bookingRepository ){
        this.busRepository = busRepository;
        this.bus_ownerRepository = bus_ownerRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    //========================================================== BOOKING ====================================================//

    //=======================================================================================================================================//

    @Override
    public void booking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setPay(bookingDTO.getPay());
        booking.setPrice(bookingDTO.getPrice());
        booking.setTickets(bookingDTO.getTickets());
        Optional<User> userOptional = userRepository.findById(bookingDTO.getUser().getId());
        User user ;
        if (!userOptional.isPresent()){
            User newUser = new User();
            newUser.setId(bookingDTO.getUser().getId());
            newUser.setName(bookingDTO.getUser().getName());
            newUser.setPhone(bookingDTO.getUser().getPhone());
            newUser.setEmail(bookingDTO.getUser().getEmail());
            user = userRepository.save(newUser);
        }
        else {
            user = userOptional.get();
        }
        Optional<Seat> seatOptional = seatRepository.findById(bookingDTO.getSeat().getId());
        Seat seat;
        if (seatOptional.isPresent()){
            Seat editSeat = seatOptional.get();
            editSeat.setStatus(bookingDTO.getSeat().getStatus());
            seat =seatRepository.save(editSeat);
        }else {
            return;
        }
        booking.setUser(user);
        booking.setSeat(seat);
        Booking saveBooking = bookingRepository.save(booking);
    }

}
