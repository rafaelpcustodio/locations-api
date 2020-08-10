package com.locationsapi.interfaces.adapter.repository.provider.fixture;

import com.locationsapi.entity.EstablishmentEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstablishmentsDatabaseProviderUnitTestFixture {

  public static EstablishmentEntity validEstablishment(final String name) {
    return EstablishmentEntity.builder()
        .id(UUID.randomUUID())
        .name(name)
        .build();
  }

  public static List<EstablishmentEntity> validEstablishmentsList() {
    List<EstablishmentEntity> establishments = new ArrayList<>();
    establishments.add(validEstablishment( "Restaurants"));
    establishments.add(validEstablishment( "Hotels"));
    establishments.add(validEstablishment("Gas Stations"));
    return establishments;
  }
}
