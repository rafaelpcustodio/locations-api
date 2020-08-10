package com.locationsapi.interfaces.adapter.repository.dynamodb;

import com.locationsapi.entity.EstablishmentEntity;
import com.locationsapi.interfaces.adapter.repository.EstablishmentsRepository;
import com.locationsapi.interfaces.adapter.repository.dynamodb.builder.EstablishmentsScanRequestBuilder;
import com.locationsapi.interfaces.adapter.repository.dynamodb.converter.RepositoryResponseConverter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

@Repository
@RequiredArgsConstructor
class EstablishmentsRepositoryDynamoDb implements EstablishmentsRepository {

  private final DynamoDbClient dynamoDbClient;
  private final RepositoryResponseConverter<EstablishmentEntity> establishmentsResponseConverter;

  @Override
  public List<EstablishmentEntity> findAll() {
    final ScanRequest scanRequest =
        EstablishmentsScanRequestBuilder.builder()
            .build();
    return this.dynamoDbClient.scan(scanRequest)
        .items()
        .stream()
        .map(establishmentsResponseConverter::fromEntry)
        .collect(Collectors.toList());
  }
}
