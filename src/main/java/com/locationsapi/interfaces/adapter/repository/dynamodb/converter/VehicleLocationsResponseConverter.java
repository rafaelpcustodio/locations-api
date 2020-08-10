package com.locationsapi.interfaces.adapter.repository.dynamodb.converter;

import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.CREATED_AT;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LATITUDE;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LICENSE_PLATE;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LONGITUDE;

import com.locationsapi.entity.VehicleLocationEntity;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@Component
public class VehicleLocationsResponseConverter implements RepositoryResponseConverter<VehicleLocationEntity> {

  @Override
  public VehicleLocationEntity fromEntry(final Map<String, AttributeValue> entry) {
    final String licensePlateFromDb = entry.get(LICENSE_PLATE.getValue()).s();
    final String latitudeFromDb = entry.get(LATITUDE.getValue()).n();
    final String longitudeFromDb = entry.get(LONGITUDE.getValue()).n();
    final String createdAtFromDb = entry.get(CREATED_AT.getValue()).s();
    return VehicleLocationEntity.builder()
        .latitude(Float.valueOf(latitudeFromDb))
        .longitude(Float.valueOf(longitudeFromDb))
        .licensePlate(licensePlateFromDb)
        .createdAt(LocalDateTime.parse(createdAtFromDb))
        .build();
  }
}
