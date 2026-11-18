package com.xyz.pedestal.client;

import com.xyz.pedestal.exception.PortServiceUnavailableException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Component
public class PortClient {

    private final RestClient restClient;

    public PortClient(@Value("${port-service.url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public boolean exists(Long portId) {
        try {
            restClient.get()
                    .uri("/api/v1/ports/{id}", portId)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (ResourceAccessException e) {
            throw new PortServiceUnavailableException();
        }
    }
}