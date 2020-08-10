package com.locationsapi.interfaces.adapter.repository.dynamodb.converter;

import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.EstablishmentsDynamoDbDefinitions.ESTABLISHMENTS_ID;
import static com.locationsapi.interfaces.adapter.repository.dynamodb.definitions.EstablishmentsDynamoDbDefinitions.ESTABLISHMENTS_NAME;

import com.locationsapi.entity.EstablishmentEntity;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@Component
public class EstablishmentsResponseConverter implements RepositoryResponseConverter<EstablishmentEntity> {

    @Override
    public EstablishmentEntity fromEntry(final Map<String, AttributeValue> entry) {
        final String idFromDb = entry.get(ESTABLISHMENTS_ID.getValue()).s();
        final String nameFromDb = entry.get(ESTABLISHMENTS_NAME.getValue()).s();
        return EstablishmentEntity
                .builder()
                .id(UUID.fromString(idFromDb))
                .name(nameFromDb)
                .build();
    }
}
