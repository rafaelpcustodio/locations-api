package com.locationsapi.interfaces.adapter.repository.dynamodb.definitions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public enum VehicleLocationsDynamoDbDefinitions {
  @Value("${app.repository.locations.name}")
  LOCATIONS_TABLE("locations"),
  LICENSE_PLATE("licensePlate"),
  LATITUDE("latitude"),
  LONGITUDE("longitude"),
  CREATED_AT("createdAt");

  @Getter
  private final String value;
}
