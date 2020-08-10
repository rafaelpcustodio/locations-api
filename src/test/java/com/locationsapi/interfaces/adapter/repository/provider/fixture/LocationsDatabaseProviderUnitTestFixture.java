package com.locationsapi.interfaces.adapter.repository.provider.fixture;

import com.locationsapi.entity.VehicleLocationEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocationsDatabaseProviderUnitTestFixture {

  private static final String LICENSE_PLATE = "EET4787";

  public static VehicleLocationEntity validLocation(
      final String licensePlate,
      final Float latitude,
      final Float longitude,
      final LocalDateTime localDateTime) {
    return VehicleLocationEntity.builder()
        .licensePlate(licensePlate)
        .latitude(latitude)
        .createdAt(localDateTime)
        .longitude(longitude)
        .build();
  }

  public static List<VehicleLocationEntity> validLocationsList() {
    List<VehicleLocationEntity> locations = new ArrayList<>();
    locations.add(validLocation(LICENSE_PLATE, 41.40285F, 2.17403F, LocalDateTime.of(2090,8,3,20,21,22)));
    locations.add(validLocation(LICENSE_PLATE, 41.40286F, 2.17402F, LocalDateTime.of(2090,8,3,20,22,22)));
    locations.add(validLocation(LICENSE_PLATE,41.40287F, 2.17401F, LocalDateTime.of(2090,8,3,20,23,22)));
    locations.add(validLocation(LICENSE_PLATE, 41.40288F, 2.17400F, LocalDateTime.of(2090,8,3,20,24,22)));
    locations.add(validLocation(LICENSE_PLATE,41.40289F, 2.17399F, LocalDateTime.of(2090,8,3,20,25,22)));
    locations.add(validLocation(LICENSE_PLATE, 41.40290F, 2.17398F, LocalDateTime.of(2090,8,3,20,26,22)));
    locations.add(validLocation(LICENSE_PLATE, 41.40291F, 2.17397F, LocalDateTime.of(2090,8,3,20,27,22)));
    locations.add(validLocation(LICENSE_PLATE,41.4092F, 2.17396F, LocalDateTime.of(2090,8,3,20,28,22)));
    locations.add(validLocation(LICENSE_PLATE,41.40293F, 2.17395F, LocalDateTime.of(2090,8,3,20,29,22)));
    return locations;
  }
}
