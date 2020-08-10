package com.locationsapi.usecases.fixture;

import com.locationsapi.interfaces.adapter.http.dto.request.locations.minimumdistance.MinimumDistanceLocationDTO;
import java.util.ArrayList;
import java.util.List;

public class FindMinimumDistanceUnitTestFixture {

  public static MinimumDistanceLocationDTO validLocation(final Float latitude, final Float longitude) {
    return MinimumDistanceLocationDTO.builder()
        .latitude(latitude)
        .longitude(longitude)
        .build();
  }

  public static List<MinimumDistanceLocationDTO> validLocations() {
    List<MinimumDistanceLocationDTO> locations = new ArrayList<>();
    locations.add(validLocation(41.40185F, 2.17404F));
    locations.add(validLocation(41.40186F, 2.17404F));
    locations.add(validLocation(41.40187F, 2.17404F));
    locations.add(validLocation(41.40188F, 2.17404F));
    locations.add(validLocation(41.40189F, 2.17404F));
    locations.add(validLocation(41.40190F, 2.17404F));
    locations.add(validLocation(41.40191F, 2.17404F));
    locations.add(validLocation(41.40192F, 2.17404F));
    locations.add(validLocation(41.40193F, 2.17404F));
    return locations;
  }
}
