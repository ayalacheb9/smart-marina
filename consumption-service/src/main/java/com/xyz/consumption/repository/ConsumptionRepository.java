package com.xyz.consumption.repository;

import com.xyz.consumption.entity.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
    boolean existsByMeasurementId(String measurementId);
    List<Consumption> findByPedestalIdOrderByMeasuredAtDesc(Long pedestalId);
}