package com.musalasoft.droneproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musalasoft.droneproject.interfaces.Load;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


public class MedicationDTO implements Load {
    private long id;

    @JsonProperty
    @NotBlank(message = "Medication name is mandatory")
    private String name;

    @JsonProperty
    private int weight;

    @JsonProperty
    @Pattern( regexp = "^[a-zA-Z0-9_\\-]+$", message="Name can only contain letters, numbers, '-', '_'")
    private String code;

    @JsonProperty
    @NotBlank(message = "Medication name is mandatory")
    private String image;

    private DroneDTO drone;

    public MedicationDTO() {
    }

    public MedicationDTO(String name, int weight, String code, String image) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    public MedicationDTO(long id, String name, int weight, String code, String image, DroneDTO drone) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
        this.drone = drone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public DroneDTO getDrone() {
        return drone;
    }

    public void setDrone(DroneDTO drone) {
        this.drone = drone;
    }
}
