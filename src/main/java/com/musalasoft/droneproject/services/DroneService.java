package com.musalasoft.droneproject.services;

import com.musalasoft.droneproject.dto.DroneDTO;
import com.musalasoft.droneproject.entities.Drone;
import com.musalasoft.droneproject.enums.State;
import com.musalasoft.droneproject.repository.DroneRepository;
import org.springframework.stereotype.Service;

@Service
public class DroneService {
    public final DroneRepository droneRepository;

    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }
    public DroneDTO register(DroneDTO newDrone){
        Drone droneEntity = new Drone(
                newDrone.getSerialNumber(),
                newDrone.getModel(),
                newDrone.getWeightLimit(),
                newDrone.getBatteryCapacity(),
                State.IDLE);
        droneRepository.save(droneEntity);
        newDrone.setId(droneEntity.getId());
        newDrone.setState(State.IDLE);
        return newDrone;
    }


}
