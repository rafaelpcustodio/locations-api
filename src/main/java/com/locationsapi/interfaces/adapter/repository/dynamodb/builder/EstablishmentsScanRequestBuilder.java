package com.locationsapi.interfaces.adapter.repository.dynamodb.builder;

import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.EstablishmentsDynamoDbDefinitions.ESTABLISHMENTS_TABLE;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest.Builder;
import software.amazon.awssdk.utils.StringUtils;
import software.amazon.awssdk.utils.builder.SdkBuilder;

@RequiredArgsConstructor
public class EstablishmentsScanRequestBuilder {

  private final Builder scanRequest;

  public static EstablishmentsScanRequestBuilder builder() {
    return new EstablishmentsScanRequestBuilder(ScanRequest.builder());
  }

  public ScanRequest build() {
    return Optional.ofNullable(ESTABLISHMENTS_TABLE.getValue())
        .filter(StringUtils::isNotBlank)
        .map(scanRequest::tableName)
        .map(SdkBuilder::build)
        .orElseThrow(IllegalArgumentException::new);
  }
}
