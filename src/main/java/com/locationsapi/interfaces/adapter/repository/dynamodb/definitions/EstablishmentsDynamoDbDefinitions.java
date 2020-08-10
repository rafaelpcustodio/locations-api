package com.locationsapi.interfaces.adapter.repository.dynamodb.definitions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public enum EstablishmentsDynamoDbDefinitions {

  @Value("${app.repository.establishments.name}")
  ESTABLISHMENTS_TABLE("establishments"),
  ESTABLISHMENTS_ID("id"),
  ESTABLISHMENTS_NAME("name");

  @Getter
  private final String value;
}
