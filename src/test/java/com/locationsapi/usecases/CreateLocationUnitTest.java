package com.locationsapi.usecases;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.locationsapi.interfaces.adapter.amqp.listener.locationevent.dto.VehicleLocationDTO;
import com.locationsapi.interfaces.adapter.serialization.JacksonConfiguration;
import com.locationsapi.usecases.fixture.CreateLocationUnitTestFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayName("Create location unit tests")
@TestInstance(Lifecycle.PER_CLASS)
public class CreateLocationUnitTest {

  private CreateLocation createLocation;

  private static final String QUEUE_URL = "http://locahost:4576/queue/creation-vehicle-location-queue";

  @Mock
  private AmazonSQS amazonSQS;

  @BeforeAll
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    createLocation = new CreateLocation(amazonSQS, QUEUE_URL, new JacksonConfiguration().objectMapper());
  }

  @Test
  @DisplayName("Should send location to the queue")
  public void shouldSendLocationToTheQueue() throws JsonProcessingException {

    final String licensePlate = "EE4T787";
    final Float expectedLatitude = 41.4092F;
    final Float expectedLongitude = 2.17396F;

    final VehicleLocationDTO vehicleDTO =
        CreateLocationUnitTestFixture
            .validVehicleLocation(licensePlate,expectedLatitude,expectedLongitude);

    createLocation.execute(vehicleDTO);

    final ArgumentCaptor<SendMessageRequest> argument =
        ArgumentCaptor.forClass(SendMessageRequest.class);
    verify(amazonSQS, times(1))
        .sendMessage(argument.capture());

    Assertions.assertEquals(QUEUE_URL, argument.getValue().getQueueUrl());
  }

}
