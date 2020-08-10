package com.locationsapi.interfaces.adapter.http.dto.request.locations.minimumdistance;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Minimum distance request")
public class MinimumDistanceRequestDTO {

  @Valid
  @JsonProperty(value = "currentLocation")
  @NotNull(message = "Current location is mandatory")
  @ApiModelProperty(value = "The current location from the desired search.", required = true)
  private MinimumDistanceLocationDTO currentLocation;

  @Valid
  @JsonProperty(value = "locationsPlaces")
  @NotNull(message = "Locations from places are mandatory")
  @ApiModelProperty(value = "The locations that are close to the desired current location search.", required = true)
  private List<MinimumDistanceLocationDTO> locationsPlaces;
}
