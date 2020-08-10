package com.locationsapi.interfaces.adapter.repository.dynamodb;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.locationsapi.entity.EstablishmentEntity;
import com.locationsapi.interfaces.adapter.repository.dynamodb.converter.EstablishmentsResponseConverter;
import com.locationsapi.interfaces.adapter.repository.dynamodb.fixture.EstablishmentsRepositoryDynamoDbUnitTestFixture;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EstablishmentsRepositoryDynamoDbUnitTests {

  @Mock
  private DynamoDbClient dynamoDbClient;

  @Mock
  private EstablishmentsRepositoryDynamoDb establishmentsRepository;

  private final EstablishmentsResponseConverter establishmentsResponseConverter = new EstablishmentsResponseConverter();

  @BeforeAll
  public void setUp() {
    initMocks(this);
    establishmentsRepository = new EstablishmentsRepositoryDynamoDb(dynamoDbClient, establishmentsResponseConverter);
  }

  @Test
  @DisplayName("Should find all establishments")
  void shouldFindAllEstablishmentsSuccessfully() {
    final String establishmentName = "Restaurants";
    final ScanRequest scanRequest =
        EstablishmentsRepositoryDynamoDbUnitTestFixture.scanRequest();
    final ScanResponse scanResponse =
        EstablishmentsRepositoryDynamoDbUnitTestFixture.response();
    when(dynamoDbClient.scan(scanRequest)).thenReturn(scanResponse);
    final List<EstablishmentEntity> establishmentEntities = establishmentsRepository.findAll();
    final ArgumentCaptor<ScanRequest> captured = ArgumentCaptor.forClass(ScanRequest.class);
    verify(dynamoDbClient, only()).scan(captured.capture());
    Assertions.assertThat(establishmentEntities.get(0).getName()).isEqualTo(establishmentName);
    Assertions.assertThat(establishmentEntities.size()).isEqualTo(1);
    Assertions.assertThat(captured.getValue()).isEqualTo(scanRequest);
  }
}
