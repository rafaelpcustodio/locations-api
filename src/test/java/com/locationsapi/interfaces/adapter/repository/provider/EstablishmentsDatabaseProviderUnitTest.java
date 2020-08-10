package com.locationsapi.interfaces.adapter.repository.provider;

import static org.mockito.Mockito.when;

import com.locationsapi.entity.EstablishmentEntity;
import com.locationsapi.interfaces.adapter.repository.EstablishmentsRepository;
import com.locationsapi.interfaces.adapter.repository.provider.fixture.EstablishmentsDatabaseProviderUnitTestFixture;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Establishments provider unit tests.")
public class EstablishmentsDatabaseProviderUnitTest {

  @Mock
  private EstablishmentsRepository establishmentsRepository;

  @InjectMocks
  private EstablishmentsDatabaseProvider establishmentsDatabaseProvider;

  @Test
  @DisplayName("Should find all establishments")
  public void shouldFindAllEstablishments() {
    final List<EstablishmentEntity> establishments =
        EstablishmentsDatabaseProviderUnitTestFixture.validEstablishmentsList();
    when(establishmentsRepository.findAll())
        .thenReturn(establishments);
    List<EstablishmentEntity> actualList = establishmentsDatabaseProvider.findAll();
    Assertions.assertEquals(establishments.size(), actualList.size());
  }
}
