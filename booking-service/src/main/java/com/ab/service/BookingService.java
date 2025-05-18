package com.ab.service;

import com.ab.domain.BookingStatus;
import com.ab.model.Booking;
import com.ab.model.SalonReport;
import com.ab.payload.dto.BookingRequest;
import com.ab.payload.dto.SalonDTO;
import com.ab.payload.dto.ServiceDTO;
import com.ab.payload.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest booking, UserDTO userDTO, SalonDTO salonDTO, Set<ServiceDTO> serviceDTOS);

    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);
    Booking getBookingById(Long id);
    Booking updateBooking(Long bookingId, BookingStatus status);

    List<Booking> getBookingsByDate(LocalDate date,Long salonId);

    SalonReport getSalonReport(Long salonId);


}
