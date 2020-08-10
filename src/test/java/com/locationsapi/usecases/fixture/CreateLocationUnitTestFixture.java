package com.locationsapi.usecases.fixture;

import com.locationsapi.interfaces.adapter.amqp.listener.locationevent.dto.VehicleLocationDTO;

public class CreateLocationUnitTestFixture {

  public static VehicleLocationDTO validVehicleLocation(
      final String licensePlate,
      final Float latitude,
      final Float longitude
  ) {
    return VehicleLocationDTO.builder()
        .latitude(latitude)
        .longitude(longitude)
        .licensePlate(licensePlate)
        .build();
  }
}
