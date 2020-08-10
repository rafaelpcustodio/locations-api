package com.locationsapi.usecases;

import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.locationsapi.entity.Event;
import com.locationsapi.interfaces.adapter.amqp.listener.locationevent.dto.VehicleLocationDTO;
import org.springframework.stereotype.Service;

@Service
public class CreateLocation {
  public void execute(final VehicleLocationDTO vehicleLocationDTO) throws JsonProcessingException {
    return;
  }
}
