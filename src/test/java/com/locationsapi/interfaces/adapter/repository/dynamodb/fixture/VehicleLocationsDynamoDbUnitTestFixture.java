package com.locationsapi.interfaces.adapter.repository.dynamodb.fixture;

import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.CREATED_AT;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LATITUDE;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LICENSE_PLATE;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LONGITUDE;

import com.locationsapi.entity.VehicleLocationEntity;
import com.locationsapi.interfaces.adapter.repository.dynamodb.builder.VehicleLocationsPutItemRequestBuilder;
import com.locationsapi.interfaces.adapter.repository.dynamodb.builder.VehicleLocationsQueryRequestBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import software.amazon.awssdk.core.SdkResponse;
import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

public class VehicleLocationsDynamoDbUnitTestFixture {

  public static VehicleLocationEntity validVehicleLocation(
      final String licensePlate,
      final Float latitude,
      final Float longitude
  ) {
    return VehicleLocationEntity.builder()
        .licensePlate(licensePlate)
        .longitude(longitude)
        .latitude(latitude)
        .build();
  }

  public static QueryResponse responseWithItems() {
    return QueryResponse.builder()
        .items(validLocationItems())
        .build();
  }

  public static QueryResponse responseWithItem() {
    return QueryResponse.builder()
        .items(validLocationItem(
            "ETR5467",
            47.435F,
            1.435F,
            LocalDateTime.now()))
        .count(1)
        .scannedCount(1)
        .build();
  }

  public static SdkResponse responseWithSuccess() {
    return PutItemResponse.builder()
        .sdkHttpResponse(SdkHttpResponse.builder()
            .statusText("OK")
            .statusCode(HttpStatusCode.OK)
            .build())
        .build();
  }

  public static QueryRequest validQueryRequestWithLicensePlateAndLocalDateTime(
      final String licensePlate,
      final LocalDateTime localDateTime
  ) {
    return VehicleLocationsQueryRequestBuilder.builder()
        .withLicensePlateAndDateTimeUntilNow(licensePlate, localDateTime)
        .build();
  }

  public static QueryRequest validQueryRequestWithLicensePlate(
      final String licensePlate
  ) {
    return VehicleLocationsQueryRequestBuilder.builder()
        .withLicensePlate(licensePlate)
        .build();
  }

  public static List<Map<String, AttributeValue>> validLocationItems() {
    List<Map<String, AttributeValue>> items = new ArrayList<>();
    items.add(validLocationItem(
        "ETR5467",
        47.435F,
        1.435F,
        LocalDateTime.of(2020, 8,3,23,32,33)
    ));
    items.add(validLocationItem(
        "ETR5467",
        47.435F,
        1.435F,
        LocalDateTime.of(2020, 8,3,23,33,33)
    ));
    items.add(validLocationItem(
        "ETR5467",
        47.435F,
        1.435F,
        LocalDateTime.of(2020, 8,3,23,34,33)
    ));
    items.add(validLocationItem(
        "ETR5467",
        47.435F,
        1.435F,
        LocalDateTime.of(2020, 8,3,23,35,33)
    ));
    return items;
  }

  public static PutItemRequest putItemRequestFromEntity(final VehicleLocationEntity vehicleLocationEntity) {
    return VehicleLocationsPutItemRequestBuilder.builder()
        .withEntity(vehicleLocationEntity)
        .build();
  }

  public static Map<String, AttributeValue> validLocationItem(
      final String licensePlate,
      final Float latitude,
      final Float longitude,
      final LocalDateTime localDateTime
  ) {
    final Map<String, AttributeValue> item = new HashMap<>();
    item.put(LONGITUDE.getValue(), AttributeValue.builder().n(String.valueOf(longitude)).build());
    item.put(LATITUDE.getValue(), AttributeValue.builder().n(String.valueOf(latitude)).build());
    item.put(LICENSE_PLATE.getValue(), AttributeValue.builder().s(licensePlate).build());
    item.put(CREATED_AT.getValue(), AttributeValue.builder().s(localDateTime.toString()).build());
    return item;
  }
}
