package com.musalasoft.droneproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.musalasoft.droneproject.enums.Model;
import com.musalasoft.droneproject.enums.State;
import com.musalasoft.droneproject.interfaces.Load;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class DroneDTO {

    private long id;

    @JsonProperty
    @NotBlank(message = "Serial Number is mandatory")
    private String serialNumber;

    @JsonProperty
    private Model model;

    @JsonProperty
    private int weightLimit;

    @JsonProperty
    @Range(min=0, max=100, message = "Battery Capacity must be in the range of 0 to 100")
    private int batteryCapacity;

    @JsonProperty
    private State state;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Load> load;

    public DroneDTO() {}

    public DroneDTO(@NotBlank(message = "Serial Number is mandatory") String serialNumber,
                    Model model,
                    @NotBlank(message = "Weight limit is mandatory") int weightLimit,
                    @NotBlank(message = "Battery Capacity is mandatory") @Range(min=0, max=100, message = "Battery Capacity must be in the range of 0 to 100") int batteryCapacity,
                    State state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }
    public DroneDTO(long id,
                    @NotBlank(message = "Serial Number is mandatory") String serialNumber,
                    Model model,
                    @NotBlank(message = "Weight limit is mandatory") int weightLimit,
                    @NotBlank(message = "Battery Capacity is mandatory") @Range(min=0, max=100, message = "Battery Capacity must be in the range of 0 to 100") int batteryCapacity,
                    State state) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Load> getLoad() {
        return load;
    }

    public void setLoad(List<Load> load) {
        this.load = load;
    }

}
