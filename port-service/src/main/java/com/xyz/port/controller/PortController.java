package com.xyz.port.controller;

import com.xyz.port.dto.PortRequest;
import com.xyz.port.dto.PortResponse;
import com.xyz.port.service.PortService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ports")
public class PortController {

    private final PortService service;

    public PortController(PortService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PortResponse create(@Valid @RequestBody PortRequest request) {
        return PortResponse.from(service.create(request));
    }

    @GetMapping
    public List<PortResponse> findAll() {
        return service.findAll().stream().map(PortResponse::from).toList();
    }

    @GetMapping("/{id}")
    public PortResponse findById(@PathVariable Long id) {
        return PortResponse.from(service.findById(id));
    }

    @PutMapping("/{id}")
    public PortResponse update(@PathVariable Long id, @Valid @RequestBody PortRequest request) {
        return PortResponse.from(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}