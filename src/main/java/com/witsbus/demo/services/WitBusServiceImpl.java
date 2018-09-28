package com.witsbus.demo.services;


import com.witsbus.demo.dto.BookingDTO;
import com.witsbus.demo.dto.SeatDTO;
import com.witsbus.demo.dto.UserDTO;
import com.witsbus.demo.models.Booking;
import com.witsbus.demo.models.Seat;
import com.witsbus.demo.models.User;
import com.witsbus.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public User addUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        return userRepository.save(user);

    }
    @Override
    public void booking(BookingDTO bookingDTO, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(bookingDTO.getId());
        User user ;
        if (userOptional.isPresent()){
            user = userOptional.get();
        }
        else {
            User newUser = new User();
            newUser.setId(userDTO.getId());
            newUser.setName(userDTO.getName());
            newUser.setEmail(userDTO.getEmail());
            newUser.setPhone(userDTO.getPhone());
            user = userRepository.save(newUser);
        }
        Optional<Seat> seatOptional = seatRepository.findById(bookingDTO.getSeat().getId());
        Seat seat;
        if (seatOptional.isPresent()){
            Seat editSeat = seatOptional.get();
            seat =seatRepository.save(editSeat);
        }else {

            seat = null;
        }
        Booking booking = new Booking();
        booking.setPay(bookingDTO.getPay());
        booking.setPrice(bookingDTO.getPrice());
        booking.setTickets(bookingDTO.getTickets());
        booking.setUser(user);
        booking.setSeat(seat);
        Booking saveBooking = bookingRepository.save(booking);
    }

    @Override
    public List<BookingDTO> getUser() {
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setPay(booking.getPay());
            bookingDTO.setPrice(booking.getPrice());
            bookingDTO.setTickets(booking.getTickets());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(booking.getUser().getId());
            userDTO.setName(booking.getUser().getName());
            userDTO.setEmail(booking.getUser().getEmail());
            userDTO.setPhone(booking.getUser().getPhone());

            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setName(booking.getSeat().getName());
            seatDTO.setStatus(booking.getSeat().getStatus());
            seatDTO.setPrice(booking.getSeat().getPrice());

            bookingDTO.setUser(userDTO);
            bookingDTO.setSeat(seatDTO);

            bookingDTOS.add(bookingDTO);
        }

        return bookingDTOS;
    }

}
