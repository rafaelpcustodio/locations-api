package com.locationsapi.usecases;

import static java.util.stream.Collectors.toList;

import com.locationsapi.entity.EstablishmentEntity;
import com.locationsapi.interfaces.adapter.http.dto.response.establishments.EstablishmentDTO;
import com.locationsapi.interfaces.adapter.repository.provider.EstablishmentsDatabaseProvider;
import com.locationsapi.usecases.exceptions.EstablishmentsNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetEstablishments {

  private final EstablishmentsDatabaseProvider establishmentsDatabaseProvider;

  public List<EstablishmentDTO> execute() {
    final List<EstablishmentEntity> establishmentsEntity = establishmentsDatabaseProvider.findAll();
    hasNoEstablishments(establishmentsEntity);
    return establishmentsEntity
        .stream()
        .map(EstablishmentDTO::new)
        .collect(toList());
  }

  private void hasNoEstablishments(final List<EstablishmentEntity> establishments) {
    if(establishments.isEmpty()) {
      throw new EstablishmentsNotFoundException();
    }
  }
}
