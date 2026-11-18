package com.xyz.pedestal.controller;

import com.xyz.pedestal.dto.PedestalRequest;
import com.xyz.pedestal.dto.PedestalResponse;
import com.xyz.pedestal.service.PedestalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedestals")
public class PedestalController {

    private final PedestalService service;

    public PedestalController(PedestalService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedestalResponse create(@Valid @RequestBody PedestalRequest request) {
        return PedestalResponse.from(service.create(request));
    }

    @GetMapping
    public List<PedestalResponse> findAll() {
        return service.findAll().stream().map(PedestalResponse::from).toList();
    }

    @GetMapping("/{id}")
    public PedestalResponse findById(@PathVariable Long id) {
        return PedestalResponse.from(service.findById(id));
    }

    @PutMapping("/{id}")
    public PedestalResponse update(@PathVariable Long id,
                                   @Valid @RequestBody PedestalRequest request) {
        return PedestalResponse.from(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}