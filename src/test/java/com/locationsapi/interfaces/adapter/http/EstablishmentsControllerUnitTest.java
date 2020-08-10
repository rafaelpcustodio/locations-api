package com.locationsapi.interfaces.adapter.http;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationsapi.interfaces.adapter.http.dto.response.establishments.EstablishmentDTO;
import com.locationsapi.interfaces.adapter.http.dto.response.establishments.EstablishmentsResponseDTO;
import com.locationsapi.interfaces.adapter.http.fixture.EstablishmentsControllerUnitTestFixture;
import com.locationsapi.interfaces.adapter.http.handler.ControllerExceptionHandler;
import com.locationsapi.interfaces.adapter.serialization.JacksonConfiguration;
import com.locationsapi.usecases.GetEstablishments;
import com.locationsapi.usecases.exceptions.EstablishmentsNotFoundException;
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
public class EstablishmentsControllerUnitTest {

  private static final String PATH_ESTABLISHMENTS = "/api/v1/establishments";

  @InjectMocks
  private EstablishmentsController establishmentsController;

  @Mock
  private GetEstablishments getEstablishments;

  private MockMvc mockMvc;

  private final ObjectMapper objectMapper = new JacksonConfiguration().objectMapper();

  @BeforeAll
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(establishmentsController)
        .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
        .setControllerAdvice(new ControllerExceptionHandler())
        .setValidator(new LocalValidatorFactoryBean())
        .build();
  }

  @AfterEach
  public void tearDown() {
    Mockito.reset(getEstablishments);
  }

  @Test
  @DisplayName("Should get all establishments successfully")
  public void shouldGetAllEstablishmentsSuccessfully() throws Exception {

    final List<EstablishmentDTO> expectedList =
        EstablishmentsControllerUnitTestFixture.validEstablishmentsList();

    final EstablishmentsResponseDTO response = EstablishmentsResponseDTO.builder()
        .establishments(expectedList).build();

    when(getEstablishments.execute()).thenReturn(expectedList);
    mockMvc.perform(get(PATH_ESTABLISHMENTS))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(response)));
  }

  @Test
  @DisplayName("Should not find establishments")
  public void shouldProduceLocationsException() throws Exception {
    when(getEstablishments.execute()).thenThrow(new EstablishmentsNotFoundException());
    mockMvc.perform(get(PATH_ESTABLISHMENTS)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
