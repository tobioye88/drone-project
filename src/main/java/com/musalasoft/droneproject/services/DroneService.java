package com.musalasoft.droneproject.services;

import com.musalasoft.droneproject.dto.DroneDTO;
import com.musalasoft.droneproject.dto.MedicationDTO;
import com.musalasoft.droneproject.entities.Drone;
import com.musalasoft.droneproject.entities.Medication;
import com.musalasoft.droneproject.enums.State;
import com.musalasoft.droneproject.exceptions.DroneBatteryLow;
import com.musalasoft.droneproject.exceptions.DroneIllegalLoading;
import com.musalasoft.droneproject.exceptions.DroneMaxWeightExceeded;
import com.musalasoft.droneproject.exceptions.DroneNotFound;
import com.musalasoft.droneproject.interfaces.Load;
import com.musalasoft.droneproject.repository.DroneRepository;
import com.musalasoft.droneproject.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DroneService {
    public final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    public DroneService(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
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

    public MedicationDTO loadDrone(long droneId, Load payload) throws Exception {
        Drone drone = validateDroneLoading(droneId, payload);
        if(drone.getState().equals(State.IDLE)){
            drone.setState(State.LOADING);
            droneRepository.save(drone);
        }
        MedicationDTO medicationDTO = (MedicationDTO) payload;
        Medication medicationEntity = new Medication(
                medicationDTO.getName(),
                medicationDTO.getWeight(),
                medicationDTO.getCode(),
                medicationDTO.getImage());
        medicationEntity.setDrone(drone);
        medicationRepository.save(medicationEntity);
        medicationDTO.setId(medicationEntity.getId());
        medicationDTO.setDrone(new DroneDTO(
                drone.getId(),
                drone.getSerialNumber(),
                drone.getModel(), drone.getWeightLimit(),
                drone.getBatteryCapacity(),
                drone.getState()));
        return medicationDTO;
    }

    private Drone validateDroneLoading(long droneId, Load load) throws Exception {
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);
        if(!optionalDrone.isPresent()){
            throw new DroneNotFound();
        }
        Drone drone = optionalDrone.get();
        if(!drone.getState().equals(State.IDLE) && !drone.getState().equals(State.LOADING)){
            throw new DroneIllegalLoading();
        }
        if(drone.getBatteryCapacity() < 25){
            throw new DroneBatteryLow("Drone Battery below 25%");
        }
        List<Medication> medications = this.getMedicationsByDroneId(droneId);
        Integer totalWeight = medications.stream().map(Medication::getWeight).reduce(0, Integer::sum);
        totalWeight += load.getWeight();
        if(totalWeight > drone.getWeightLimit()){
            throw new DroneMaxWeightExceeded();
        }
        return drone;
    }

    private List<Medication> getMedicationsByDroneId(long id) {
        return medicationRepository.findByDrone_Id(id);
    }

    public List<DroneDTO> getAvailableDrones() {
        List<Drone> idleDrones = droneRepository.findByState(State.IDLE);
        List<DroneDTO> results = new ArrayList<>();
        idleDrones.forEach(drone -> results.add(new DroneDTO(
                drone.getId(),
                drone.getSerialNumber(),
                drone.getModel(),
                drone.getWeightLimit(),
                drone.getBatteryCapacity(),
                drone.getState())));
        return results;
    }

    public DroneDTO getDrone(long id) throws DroneNotFound {
        Drone drone = droneRepository.findById(id).orElseThrow(DroneNotFound::new);
        DroneDTO droneDTO = new DroneDTO(
                drone.getId(),
                drone.getSerialNumber(),
                drone.getModel(),
                drone.getWeightLimit(),
                drone.getBatteryCapacity(),
                drone.getState());
        droneDTO.setLoad(mapDroneMedicationsToLoads(drone.getMedications()));
        return droneDTO;
    }

    private List<Load> mapDroneMedicationsToLoads(List<Medication> medications) {
        if(medications == null) return null;
        return new ArrayList<>(medications);
    }
}
