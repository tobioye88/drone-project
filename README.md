# Read Me 

Pull Dependencies and Run
```cmd
mvn dependency:resolve
mvn compile
mvn spring-boot:run
```



1. Register Drone

    ```bash
    curl --location --request POST 'http://localhost:8080/api/drone' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "serialNumber": "10234321232",
        "model": "LIGHT_WEIGHT",
        "weightLimit": 500,
        "batteryCapacity": 100
    }'
    ```

1. Load Drone with Medication
    
    ```bash
    curl --location --request PUT 'http://localhost:8080/api/drone/1' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "Aspirin1",
        "weight": 400,
        "code": "ASPIRIN_100X100",
        "image": "http://imgr.com/100X100"
    }'
    ```

1. Available Drones
    ```bash
    curl --location --request GET 'http://localhost:8080/api/drone/available'
    ```

1. Get Drone or Get Drone battery
    ```bash
    curl --location --request GET 'http://localhost:8080/api/drone/1'
    ```