package com.cooba.controller;

import com.cooba.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gate")
public class GateController {
    private final RestTemplate restTemplate;

    @GetMapping("/call")
    public Response<String> callInner(){
        ResponseEntity<Response<String>> entity = restTemplate.exchange("http://127.0.0.1:8081/inner", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return entity.getBody();
    }
}
