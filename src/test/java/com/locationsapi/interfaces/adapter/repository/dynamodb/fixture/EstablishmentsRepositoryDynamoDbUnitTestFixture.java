package com.locationsapi.interfaces.adapter.repository.dynamodb.fixture;

import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.EstablishmentsDynamoDbDefinitions.ESTABLISHMENTS_ID;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.EstablishmentsDynamoDbDefinitions.ESTABLISHMENTS_NAME;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.EstablishmentsDynamoDbDefinitions.ESTABLISHMENTS_TABLE;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

public class EstablishmentsRepositoryDynamoDbUnitTestFixture {

  private static final UUID ESTABLISHMENT_ID = UUID.randomUUID();

  public static ScanRequest scanRequest() {
    return ScanRequest.builder()
        .tableName(ESTABLISHMENTS_TABLE.getValue())
        .build();
  }

  public static ScanResponse response() {
    return ScanResponse.builder()
        .items(defaultEstablishment())
        .count(1)
        .scannedCount(1)
        .build();
  }

  public static Map<String, AttributeValue> defaultEstablishment() {
    final Map<String, AttributeValue> establishment = new HashMap<>();
    establishment.put(ESTABLISHMENTS_ID.getValue(), AttributeValue.builder().s(ESTABLISHMENT_ID.toString()).build());
    establishment.put(ESTABLISHMENTS_NAME.getValue(), AttributeValue.builder().s("Restaurants").build());
    return establishment;
  }
}
