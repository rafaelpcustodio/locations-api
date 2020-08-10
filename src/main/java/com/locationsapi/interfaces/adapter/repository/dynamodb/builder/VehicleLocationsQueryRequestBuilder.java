package com.locationsapi.interfaces.adapter.repository.dynamodb.builder;

import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.CREATED_AT;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LICENSE_PLATE;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LOCATIONS_TABLE;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ComparisonOperator;
import software.amazon.awssdk.services.dynamodb.model.Condition;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest.Builder;
import software.amazon.awssdk.utils.StringUtils;
import software.amazon.awssdk.utils.builder.SdkBuilder;

@RequiredArgsConstructor
public class VehicleLocationsQueryRequestBuilder {

    private final Builder queryRequest;

    public static VehicleLocationsQueryRequestBuilder builder() {
        return new VehicleLocationsQueryRequestBuilder(QueryRequest.builder());
    }

    public VehicleLocationsQueryRequestBuilder withLicensePlateAndDateTimeUntilNow(final String licensePlate, final LocalDateTime dateTime) {
        final Map<String, Condition> keyConditions = new HashMap<>();
        keyConditions.put(
            LICENSE_PLATE.getValue(),
            Condition.builder().attributeValueList(
                AttributeValue.builder().s((licensePlate)).build())
                .comparisonOperator(ComparisonOperator.EQ).build()
        );
        keyConditions.put(
            CREATED_AT.getValue(),
            Condition.builder().attributeValueList(
                AttributeValue.builder().s((dateTime.toString())).build())
                .comparisonOperator(ComparisonOperator.GE).build()
        );
        queryRequest.keyConditions(keyConditions);
        return this;
    }

    public VehicleLocationsQueryRequestBuilder withLicensePlate(final String licensePlate) {
        final Map<String, Condition> keyConditions = new HashMap<>();
        keyConditions.put(
            LICENSE_PLATE.getValue(),
            Condition.builder().attributeValueList(
                AttributeValue.builder().s((licensePlate)).build())
                .comparisonOperator(ComparisonOperator.EQ).build()
        );
        queryRequest.keyConditions(keyConditions);
        return this;
    }

    public QueryRequest build() {
        return Optional.ofNullable(LOCATIONS_TABLE.getValue())
            .filter(StringUtils::isNotBlank)
            .map(queryRequest::tableName)
            .map(SdkBuilder::build)
            .orElseThrow(IllegalArgumentException::new);
    }
}
