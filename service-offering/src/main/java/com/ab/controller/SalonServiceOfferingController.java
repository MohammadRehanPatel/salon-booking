package com.ab.controller;


import com.ab.model.ServiceOffering;
import com.ab.payload.dto.CategoryDTO;
import com.ab.payload.dto.SalonDTO;
import com.ab.payload.dto.ServiceDTO;
import com.ab.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping()
    public ResponseEntity<ServiceOffering> createService
            (@RequestBody ServiceDTO serviceDTO ){
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategory());

        ServiceOffering service = serviceOfferingService.createService(salonDTO, serviceDTO, categoryDTO);

        return new ResponseEntity<>(service, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService
            (@PathVariable Long id,@RequestBody ServiceOffering serviceOffering ){
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);

        ServiceOffering service = serviceOfferingService.updateService(id,serviceOffering);

        return new ResponseEntity<>(service, HttpStatus.OK);
    }

}
