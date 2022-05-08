package com.dar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServiceTypeRepository extends CrudRepository<ServiceTypeEntity, Long> {
    ServiceTypeEntity getServiceTypeEntityByCode(String paymentTypeCode);

    List<ServiceTypeEntity> getServiceTypeEntitiesBy();
}