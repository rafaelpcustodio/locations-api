package com.locationsapi.usecases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.locationsapi.entity.EstablishmentEntity;
import com.locationsapi.interfaces.adapter.repository.provider.EstablishmentsDatabaseProvider;
import com.locationsapi.usecases.exceptions.EstablishmentsNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Get establishments unit tests")
@ExtendWith(MockitoExtension.class)
public class GetEstablishmentsUnitTest {

  @InjectMocks
  private GetEstablishments getEstablishments;

  @Mock
  private EstablishmentsDatabaseProvider establishmentsDatabaseProvider;

  @Test
  @DisplayName("Should return all establishments.")
  void shouldReturnAllEstablishments() {
    final List<EstablishmentEntity> establishments = new ArrayList<>();
    establishments.add(EstablishmentEntity.builder().build());
    when(establishmentsDatabaseProvider.findAll()).thenReturn(establishments);
    getEstablishments.execute();
    verify(establishmentsDatabaseProvider, times(1))
        .findAll();
  }

  @Test
  @DisplayName("Should throw exception not found establishments.")
  void shouldNotReturnEstablishmentsException() {
    assertThrows(EstablishmentsNotFoundException.class, () ->
        getEstablishments.execute());
  }
}
