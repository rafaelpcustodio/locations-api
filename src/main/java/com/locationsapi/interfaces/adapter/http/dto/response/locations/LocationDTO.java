package com.locationsapi.interfaces.adapter.http.dto.response.locations;

import com.locationsapi.entity.VehicleLocationEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class LocationDTO {
  @ApiModelProperty(position = 1, value = "The latitude of a location.", required = true)
  private final Float latitude;
  @ApiModelProperty(position = 1, value = "The longitude of a location.", required = true)
  private final Float longitude;

  public LocationDTO(final VehicleLocationEntity vehicleLocationEntity) {
    this(vehicleLocationEntity.getLatitude(), vehicleLocationEntity.getLongitude());
  }
}
