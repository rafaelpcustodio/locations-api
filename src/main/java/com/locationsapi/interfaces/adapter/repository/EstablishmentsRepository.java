package com.locationsapi.interfaces.adapter.repository;

import com.locationsapi.entity.EstablishmentEntity;
import java.util.List;

public interface EstablishmentsRepository {

  List<EstablishmentEntity> findAll();
}
