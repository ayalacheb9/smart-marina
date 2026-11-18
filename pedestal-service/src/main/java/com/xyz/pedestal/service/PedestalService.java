package com.xyz.pedestal.service;

import com.xyz.pedestal.client.PortClient;
import com.xyz.pedestal.dto.PedestalRequest;
import com.xyz.pedestal.entity.Pedestal;
import com.xyz.pedestal.exception.DuplicateSerialNumberException;
import com.xyz.pedestal.exception.PedestalNotFoundException;
import com.xyz.pedestal.exception.PortNotFoundException;
import com.xyz.pedestal.repository.PedestalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PedestalService {

    private final PedestalRepository repository;
    private final PortClient portClient;

    public PedestalService(PedestalRepository repository, PortClient portClient) {
        this.repository = repository;
        this.portClient = portClient;
    }

    @Transactional(readOnly = true)
    public List<Pedestal> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Pedestal findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PedestalNotFoundException(id));
    }

    public Pedestal create(PedestalRequest r) {
        checkPort(r.portId());
        if (repository.existsBySerialNumber(r.serialNumber())) {
            throw new DuplicateSerialNumberException();
        }
        Pedestal p = new Pedestal();
        copy(r, p);
        return repository.save(p);
    }

    public Pedestal update(Long id, PedestalRequest r) {
        Pedestal p = findById(id);
        checkPort(r.portId());
        repository.findBySerialNumber(r.serialNumber())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> { throw new DuplicateSerialNumberException(); });
        copy(r, p);
        return repository.save(p);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    private void checkPort(Long portId) {
        if (!portClient.exists(portId)) {
            throw new PortNotFoundException(portId);
        }
    }

    private void copy(PedestalRequest r, Pedestal p) {
        p.setSerialNumber(r.serialNumber());
        p.setName(r.name());
        p.setPortId(r.portId());
        p.setPontoon(r.pontoon());
        p.setStatus(r.status());
    }
}