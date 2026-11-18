package com.xyz.port.repository;

import com.xyz.port.entity.Port;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port, Long> {
}