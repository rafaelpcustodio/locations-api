package com.locationsapi.interfaces.adapter.repository.dynamodb.builder;

import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.CREATED_AT;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LATITUDE;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LICENSE_PLATE;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LOCATIONS_TABLE;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LONGITUDE;

import com.locationsapi.entity.VehicleLocationEntity;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest.Builder;
import software.amazon.awssdk.services.dynamodb.model.ReturnValue;
import software.amazon.awssdk.utils.StringUtils;
import software.amazon.awssdk.utils.builder.SdkBuilder;

public class VehicleLocationsPutItemRequestBuilder {

  private Builder putItemRequest;

  public static VehicleLocationsPutItemRequestBuilder builder() {
    return new VehicleLocationsPutItemRequestBuilder();
  }

  public VehicleLocationsPutItemRequestBuilder withEntity(final VehicleLocationEntity entity) {
    final String licensePlate = entity.getLicensePlate();
    final Float latitude = entity.getLatitude();
    final Float longitude = entity.getLongitude();
    final Map<String, AttributeValue> attributes = new HashMap<>();
    attributes.put(LICENSE_PLATE.getValue(), AttributeValue.builder().s(licensePlate).build());
    attributes.put(LATITUDE.getValue(), AttributeValue.builder().n(String.valueOf(latitude)).build());
    attributes.put(LONGITUDE.getValue(), AttributeValue.builder().n(String.valueOf(longitude)).build());
    attributes.put(CREATED_AT.getValue(), AttributeValue.builder().s(LocalDateTime.now(ZoneOffset.UTC).toString()).build());
    putItemRequest = PutItemRequest.builder()
        .item(attributes)
        .returnValues(ReturnValue.NONE);
    return this;
  }

  public PutItemRequest build() {
    return Optional.ofNullable(LOCATIONS_TABLE.getValue())
        .filter(StringUtils::isNotBlank)
        .map(putItemRequest::tableName)
        .map(SdkBuilder::build)
        .orElseThrow(IllegalArgumentException::new);
  }
}
