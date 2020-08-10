package com.locationsapi.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class EstablishmentEntity {
  private final UUID id;
  private final String name;
}
