package com.cooba.controller;

import com.cooba.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/zipkin")
public class ZipkinController {

    @GetMapping
    public Response<String> zipkin(){
        Response<String> response = new Response<>();
        response.setData("zipkin");
        return response;
    }
}
