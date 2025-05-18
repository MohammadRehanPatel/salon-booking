package com.ab.service.impl;

import com.ab.exception.ServiceOfferingException;
import com.ab.model.ServiceOffering;
import com.ab.payload.dto.CategoryDTO;
import com.ab.payload.dto.SalonDTO;
import com.ab.payload.dto.ServiceDTO;
import com.ab.repository.ServiceOfferingRepository;
import com.ab.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO) {

        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setImage(serviceDTO.getImage());
        serviceOffering.setName(serviceDTO.getName());
        serviceOffering.setPrice(serviceDTO.getPrice());
        serviceOffering.setDescription(serviceDTO.getDescription());
        serviceOffering.setDuration(serviceDTO.getDuration());
        serviceOffering.setCategoryId(categoryDTO.getId());
        serviceOffering.setSalonId(salonDTO.getId());


        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) {

        ServiceOffering serviceOffering = serviceOfferingRepository.findById(serviceId).orElse(null);
        if(serviceOffering==null){
            throw  new ServiceOfferingException("Service Offering not exist with id "+serviceId);
        }
        serviceOffering.setImage(service.getImage());
        serviceOffering.setName(service.getName());
        serviceOffering.setPrice(service.getPrice());
        serviceOffering.setDescription(service.getDescription());
        serviceOffering.setDuration(service.getDuration());



        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {
        Set<ServiceOffering> services = serviceOfferingRepository.findBySalonId(salonId);

        if(categoryId!=null){
            services= services.stream().filter((service)->service.getCategoryId() !=null && service.getCategoryId()==categoryId)
                    .collect(Collectors.toSet());
        }

        return services;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {
        List<ServiceOffering> services = serviceOfferingRepository.findAllById(ids);

        return new HashSet<>(services);

    }

    @Override
    public ServiceOffering getServiceById(Long id) {
        ServiceOffering serviceOffering = serviceOfferingRepository.findById(id).orElse(null);
        if(serviceOffering==null){
            throw  new ServiceOfferingException("Service Offering not exist with id "+id);
        }
        return serviceOffering;
    }
}
