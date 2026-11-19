package com.xyz.consumption.service;

import com.xyz.consumption.client.PedestalClient;
import com.xyz.consumption.dto.ConsumptionRequest;
import com.xyz.consumption.entity.Consumption;
import com.xyz.consumption.exception.ConsumptionNotFoundException;
import com.xyz.consumption.exception.DuplicateMeasurementException;
import com.xyz.consumption.exception.PedestalNotFoundException;
import com.xyz.consumption.repository.ConsumptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ConsumptionService {

    private final ConsumptionRepository repository;
    private final PedestalClient pedestalClient;

    public ConsumptionService(ConsumptionRepository repository, PedestalClient pedestalClient) {
        this.repository = repository;
        this.pedestalClient = pedestalClient;
    }

    @Transactional(readOnly = true)
    public List<Consumption> findAll(Long pedestalId) {
        if (pedestalId != null) {
            return repository.findByPedestalIdOrderByMeasuredAtDesc(pedestalId);
        }
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Consumption findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ConsumptionNotFoundException(id));
    }

    public Consumption create(ConsumptionRequest r) {
        if (!pedestalClient.exists(r.pedestalId())) {
            throw new PedestalNotFoundException(r.pedestalId());
        }

        String measurementId = (r.measurementId() == null || r.measurementId().isBlank())
                ? UUID.randomUUID().toString()
                : r.measurementId();

        if (repository.existsByMeasurementId(measurementId)) {
            throw new DuplicateMeasurementException(measurementId);
        }

        Consumption c = new Consumption();
        c.setMeasurementId(measurementId);
        c.setPedestalId(r.pedestalId());
        c.setResource(r.resource());
        c.setQuantity(r.quantity());
        c.setUnit(r.resource().getUnit());
        c.setMeasuredAt(r.measuredAt());
        c.setReceivedAt(Instant.now());
        return repository.save(c);
    }
}