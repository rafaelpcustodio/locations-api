package com.locationsapi.interfaces.adapter.http.fixture;

import com.locationsapi.interfaces.adapter.http.dto.response.establishments.EstablishmentDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstablishmentsControllerUnitTestFixture {

  public static EstablishmentDTO validEstablishment(final String name) {
    return EstablishmentDTO.builder()
        .id(UUID.randomUUID())
        .name(name)
        .build();
  }

  public static List<EstablishmentDTO> validEstablishmentsList() {
    List<EstablishmentDTO> establishments = new ArrayList<>();
    establishments.add(validEstablishment( "Restaurants"));
    establishments.add(validEstablishment( "Hotels"));
    establishments.add(validEstablishment("Gas Stations"));
    return establishments;
  }
}
