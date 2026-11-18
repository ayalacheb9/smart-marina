package com.xyz.pedestal.repository;

import com.xyz.pedestal.entity.Pedestal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedestalRepository extends JpaRepository<Pedestal, Long> {
    Optional<Pedestal> findBySerialNumber(String serialNumber);
    boolean existsBySerialNumber(String serialNumber);
}