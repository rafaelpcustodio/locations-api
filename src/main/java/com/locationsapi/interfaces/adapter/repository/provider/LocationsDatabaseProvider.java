package com.locationsapi.interfaces.adapter.repository.provider;

import com.locationsapi.entity.VehicleLocationEntity;
import com.locationsapi.interfaces.adapter.repository.LocationsRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationsDatabaseProvider {

  private final LocationsRepository locationRepository;

  public void save(final VehicleLocationEntity vehicleLocationEntity) {
    locationRepository.save(vehicleLocationEntity);
  }

  public List<VehicleLocationEntity> findAllVehicleLocationsByLicensePlate(final String licensePlate) {
    return locationRepository.findAllVehicleLocationsByLicensePlate(licensePlate);
  }

  public List<VehicleLocationEntity> findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(
      final String licensePlate,
      final LocalDateTime dateTime
  ) {
    return locationRepository.findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(licensePlate, dateTime);
  }
}
