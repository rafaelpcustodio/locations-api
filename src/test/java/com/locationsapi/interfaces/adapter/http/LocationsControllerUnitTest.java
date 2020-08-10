package com.locationsapi.interfaces.adapter.http;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.LocationDTO;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.LocationsResponseDTO;
import com.locationsapi.interfaces.adapter.http.fixture.LocationsControllerUnitTestFixture;
import com.locationsapi.interfaces.adapter.http.handler.error.ControllerExceptionHandler;
import com.locationsapi.interfaces.adapter.http.handler.error.LocationsController;
import com.locationsapi.interfaces.adapter.serialization.JacksonConfiguration;
import com.locationsapi.usecases.GetLocations;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
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
  private GetLocations getLocations;

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
}
