package com.ab.service.impl;

import com.ab.exception.SalonException;
import com.ab.model.Salon;
import com.ab.payload.dto.SalonDTO;
import com.ab.payload.dto.UserDTO;
import com.ab.repository.SalonRepository;
import com.ab.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SalonServiceImpl  implements SalonService {

    private final SalonRepository salonRepository;


    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {

        Salon salon1 = new Salon();
        salon1.setOwnerId(user.getId());

        salon1.setAddress(req.getAddress());
        salon1.setCity(req.getCity());
        salon1.setOpenTime(req.getOpenTime());
        salon1.setCloseTime(req.getCloseTime());
        salon1.setEmail(req.getEmail());
        salon1.setName(req.getName());
        salon1.setPhoneNumber(req.getPhoneNumber());
        salon1.setImages(req.getImages());

        return salonRepository.save(salon1);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) {

        Salon existingSalon = salonRepository.findById(salonId).orElse(null);
        if(existingSalon!=null && salon.getOwnerId().equals(user.getId())){
        existingSalon.setCity(salon.getCity());
        existingSalon.setImages(salon.getImages());
        existingSalon.setEmail(salon.getEmail());
        existingSalon.setName(salon.getName());
        existingSalon.setAddress(salon.getAddress());
        existingSalon.setPhoneNumber(salon.getPhoneNumber());
        existingSalon.setOpenTime(salon.getOpenTime());
        existingSalon.setCloseTime(salon.getCloseTime());
        existingSalon.setOwnerId(user.getId());

        return salonRepository.save(existingSalon);
        }

        throw new SalonException("salon not exists");
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) {
        Optional<Salon> opt = salonRepository.findById(salonId);
        if(opt.isPresent()){
            return opt.get();
        }

        throw new SalonException("Salon not found");
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
}
