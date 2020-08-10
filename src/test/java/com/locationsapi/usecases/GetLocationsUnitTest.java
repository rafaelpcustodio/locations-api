package com.locationsapi.usecases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.locationsapi.entity.VehicleLocationEntity;
import com.locationsapi.interfaces.adapter.repository.provider.LocationsDatabaseProvider;
import com.locationsapi.usecases.exceptions.LocationsNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Get locations unit tests")
@ExtendWith(MockitoExtension.class)
public class GetLocationsUnitTest {

  @InjectMocks
  private GetLocations getLocations;

  @Mock
  private LocationsDatabaseProvider locationsDatabaseProvider;

  @Test
  @DisplayName("Should return all locations from a valid license plate.")
  void shouldReturnAllLocationsFromLicensePlate() {

    final String licensePlate = "EET4787";

    final LocalDateTime dateTime = null;

    final List<VehicleLocationEntity> locations = new ArrayList<>();
    locations.add(VehicleLocationEntity.builder().licensePlate(licensePlate).build());
    when(locationsDatabaseProvider
        .findAllVehicleLocationsByLicensePlate(licensePlate)
    ).thenReturn(locations);

    getLocations.execute(licensePlate, dateTime);

    final ArgumentCaptor<String> argument =
        ArgumentCaptor.forClass(String.class);
    verify(locationsDatabaseProvider)
        .findAllVehicleLocationsByLicensePlate(argument.capture());

    Assertions.assertEquals(licensePlate, argument.getValue());
  }

  @Test
  @DisplayName("Should return all locations from a valid license plate and date time.")
  void shouldReturnAllLocationsFromLicensePlateAndDateTime() {

    final String licensePlate = "EET4787";

    final LocalDateTime dateTime = LocalDateTime.of(2020,8,1,12,12,12);

    final List<VehicleLocationEntity> locations = new ArrayList<>();
    locations.add(VehicleLocationEntity.builder().licensePlate(licensePlate).build());
    when(locationsDatabaseProvider
        .findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(licensePlate, dateTime)
    ).thenReturn(locations);

    getLocations.execute(licensePlate, dateTime);

    final ArgumentCaptor<String> argument =
        ArgumentCaptor.forClass(String.class);
    final ArgumentCaptor<LocalDateTime> argumentDateTime =
        ArgumentCaptor.forClass(LocalDateTime.class);
    verify(locationsDatabaseProvider)
        .findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(argument.capture(), argumentDateTime.capture());

    Assertions.assertEquals(licensePlate, argument.getValue());
    Assertions.assertEquals(dateTime, argumentDateTime.getValue());
  }

  @Test
  @DisplayName("Should not return all locations from a valid license plate.")
  void shouldNotReturnAllLocationsFromLicensePlate() {

    final String licensePlate = "EET4787";

    final LocalDateTime dateTime = null;

    when(locationsDatabaseProvider
        .findAllVehicleLocationsByLicensePlate(licensePlate)
    ).thenReturn(Collections.emptyList());

    assertThrows(LocationsNotFoundException.class, () ->
        getLocations.execute(licensePlate, dateTime));
  }

  @Test
  @DisplayName("Should not return all locations from a valid license plate.")
  void shouldNotReturnLocationsFromLicensePlateAndDateTime() {

    final String licensePlate = "EET4787";

    final LocalDateTime dateTime = LocalDateTime.of(2020,8,1,12,12,12);

    when(locationsDatabaseProvider
        .findAllVehicleLocationsByLicensePlateAndDateTimeUntilNow(licensePlate, dateTime)
    ).thenReturn(Collections.emptyList());

    assertThrows(LocationsNotFoundException.class, () ->
        getLocations.execute(licensePlate, dateTime));
  }
}
