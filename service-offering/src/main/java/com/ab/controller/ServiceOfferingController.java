package com.ab.controller;

import com.ab.model.ServiceOffering;
import com.ab.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServiceOfferingsBySalonId
            (@PathVariable Long salonId, @RequestParam(required = false) Long categoryId ){
        return ResponseEntity.ok(serviceOfferingService.getAllServiceBySalonId(salonId,categoryId));
    }

      @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceById
            (@PathVariable Long id ){
        return ResponseEntity.ok(serviceOfferingService.getServiceById(id));
    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds
            (@PathVariable Set<Long> ids){
        return ResponseEntity.ok(serviceOfferingService.getServicesByIds(ids));
    }





}
