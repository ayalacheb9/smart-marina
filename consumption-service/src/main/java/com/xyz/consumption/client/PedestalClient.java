package com.xyz.consumption.client;

import com.xyz.consumption.exception.PedestalServiceUnavailableException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Component
public class PedestalClient {

    private final RestClient restClient;

    public PedestalClient(@Value("${pedestal-service.url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public boolean exists(Long pedestalId) {
        try {
            restClient.get()
                    .uri("/api/v1/pedestals/{id}", pedestalId)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (ResourceAccessException e) {
            throw new PedestalServiceUnavailableException();
        }
    }
}