package com.ab.service;

import com.ab.model.Salon;
import com.ab.payload.dto.SalonDTO;
import com.ab.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {

    Salon createSalon(SalonDTO salon, UserDTO user);
    Salon updateSalon(SalonDTO salon, UserDTO user,Long salonId);

    List<Salon> getAllSalons();
    Salon getSalonById(Long salonId);

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> searchSalonByCity(String city);


}
