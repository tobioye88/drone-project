package com.musalasoft.droneproject.repository;

import com.musalasoft.droneproject.entities.Drone;
import com.musalasoft.droneproject.enums.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends CrudRepository<Drone, Long> {
    List<Drone> findByState(State state);
}
