package com.locationsapi.interfaces.adapter.amqp.listener.locationevent;

import com.locationsapi.entity.Event;
import com.locationsapi.interfaces.adapter.amqp.listener.locationevent.dto.VehicleLocationDTO;
import com.locationsapi.interfaces.adapter.repository.provider.LocationsDatabaseProvider;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class LocationCreationEventListener {

    private final LocationsDatabaseProvider locationsDatabaseProvider;

    @SqsListener("${aws.sqs.creation-vehicle-location-queue.name}")
    void execute(final @Valid Event<VehicleLocationDTO> event) {
        locationsDatabaseProvider.save(event.getContent().toEntity());
    }
}
