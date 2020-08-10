package com.locationsapi.interfaces.adapter.repository.provider;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.locationsapi.entity.VehicleLocationEntity;
import com.locationsapi.interfaces.adapter.repository.LocationsRepository;
import com.locationsapi.interfaces.adapter.repository.provider.fixture.LocationsDatabaseProviderUnitTestFixture;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Locations provider unit tests.")
public class LocationsDatabaseProviderUnitTest {

  @Mock
  private LocationsRepository locationsRepository;

  @InjectMocks
  private LocationsDatabaseProvider locationsDatabaseProvider;

  @Test
  @DisplayName("Should find all vehicle locations with license plate and local date time until now")
  public void shouldFindAllVehicleLocationsWithLicensePlateFromLocalDateTimeUntilNow() {
    final String licensePlate = "EET4787";
    final LocalDateTime dateTime = LocalDateTime.of(2090,8,3,20,24,22);
    final List<VehicleLocationEntity> locationsVehicle =
        LocationsDatabaseProviderUnitTestFixture.validLocationsList();
    when(locationsRepository
        .findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(licensePlate, dateTime))
        .thenReturn(locationsVehicle);
    List<VehicleLocationEntity> actualList = locationsDatabaseProvider
        .findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(licensePlate, dateTime);
    Assertions.assertEquals(9, actualList.size());
  }

  @Test
  @DisplayName("Should find all vehicle locations with license plate")
  public void shouldFindAllVehicleLocationsWithLicensePlate() {
    final String licensePlate = "EET4787";
    final List<VehicleLocationEntity> locationsVehicle =
        LocationsDatabaseProviderUnitTestFixture.validLocationsList();
    when(locationsRepository
        .findAllVehicleLocationsByLicensePlate(licensePlate))
        .thenReturn(locationsVehicle);
    List<VehicleLocationEntity> actualList = locationsDatabaseProvider
        .findAllVehicleLocationsByLicensePlate(licensePlate);
    Assertions.assertEquals(9, actualList.size());
  }

  @Test
  @DisplayName("Should save vehicle location")
  public void shouldSaveVehicleLocation() {
    final String licensePlate = "EET4787";
    final Float expectedLatitude = 41.4092F;
    final Float expectedLongitude = 2.17396F;
    final VehicleLocationEntity vehicleLocationEntity =
        LocationsDatabaseProviderUnitTestFixture.validLocation(
            licensePlate, expectedLatitude, expectedLongitude,LocalDateTime.now()
        );

    doNothing().when(locationsRepository).save(vehicleLocationEntity);
    locationsDatabaseProvider.save(vehicleLocationEntity);
    verify(locationsRepository, times(1))
        .save(vehicleLocationEntity);
  }
}
