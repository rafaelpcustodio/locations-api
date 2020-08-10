package com.locationsapi.interfaces.adapter.http.dto.response.locations;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@ApiModel(value = "Minimum distance info", description = "Minimum distance found in meters from one location and a list of locations")
public class MinimumDistanceResponseDTO {
  @ApiModelProperty(value = "The closest location from the desired location." , required = true)
  private final ClosestLocationDTO location;
  @ApiModelProperty(value = "The minimum distance from a location and the closest location in meters." , required = true)
  private final Double minimumDistance;
}
