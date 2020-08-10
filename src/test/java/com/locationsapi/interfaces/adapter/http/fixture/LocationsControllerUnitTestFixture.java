package com.locationsapi.interfaces.adapter.http.fixture;

import com.locationsapi.interfaces.adapter.http.dto.request.locations.LocationRequestDTO;
import com.locationsapi.interfaces.adapter.http.dto.request.locations.minimumdistance.MinimumDistanceLocationDTO;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.LocationDTO;
import java.util.ArrayList;
import java.util.List;

public class LocationsControllerUnitTestFixture {

  public static LocationDTO validLocation(final Float latitude, final Float longitude) {
    return LocationDTO.builder()
        .latitude(latitude)
        .longitude(longitude)
        .build();
  }

  public static MinimumDistanceLocationDTO validMinimumDistanceLocationDTO(final Float latitude, final Float longitude) {
    return MinimumDistanceLocationDTO.builder()
        .latitude(latitude)
        .longitude(longitude)
        .build();
  }

  public static List<MinimumDistanceLocationDTO> validLocationsDTO() {
    List<MinimumDistanceLocationDTO> locations = new ArrayList<>();
    locations.add(validMinimumDistanceLocationDTO(41.40185F, 2.17404F));
    locations.add(validMinimumDistanceLocationDTO(41.40186F, 2.17404F));
    locations.add(validMinimumDistanceLocationDTO(41.40187F, 2.17404F));
    locations.add(validMinimumDistanceLocationDTO(41.40188F, 2.17404F));
    locations.add(validMinimumDistanceLocationDTO(41.40189F, 2.17404F));
    locations.add(validMinimumDistanceLocationDTO(41.40190F, 2.17404F));
    locations.add(validMinimumDistanceLocationDTO(41.40191F, 2.17404F));
    locations.add(validMinimumDistanceLocationDTO(41.40192F, 2.17404F));
    locations.add(validMinimumDistanceLocationDTO(41.40193F, 2.17404F));
    return locations;
  }

  public static List<LocationDTO> validLocationsList() {
    List<LocationDTO> locations = new ArrayList<>();
    locations.add(validLocation(41.40285F, 2.17404F));
    locations.add(validLocation(41.40286F, 2.17404F));
    locations.add(validLocation(41.40287F, 2.17404F));
    locations.add(validLocation(41.40288F, 2.17404F));
    locations.add(validLocation(41.40289F, 2.17404F));
    locations.add(validLocation(41.40290F, 2.17404F));
    locations.add(validLocation(41.40291F, 2.17404F));
    locations.add(validLocation(41.4092F, 2.17404F));
    locations.add(validLocation(41.40293F, 2.17404F));
    return locations;
  }

  public static LocationRequestDTO validRequestLocationDTO(
      final String licensePlate,
      final Float latitude,
      final Float longitude) {
    return LocationRequestDTO.builder()
        .licensePlate(licensePlate)
        .latitude(latitude)
        .longitude(longitude)
        .build();
  }
}
