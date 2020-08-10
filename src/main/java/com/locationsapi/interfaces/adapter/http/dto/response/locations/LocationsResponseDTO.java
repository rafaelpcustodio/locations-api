package com.locationsapi.interfaces.adapter.http.dto.response.locations;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@ApiModel(value = "Vehicle Locations info", description = "All locations from vehicle")
public class LocationsResponseDTO {

  @ApiModelProperty(value = "License plate from vehicle" , required = true)
  private final String licensePlate;

  @ApiModelProperty(value = "Locations from vehicle" , required = true)
  private final List<LocationDTO> vehicleLocationEntities;
}
