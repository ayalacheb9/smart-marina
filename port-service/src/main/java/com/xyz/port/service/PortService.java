package com.xyz.port.service;

import com.xyz.port.dto.PortRequest;
import com.xyz.port.entity.Port;
import com.xyz.port.exception.PortNotFoundException;
import com.xyz.port.repository.PortRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PortService {

    private final PortRepository repository;

    public PortService(PortRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Port> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Port findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PortNotFoundException(id));
    }

    public Port create(PortRequest r) {
        Port p = new Port();
        copy(r, p);
        return repository.save(p);
    }

    public Port update(Long id, PortRequest r) {
        Port p = findById(id);
        copy(r, p);
        return repository.save(p);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    private void copy(PortRequest r, Port p) {
        p.setName(r.name());
        p.setCity(r.city());
        p.setCapacity(r.capacity());
    }
}