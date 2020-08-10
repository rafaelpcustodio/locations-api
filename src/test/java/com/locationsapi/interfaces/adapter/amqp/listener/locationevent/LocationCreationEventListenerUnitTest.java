package com.locationsapi.interfaces.adapter.amqp.listener.locationsevent;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.locationsservice.entity.Event;
import com.locationsservice.entity.VehicleLocationEntity;
import com.locationsservice.interfaces.adapter.amqp.listener.locationevent.dto.VehicleLocationDTO;
import com.locationsservice.interfaces.adapter.amqp.listener.locationevent.fixture.LocationCreationEventListenerUnitTestFixture;
import com.locationsservice.interfaces.adapter.repository.provider.LocationsDatabaseProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Create location event listener unit tests.")
@ExtendWith(MockitoExtension.class)
public class LocationCreationEventListenerUnitTest {

  private LocationCreationEventListener locationCreationEventListener;

  @Mock
  private LocationsDatabaseProvider locationsDatabaseProvider;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    locationCreationEventListener = new LocationCreationEventListener(
        locationsDatabaseProvider
    );
  }

  @Test
  @DisplayName("Should receive location creation event successfully")
  public void shouldReceiveLocationCreationEventSuccessfully() {

    final String licensePlate = "EET4787";
    final Float expectedLatitude = 41.4092F;
    final Float expectedLongitude = 2.17396F;

    final VehicleLocationDTO vehicleLocationDTO =
        LocationCreationEventListenerUnitTestFixture.validVehicleDTO(
            licensePlate,
            expectedLatitude,
            expectedLongitude
        );
    locationCreationEventListener.execute(new Event<>(vehicleLocationDTO));

    final ArgumentCaptor<VehicleLocationEntity> argument =
        ArgumentCaptor.forClass(VehicleLocationEntity.class);
    verify(locationsDatabaseProvider, times(1))
        .save(argument.capture());

    Assertions.assertEquals(expectedLatitude, argument.getValue().getLatitude());
    Assertions.assertEquals(expectedLongitude, argument.getValue().getLongitude());
    Assertions.assertEquals(licensePlate, argument.getValue().getLicensePlate());
  }

}
