package com.locationsapi.interfaces.adapter.repository.dynamodb;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LATITUDE;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LICENSE_PLATE;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.VehicleLocationsDynamoDbDefinitions.LONGITUDE;


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
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;
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

  @Test
  void shouldSave() {
    final String licensePlate = "EET4787";
    final Float expectedLatitude = 41.4092F;
    final Float expectedLongitude = 2.17396F;
    final VehicleLocationEntity vehicleLocationEntity =
        VehicleLocationsDynamoDbUnitTestFixture
            .validVehicleLocation(licensePlate, expectedLatitude, expectedLongitude);

    final PutItemRequest putItemRequest =
        VehicleLocationsDynamoDbUnitTestFixture
            .putItemRequestFromEntity(vehicleLocationEntity);

    final PutItemResponse itemResponse = (PutItemResponse) VehicleLocationsDynamoDbUnitTestFixture
        .responseWithSuccess();

    locationsRepository.save(vehicleLocationEntity);

    final ArgumentCaptor<PutItemRequest> captured =
        ArgumentCaptor.forClass(PutItemRequest.class);
    when(dynamoDbClient.putItem(putItemRequest)).thenReturn(itemResponse);
    verify(dynamoDbClient, times(1)).putItem(captured.capture());
    Assertions.assertThat(captured.getValue().item().get(LICENSE_PLATE.getValue()))
        .isEqualTo(putItemRequest.item().get(LICENSE_PLATE.getValue()));
    Assertions.assertThat(captured.getValue().item().get(LATITUDE.getValue()))
        .isEqualTo(putItemRequest.item().get(LATITUDE.getValue()));
    Assertions.assertThat(captured.getValue().item().get(LONGITUDE.getValue()))
        .isEqualTo(putItemRequest.item().get(LONGITUDE.getValue()));
  }
}
