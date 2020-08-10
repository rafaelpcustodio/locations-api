package com.locationsapi.interfaces.adapter.amqp.listener.locationevent.dto;

import com.locationsapi.entity.VehicleLocationEntity;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class VehicleLocationDTO {

  @NotNull
  private String licensePlate;

  @NotNull
  private Float latitude;

  @NotNull
  private Float longitude;

  public VehicleLocationEntity toEntity() {
    return VehicleLocationEntity.builder()
        .licensePlate(this.getLicensePlate())
        .longitude(this.getLongitude())
        .latitude(this.getLatitude())
        .build();
  }
}
