package com.locationsapi.interfaces.adapter.repository.dynamodb;

import com.locationsapi.entity.VehicleLocationEntity;
import com.locationsapi.interfaces.adapter.repository.LocationsRepository;
import com.locationsapi.interfaces.adapter.repository.dynamodb.builder.VehicleLocationsPutItemRequestBuilder;
import com.locationsapi.interfaces.adapter.repository.dynamodb.builder.VehicleLocationsQueryRequestBuilder;
import com.locationsapi.interfaces.adapter.repository.dynamodb.converter.RepositoryResponseConverter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;

@Repository
@RequiredArgsConstructor
class VehicleLocationsRepositoryDynamoDb implements LocationsRepository {

  private final DynamoDbClient dynamoDbClient;
  private final RepositoryResponseConverter<VehicleLocationEntity> vehicleLocationsResponseConverter;

  @Override
  public List<VehicleLocationEntity> findAllVehicleLocationsByLicensePlate(final String licensePlate) {
    final QueryRequest queryRequest = VehicleLocationsQueryRequestBuilder.builder()
        .withLicensePlate(licensePlate)
        .build();
    return this.dynamoDbClient.query(queryRequest)
        .items()
        .stream()
        .map(vehicleLocationsResponseConverter::fromEntry)
        .collect(Collectors.toList());
  }

  @Override
  public void save(VehicleLocationEntity vehicleLocationEntity) {
    final PutItemRequest putItemRequest = VehicleLocationsPutItemRequestBuilder.builder()
        .withEntity(vehicleLocationEntity)
        .build();
    dynamoDbClient.putItem(putItemRequest);
  }

  @Override
  public List<VehicleLocationEntity> findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(
      final String licensePlate,
      final LocalDateTime dateTime
  ) {
    final QueryRequest queryRequest = VehicleLocationsQueryRequestBuilder.builder()
        .withLicensePlateAndDateTimeUntilNow(licensePlate,dateTime)
        .build();
    return this.dynamoDbClient.query(queryRequest)
        .items()
        .stream()
        .map(vehicleLocationsResponseConverter::fromEntry)
        .collect(Collectors.toList());
  }
}
