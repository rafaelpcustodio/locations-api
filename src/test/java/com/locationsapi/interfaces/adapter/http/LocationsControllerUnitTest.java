package com.locationsapi.interfaces.adapter.http;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationsapi.interfaces.adapter.amqp.listener.locationevent.dto.VehicleLocationDTO;
import com.locationsapi.interfaces.adapter.http.dto.request.locations.LocationRequestDTO;
import com.locationsapi.interfaces.adapter.http.dto.request.locations.minimumdistance.MinimumDistanceLocationDTO;
import com.locationsapi.interfaces.adapter.http.dto.request.locations.minimumdistance.MinimumDistanceRequestDTO;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.LocationDTO;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.LocationsResponseDTO;
import com.locationsapi.interfaces.adapter.http.fixture.LocationsControllerUnitTestFixture;
import com.locationsapi.interfaces.adapter.http.handler.ControllerExceptionHandler;
import com.locationsapi.interfaces.adapter.http.handler.error.Error;
import com.locationsapi.interfaces.adapter.serialization.JacksonConfiguration;
import com.locationsapi.usecases.CreateLocation;
import com.locationsapi.usecases.FindMinimumDistance;
import com.locationsapi.usecases.GetLocations;
import com.locationsapi.usecases.exceptions.LocationsNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocationsControllerUnitTest {

  private static final String PATH_LOCATIONS = "/api/v1/locations";

  private static final String VALID_LICENSE_PLATE = "EET4787";

  @InjectMocks
  private LocationsController locationsController;

  @Mock
  private CreateLocation createLocation;

  @Mock
  private GetLocations getLocations;

  @Mock
  private FindMinimumDistance findMinimumDistance;

  private MockMvc mockMvc;

  private final ObjectMapper objectMapper = new JacksonConfiguration().objectMapper();

  @BeforeAll
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(locationsController)
        .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
        .setControllerAdvice(new ControllerExceptionHandler())
        .setValidator(new LocalValidatorFactoryBean())
        .build();
  }

  @AfterEach
  public void tearDown() {
    Mockito.reset(getLocations);
  }

  @Test
  @DisplayName("Should get vehicle locations info  with license plate successfully")
  public void shouldGetVehicleLocationsInfoSuccessfully() throws Exception {

    final List<LocationDTO> expectedList = LocationsControllerUnitTestFixture.validLocationsList();

    final LocationsResponseDTO response =
        new LocationsResponseDTO(VALID_LICENSE_PLATE, expectedList);

    when(getLocations.execute(VALID_LICENSE_PLATE, null)).thenReturn(expectedList);

    mockMvc.perform(get(PATH_LOCATIONS + "/" + VALID_LICENSE_PLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(response)));
  }

  @Test
  @DisplayName("Should get vehicle locations info  with license plate and date time successfully")
  public void shouldGetVehicleLocationsAndDateTimeInfoSuccessfully() throws Exception {

    final LocalDateTime dateTime = LocalDateTime.of(2020,8,1,12,12,12);
    final List<LocationDTO> expectedList = LocationsControllerUnitTestFixture.validLocationsList();

    final LocationsResponseDTO response =
        new LocationsResponseDTO(VALID_LICENSE_PLATE, expectedList);

    when(getLocations.execute(VALID_LICENSE_PLATE, dateTime)).thenReturn(expectedList);

    mockMvc.perform(get(PATH_LOCATIONS + "/" + VALID_LICENSE_PLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .queryParam("dateTime", dateTime.toString()))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(response)));
  }

  @Test
  @DisplayName("Should get minimum distance between a location and a list of locations")
  public void shouldGetMinimumDistanceSuccessfully() throws Exception {
    final Float expectedLatitude = 41.40286F;
    final Float expectedLongitude = 2.17404F;

    final List<MinimumDistanceLocationDTO> locations =
        LocationsControllerUnitTestFixture.validLocationsDTO();
    final MinimumDistanceLocationDTO locationToBeEvaluated =
        LocationsControllerUnitTestFixture
            .validMinimumDistanceLocationDTO(expectedLatitude, expectedLongitude);
    final MinimumDistanceRequestDTO requestMinimumDistanceDTO =
        MinimumDistanceRequestDTO.builder()
            .currentLocation(locationToBeEvaluated)
            .locationsPlaces(locations)
            .build();
    mockMvc.perform(post(PATH_LOCATIONS + "/minimum-distance")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestMinimumDistanceDTO)))
        .andExpect(status().isOk());

    verify(findMinimumDistance, times(1))
        .execute(any(), any());
  }

  @Test
  @DisplayName("Should accept vehicle location info successfully")
  public void shouldCreateVehicleLocationInfoSuccessfully() throws Exception {

    final Float expectedLatitude = 41.4092F;
    final Float expectedLongitude = 2.17396F;

    LocationRequestDTO locationRequestDTO =
        LocationsControllerUnitTestFixture
            .validRequestLocationDTO(VALID_LICENSE_PLATE, expectedLatitude, expectedLongitude);

    doNothing().when(createLocation)
        .execute(any());

    mockMvc.perform(post(PATH_LOCATIONS)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(locationRequestDTO)))
        .andExpect(status().isAccepted());

    final ArgumentCaptor<VehicleLocationDTO> argument =
        ArgumentCaptor.forClass(VehicleLocationDTO.class);
    verify(createLocation, times(1))
        .execute(argument.capture());

    Assertions.assertEquals(expectedLatitude, argument.getValue().getLatitude());
    Assertions.assertEquals(expectedLongitude, argument.getValue().getLongitude());
    Assertions.assertEquals(VALID_LICENSE_PLATE, argument.getValue().getLicensePlate());
  }

  @Test
  @DisplayName("Should not accept vehicle geo location info due to not license plate regex format")
  public void shouldNotCreateVehicleLocationInfoErrorDueToWrongLicensePlate() throws Exception {
    final String wrongFormatLicensePlate = "E431TYUY";
    final Float expectedLatitude = 41.4092F;
    final Float expectedLongitude = 2.17396F;

    LocationRequestDTO locationRequestDTO =
        LocationsControllerUnitTestFixture.validRequestLocationDTO(wrongFormatLicensePlate, expectedLatitude, expectedLongitude);

    mockMvc.perform(post(PATH_LOCATIONS)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(locationRequestDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Should not find locations")
  public void shouldNotFindLocationsException() throws Exception {
    final String wrongFormatLicensePlate = "E431TYUY";

    when(getLocations.execute(any(), any())).thenThrow(new LocationsNotFoundException());

    mockMvc.perform(get(PATH_LOCATIONS + "/" + wrongFormatLicensePlate)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
