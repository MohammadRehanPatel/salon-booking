package com.ab.controller;

import com.ab.domain.BookingStatus;
import com.ab.mapper.BookingMapper;
import com.ab.model.Booking;
import com.ab.model.SalonReport;
import com.ab.payload.dto.*;
import com.ab.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest, @RequestParam Long salonId) {

        UserDTO user = new UserDTO();
        user.setId(1L);

        SalonDTO salon = new SalonDTO();
        salon.setId(salonId);
        salon.setOpenTime(LocalTime.now().minusHours(2));
        salon.setCloseTime(LocalTime.now().plusHours(12));
        Set<ServiceDTO> serviceDTOSet = new HashSet<>();
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(399);
        serviceDTO.setDuration(45);
        serviceDTO.setName("Hair cut for men");

        serviceDTOSet.add(serviceDTO);

        Booking booking = bookingService.createBooking(bookingRequest, user, salon, serviceDTOSet);


        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>>getBookingsByCustomer(){
        UserDTO user =new UserDTO();
        user.setId(1L);

        List<Booking> bookings= bookingService.getBookingsByCustomer(1L);

        return ResponseEntity.ok(getBookingsDTOs(bookings));

    }
    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>>getBookingsBySalon(){
        UserDTO user =new UserDTO();
        user.setId(1L);

        List<Booking> bookings= bookingService.getBookingsBySalon(1L);

        return ResponseEntity.ok(getBookingsDTOs(bookings));

    }


    private Set<BookingDTO> getBookingsDTOs(List<Booking> bookings){
        return bookings.stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO>getBookingsById(@PathVariable Long bookingId){

        Booking booking= bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long bookingId, @RequestParam BookingStatus status){

        Booking booking= bookingService.updateBooking(bookingId,status);

        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }
     @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookedSlot(@PathVariable Long salonId,
                                                              @PathVariable(required = false) LocalDate date){

        List<Booking> bookings= bookingService.getBookingsByDate(date,salonId);
    List<BookingSlotDTO> slotDTOs = bookings.stream()
            .map(booking->{
                BookingSlotDTO slotDTO = new BookingSlotDTO();
                slotDTO.setStartTime(booking.getStartTime());
                slotDTO.setEndTime(booking.getEndTime());
                return slotDTO;
            })        .collect(Collectors.toList());

        return ResponseEntity.ok(slotDTOs);
    }


    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport(){

        SalonReport salonReport = bookingService.getSalonReport(1L);


        return ResponseEntity.ok(salonReport);
    }




}
