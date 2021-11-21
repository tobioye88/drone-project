package com.musalasoft.droneproject.component;

import javax.annotation.PostConstruct;

import com.musalasoft.droneproject.entities.Drone;
import com.musalasoft.droneproject.enums.Model;
import com.musalasoft.droneproject.enums.State;
import com.musalasoft.droneproject.repositories.DroneRepository;

import org.springframework.stereotype.Component;

@Component
public class PreLoadDB {
    private DroneRepository droneRepository;

    public PreLoadDB(DroneRepository droneRepository){
        this.droneRepository = droneRepository;
    }


    @PostConstruct
    private void postConstruct() {
        //add 10 drones
        for(int i = 0; i < 10; i++){
            droneRepository.save(new Drone("1234567890" + i, Model.LightWeight, 500, 100, State.IDLE));
        }
    }
    
}
