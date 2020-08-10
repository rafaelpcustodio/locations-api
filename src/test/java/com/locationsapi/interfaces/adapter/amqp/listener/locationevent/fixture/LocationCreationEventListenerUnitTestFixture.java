package com.locationsapi.interfaces.adapter.amqp.listener.locationevent.fixture;

import com.locationsapi.interfaces.adapter.amqp.listener.locationevent.dto.VehicleLocationDTO;

public class LocationCreationEventListenerUnitTestFixture {
  public static VehicleLocationDTO validVehicleDTO(final String licensePlate, final Float latitude, final Float longitude) {
    return VehicleLocationDTO.builder()
        .latitude(latitude)
        .longitude(longitude)
        .licensePlate(licensePlate)
        .build();
  }
}
