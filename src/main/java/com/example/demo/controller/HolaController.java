package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path="/hola")
@RestController
public class HolaController {

    @GetMapping
    public ResponseEntity<String> hola() {
        return ResponseEntity.ok("hola");
    }

}
