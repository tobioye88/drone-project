package com.musalasoft.droneproject.controllers;

import com.musalasoft.droneproject.dto.DroneDTO;
import com.musalasoft.droneproject.dto.MedicationDTO;
import com.musalasoft.droneproject.enums.Model;
import com.musalasoft.droneproject.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DroneControllerTest {

    @Autowired
    private DroneController controller;

    public DroneDTO getIdleDroneDTO(){
        return new DroneDTO("1234567890", Model.LightWeight, 500, 100, State.IDLE);
    }

    public MedicationDTO getMedicationDTO(){
        return new MedicationDTO("Aspirin1", 400, "ASPIRIN_100gms", "http://imgr.com/100X100");
    }


    @Test
    public void contextLoads() throws Exception {
        assertNotNull(controller);
    }

    @Test
    void register() {
        DroneDTO expectedDrone = getIdleDroneDTO();
        ResponseEntity<DroneDTO> registeredDrone = controller.register(expectedDrone);
        DroneDTO actualDrone = registeredDrone.getBody();

        assertNotNull(actualDrone);
        assertEquals(expectedDrone.getSerialNumber(), actualDrone.getSerialNumber());
    }

    @Test
    void loadDrone() {
        MedicationDTO expectedMedicationDTO = getMedicationDTO();
        assertDoesNotThrow(() -> {
            ResponseEntity<MedicationDTO> medicationDTOResponseEntity = controller.loadDrone(1L, expectedMedicationDTO);
            assertEquals(medicationDTOResponseEntity.getStatusCode(), HttpStatus.OK);

            MedicationDTO body = medicationDTOResponseEntity.getBody();
            assertNotNull(body);
            assertNotNull(body.getDrone());
        });
    }

    @Test
    void dronesAvailable() {
        assertDoesNotThrow(() -> {
            ResponseEntity<List<DroneDTO>> droneDTOResponseEntity = controller.dronesAvailable();
            assertEquals(droneDTOResponseEntity.getStatusCode(), HttpStatus.OK);

            List<DroneDTO> droneDTO = droneDTOResponseEntity.getBody();
            assertNotNull(droneDTO);
        });
    }

    @Test
    void getDrone() {
        assertDoesNotThrow(() -> {
            ResponseEntity<DroneDTO> droneDTOResponseEntity = controller.getDrone(1L);
            assertEquals(droneDTOResponseEntity.getStatusCode(), HttpStatus.OK);

            DroneDTO droneDTO = droneDTOResponseEntity.getBody();
            assertNotNull(droneDTO);
        });
    }
}