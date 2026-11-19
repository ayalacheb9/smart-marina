package com.xyz.consumption.controller;

import com.xyz.consumption.dto.ConsumptionRequest;
import com.xyz.consumption.dto.ConsumptionResponse;
import com.xyz.consumption.service.ConsumptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consumptions")
public class ConsumptionController {

    private final ConsumptionService service;

    public ConsumptionController(ConsumptionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumptionResponse create(@Valid @RequestBody ConsumptionRequest request) {
        return ConsumptionResponse.from(service.create(request));
    }

    @GetMapping
    public List<ConsumptionResponse> findAll(@RequestParam(required = false) Long pedestalId) {
        return service.findAll(pedestalId).stream().map(ConsumptionResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ConsumptionResponse findById(@PathVariable Long id) {
        return ConsumptionResponse.from(service.findById(id));
    }
}