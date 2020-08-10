package com.locationsapi.interfaces.adapter.repository.dynamodb;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.locationsapi.entity.VehicleLocationEntity;
import com.locationsapi.interfaces.adapter.repository.LocationsRepository;
import com.locationsapi.interfaces.adapter.repository.dynamodb.converter.VehicleLocationsResponseConverter;
import com.locationsapi.interfaces.adapter.repository.dynamodb.fixture.VehicleLocationsDynamoDbUnitTestFixture;
import java.time.LocalDateTime;
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
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VehicleLocationsRepositoryDynamoDbUnitTest {

  @Mock
  private DynamoDbClient dynamoDbClient;

  private final VehicleLocationsResponseConverter vehicleLocationsResponseConverter = new VehicleLocationsResponseConverter();

  private LocationsRepository locationsRepository;

  @BeforeAll
  public void setUp() {
    initMocks(this);
    locationsRepository =
        new VehicleLocationsRepositoryDynamoDb(
          dynamoDbClient, vehicleLocationsResponseConverter
        );
  }

  @Test
  @DisplayName("Should find all vehicle locations based on license plate")
  void shouldFindAllVehicleLocationsBasedOnLicensePlate() {
    final String validLicensePlate = "ETR5467";
    final QueryRequest queryRequest =
        VehicleLocationsDynamoDbUnitTestFixture.validQueryRequestWithLicensePlate(validLicensePlate);
    final QueryResponse queryResponse =
        VehicleLocationsDynamoDbUnitTestFixture.responseWithItem();
    when(dynamoDbClient.query(queryRequest)).thenReturn(queryResponse);
    final List<VehicleLocationEntity> locations =
        locationsRepository.findAllVehicleLocationsByLicensePlate(validLicensePlate);
    final ArgumentCaptor<QueryRequest> captured = ArgumentCaptor.forClass(QueryRequest.class);
    verify(dynamoDbClient, times(1)).query(captured.capture());
    Assertions.assertThat(locations.get(0).getLicensePlate()).isEqualTo(validLicensePlate);
    Assertions.assertThat(captured.getValue()).isEqualTo(queryRequest);
  }

  @Test
  @DisplayName("Should find all vehicle locations based on license plate and local date time")
  void shouldFindAllVehicleLocationsBasedOnLicensePlateAndLocalDateTime() {
    final String validLicensePlate = "ETR5467";
    final LocalDateTime localDateTime = LocalDateTime.of(2020,8,3,21,33,33);
    final QueryRequest queryRequest =
        VehicleLocationsDynamoDbUnitTestFixture
            .validQueryRequestWithLicensePlateAndLocalDateTime(validLicensePlate, localDateTime);
    final QueryResponse queryResponse =
        VehicleLocationsDynamoDbUnitTestFixture.responseWithItems();
    when(dynamoDbClient.query(queryRequest)).thenReturn(queryResponse);
    final List<VehicleLocationEntity> locations =
        locationsRepository.findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(
            validLicensePlate, localDateTime
        );
    Assertions.assertThat(locations.get(0).getLicensePlate()).isEqualTo(validLicensePlate);
    Assertions.assertThat(locations.size()).isEqualTo(4);
  }
}
