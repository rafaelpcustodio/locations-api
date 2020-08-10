package com.locationsapi.interfaces.adapter.repository.provider;

import com.locationsapi.entity.VehicleLocationEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LocationsDatabaseProvider {
  public void save(final VehicleLocationEntity vehicleLocationEntity) {
  }

  public List<VehicleLocationEntity> findAllVehicleLocationsByLicensePlate(final String licensePlate) {
    return null;
  }

  public List<VehicleLocationEntity> findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(
      final String licensePlate,
      final LocalDateTime dateTime
  ) {
    return null;
  }
}
