package com.locationsapi.interfaces.adapter.http.fixture;

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
}
