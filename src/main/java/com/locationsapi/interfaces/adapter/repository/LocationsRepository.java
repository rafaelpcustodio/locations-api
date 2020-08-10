package com.locationsapi.interfaces.adapter.repository;

import com.locationsapi.entity.VehicleLocationEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface LocationsRepository {

  List<VehicleLocationEntity> findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(String licensePlate, LocalDateTime dateTime);

  List<VehicleLocationEntity> findAllVehicleLocationsByLicensePlate(String licensePlate);

  void save(VehicleLocationEntity vehicleLocationEntity);
}
