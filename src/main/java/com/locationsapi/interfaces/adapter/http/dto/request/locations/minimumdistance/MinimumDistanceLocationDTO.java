package com.locationsapi.interfaces.adapter.http.dto.request.locations.minimumdistance;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MinimumDistanceLocationDTO {

  @ApiModelProperty(value = "Latitude from the location.")
  @NotNull(message = "Latitude is mandatory")
  private Float latitude;

  @ApiModelProperty(value = "Longitude from the location.")
  @NotNull(message = "Longitude is mandatory")
  private Float longitude;
}
