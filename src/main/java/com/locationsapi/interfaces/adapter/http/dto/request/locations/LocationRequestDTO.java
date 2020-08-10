package com.locationsapi.interfaces.adapter.http.dto.request.locations;

import com.locationsapi.interfaces.adapter.amqp.listener.locationevent.dto.VehicleLocationDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Location parameter request", description = "Representation of an location value request")
public class LocationRequestDTO {

  @NotNull(message = "License plate is mandatory")
  @ApiModelProperty(value = "License plate from the desired vehicle location.", required = true)
  @Pattern(regexp = "^[A-Z]{3}[0-9]{4}$", message = "Invalid license plate")
  private String licensePlate;

  @NotNull(message = "Latitude is mandatory")
  @ApiModelProperty(value = "Latitude from the desired vehicle location.", required = true)
  private Float latitude;

  @NotNull(message = "Longitude is mandatory")
  @ApiModelProperty(value = "Longitude from the desired vehicle location.", required = true)
  private Float longitude;

  public VehicleLocationDTO toMessage() {
    return VehicleLocationDTO.builder()
        .licensePlate(this.getLicensePlate())
        .latitude(this.getLatitude())
        .longitude(this.getLongitude())
        .build();
  }
}
