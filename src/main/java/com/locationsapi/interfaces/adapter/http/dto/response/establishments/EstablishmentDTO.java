package com.locationsapi.interfaces.adapter.http.dto.response.establishments;

import com.locationsapi.entity.EstablishmentEntity;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentDTO {
  @ApiModelProperty(position = 1, value = "Establishment type id.", required = true)
  private UUID id;
  @ApiModelProperty(position = 1, value = "Establishment type name.", required = true)
  private String name;

  public EstablishmentDTO(final EstablishmentEntity establishmentEntity) {
    this.id = establishmentEntity.getId();
    this.name = establishmentEntity.getName();
  }
}
