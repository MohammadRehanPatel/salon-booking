package com.ab.service;

import com.ab.model.ServiceOffering;
import com.ab.payload.dto.CategoryDTO;
import com.ab.payload.dto.SalonDTO;
import com.ab.payload.dto.ServiceDTO;

import java.util.List;
import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);

    ServiceOffering updateService(Long serviceId,ServiceOffering serviceOffering);

    Set<ServiceOffering> getAllServiceBySalonId(Long salonId,Long categoryId);

    Set<ServiceOffering> getServicesByIds(Set<Long> ids);

    ServiceOffering getServiceById(Long id);

}
