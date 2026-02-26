package org.brainmaster.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.brainmaster.entity.Cage;
import org.brainmaster.entity.CageLog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CageService {

  private static final String QUERY_CAGE_NAME_STATEMENT = "FROM Cage c where c.name = ?1";

  @Transactional
  public CageLog appendLog(CageLog cageLog) {
    cageLog.persist();
    return CageLog.findById(cageLog.getId());
  }

  @Transactional
  public long countCageDetails() {
    return CageLog.count();
  }

  @Transactional
  public long countCages() {
    return Cage.count();
  }

  @Transactional
  public void createCage(Cage cage) {
    cage.persist();
  }

  @Transactional
  public Optional<Cage> findByName(String name) {
    return Optional.of((Cage) Cage.find(QUERY_CAGE_NAME_STATEMENT, name).singleResultOptional()
        .orElseThrow(() -> new IllegalArgumentException(
            String.format("unable to find cage with name %s", name))));
  }

  @Transactional
  public List<Cage> getCages(Integer pageIndex, Integer pageSize) {
    return Cage.findAll().page(pageIndex, pageSize).list();
  }

  @Transactional
  public List<CageLog> getLogs(UUID cageId, Integer numberOfWeek) {
    // TODO: calculate using number of weeks from Cage initialized
    return CageLog.find("FROM CageLog c where c.cage.id = ?1 and c.createdDate >= ?2", cageId,
        LocalDateTime.now().minusDays(numberOfWeek)).list();
  }


}
