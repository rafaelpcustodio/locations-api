package com.locationsapi.usecases;

import com.locationsapi.interfaces.adapter.http.dto.request.locations.minimumdistance.MinimumDistanceLocationDTO;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.MinimumDistanceResponseDTO;
import com.locationsapi.usecases.fixture.FindMinimumDistanceUnitTestFixture;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockitoAnnotations;

@DisplayName("Find minimum distance unit tests")
@TestInstance(Lifecycle.PER_CLASS)
public class FindMinimumDistanceUnitTest {

  private FindMinimumDistance findMinimumDistance;

  @BeforeAll
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    findMinimumDistance = new FindMinimumDistance();
  }

  @Test
  @DisplayName("Should return distance from valid inputs.")
  void shouldReturnDistanceFromValidInputs() {
    final Double expectedDistance = 103.07452392578125;
    final Float expectedLatitude = 41.40193F;
    final Float expectedLongitude = 2.17404F;
    final MinimumDistanceLocationDTO locationToCompare = MinimumDistanceLocationDTO.builder()
        .latitude(41.40286F)
        .longitude(2.17404F)
        .build();
    final List<MinimumDistanceLocationDTO> locations = FindMinimumDistanceUnitTestFixture.validLocations();
    final MinimumDistanceResponseDTO actual =
        findMinimumDistance.execute(locationToCompare, locations);
    Assertions.assertEquals(expectedDistance, actual.getMinimumDistance());
    Assertions.assertEquals(expectedLatitude, actual.getLocation().getLatitude());
    Assertions.assertEquals(expectedLongitude, actual.getLocation().getLongitude());
  }
}
