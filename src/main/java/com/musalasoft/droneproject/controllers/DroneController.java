package com.musalasoft.droneproject.controllers;

import com.musalasoft.droneproject.dto.DroneDTO;
import com.musalasoft.droneproject.services.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/drone")
public class DroneController {
    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping(value={"", "/"}, consumes = "application/json")
    public ResponseEntity<DroneDTO> register(@Valid @RequestBody DroneDTO droneDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(droneService.register(droneDTO));
    }


}
