package com.locationsapi.usecases;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationsapi.entity.Event;
import com.locationsapi.interfaces.adapter.amqp.listener.locationevent.dto.VehicleLocationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateLocation {

  private final AmazonSQS sqs;
  private final String queueUrl;
  private final ObjectMapper mapper;

  public CreateLocation(
      final AmazonSQS sqs,
      @Value("${aws.sqs.creation-vehicle-location-queue.url}") final String queueUrl,
      final ObjectMapper mapper) {
    this.sqs = sqs;
    this.queueUrl = queueUrl;
    this.mapper = mapper;
  }

  public void execute(final VehicleLocationDTO vehicleLocationDTO) throws JsonProcessingException {
    final Event<VehicleLocationDTO> event = new Event<>(vehicleLocationDTO);
    final SendMessageRequest message = new SendMessageRequest()
        .withQueueUrl(queueUrl)
        .withMessageBody(mapper.writeValueAsString(event));
    sqs.sendMessage(message);
  }
}
