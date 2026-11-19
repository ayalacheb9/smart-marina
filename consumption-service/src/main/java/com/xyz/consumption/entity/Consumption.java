package com.xyz.consumption.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "consumptions",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_consumption_measurement_id", columnNames = "measurement_id"))
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "measurement_id", nullable = false, length = 64)
    private String measurementId;

    @Column(name = "pedestal_id", nullable = false)
    private Long pedestalId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ResourceType resource;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal quantity;

    @Column(nullable = false, length = 10)
    private String unit;

    @Column(name = "measured_at", nullable = false)
    private Instant measuredAt;

    @Column(name = "received_at", nullable = false)
    private Instant receivedAt;

    public Consumption() {}

    public Long getId() { return id; }
    public String getMeasurementId() { return measurementId; }
    public void setMeasurementId(String measurementId) { this.measurementId = measurementId; }
    public Long getPedestalId() { return pedestalId; }
    public void setPedestalId(Long pedestalId) { this.pedestalId = pedestalId; }
    public ResourceType getResource() { return resource; }
    public void setResource(ResourceType resource) { this.resource = resource; }
    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public Instant getMeasuredAt() { return measuredAt; }
    public void setMeasuredAt(Instant measuredAt) { this.measuredAt = measuredAt; }
    public Instant getReceivedAt() { return receivedAt; }
    public void setReceivedAt(Instant receivedAt) { this.receivedAt = receivedAt; }
}