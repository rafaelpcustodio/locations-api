package com.locationsapi.interfaces.adapter.http.dto.response.establishments;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@ApiModel(value = "Establishments info", description = "All Establishments possible for search")
public class EstablishmentsResponseDTO {
  @ApiModelProperty(position = 1, value = "Establishment types for possible search.", required = true)
  private final List<EstablishmentDTO> establishments;
}
