package com.locationsapi.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class VehicleLocationEntity {
  private final String licensePlate;
  private final Float latitude;
  private final Float longitude;
  private final LocalDateTime createdAt;
}
