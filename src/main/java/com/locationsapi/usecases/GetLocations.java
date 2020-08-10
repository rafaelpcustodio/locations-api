package com.locationsapi.usecases;

import static java.util.Objects.nonNull;

import com.locationsapi.entity.VehicleLocationEntity;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.LocationDTO;
import com.locationsapi.interfaces.adapter.repository.provider.LocationsDatabaseProvider;
import com.locationsapi.usecases.exceptions.LocationsNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetLocations {

  private final LocationsDatabaseProvider locationsDatabaseProvider;

  public List<LocationDTO> execute(final String licensePlate, final LocalDateTime dateTime) {
    if(nonNull(dateTime)) {
      return this.findLocationsWithLicensePlateAndDateTime(licensePlate, dateTime);
    }
    return this.findLocationsWithLicense(licensePlate);
  }

  private List<LocationDTO> findLocationsWithLicensePlateAndDateTime(final String licensePlate, final LocalDateTime dateTime) {
    final List<VehicleLocationEntity> locations = locationsDatabaseProvider
        .findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(licensePlate, dateTime);
    hasNoLocations(locations);
    return locations.stream()
        .map(LocationDTO::new)
        .collect(Collectors.toList());
  }

  private List<LocationDTO> findLocationsWithLicense(final String licensePlate) {
    final List<VehicleLocationEntity> locations = locationsDatabaseProvider
        .findAllVehicleLocationsByLicensePlate(licensePlate);
    hasNoLocations(locations);
    return locations.stream()
        .map(LocationDTO::new)
        .collect(Collectors.toList());
  }

  private void hasNoLocations(final List<VehicleLocationEntity> locations) {
    if(locations.isEmpty()) {
      throw new LocationsNotFoundException();
    }
  }
}
