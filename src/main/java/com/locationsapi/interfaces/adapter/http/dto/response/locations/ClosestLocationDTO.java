package com.locationsapi.interfaces.adapter.http.dto.response.locations;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ClosestLocationDTO {
  @ApiModelProperty(position = 1, value = "The latitude from the closest location.", required = true)
  private final Float latitude;
  @ApiModelProperty(position = 1, value = "The longitude from the closest location.", required = true)
  private final Float longitude;
}
