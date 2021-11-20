package com.musalasoft.droneproject.repository;

import com.musalasoft.droneproject.entities.Drone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends CrudRepository<Drone, Long> {
}
