package com.locationsapi.interfaces.adapter.repository.provider;

import com.locationsapi.entity.EstablishmentEntity;
import com.locationsapi.interfaces.adapter.repository.EstablishmentsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstablishmentsDatabaseProvider {

  private final EstablishmentsRepository establishmentsRepository;

  public List<EstablishmentEntity> findAll() {
    return establishmentsRepository.findAll();
  }
}
