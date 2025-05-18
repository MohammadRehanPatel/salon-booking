package com.ab.controller;

import com.ab.mapper.SalonMapper;
import com.ab.model.Salon;
import com.ab.payload.dto.SalonDTO;
import com.ab.payload.dto.UserDTO;
import com.ab.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/salons")
public class SalonController {

    private final SalonService salonService;


    @PostMapping()
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("");
        userDTO.setFullName("");
        Salon salon = salonService.createSalon(salonDTO, userDTO);
        SalonDTO DTO = SalonMapper.mapToDTO(salon);

        return new ResponseEntity<>(DTO, HttpStatus.CREATED);
    }
    @PatchMapping("/{salonId}")
    public ResponseEntity<SalonDTO> updateSalon(@RequestBody SalonDTO salonDTO ,@PathVariable Long salonId){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("");
        userDTO.setFullName("");
        Salon salon = salonService.updateSalon(salonDTO, userDTO,salonId);
        SalonDTO DTO = SalonMapper.mapToDTO(salon);

        return new ResponseEntity<>(DTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<SalonDTO>> getSalons(){
        List<Salon> salons = salonService.getAllSalons();
        List<SalonDTO> salonDTOList = salons.stream().map(SalonMapper::mapToDTO).toList();
        return new ResponseEntity<>(salonDTOList, HttpStatus.OK);
    }
    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long salonId){
        Salon salon = salonService.getSalonById(salonId);
        SalonDTO DTO = SalonMapper.mapToDTO(salon);
        return new ResponseEntity<>(DTO, HttpStatus.OK);
    }
    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon salon = salonService.getSalonByOwnerId(userDTO.getId());
        SalonDTO DTO = SalonMapper.mapToDTO(salon);
        return new ResponseEntity<>(DTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalon(@RequestParam String city){
        List<Salon> salons = salonService.searchSalonByCity(city);
        List<SalonDTO> salonDTOList = salons.stream().map(SalonMapper::mapToDTO).toList();
        return new ResponseEntity<>(salonDTOList, HttpStatus.OK);
    }






}
