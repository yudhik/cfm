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

  @Transactional
  public void createCage(Cage cage) {
    cage.persist();
  }

  @Transactional
  public List<Cage> getCages(Integer pageIndex, Integer pageSize) {
    return Cage.findAll().page(pageIndex, pageSize).list();
  }

  @Transactional
  public long countCages() {
    return Cage.count();
  }

  @Transactional
  public CageLog appendLog(CageLog cageLog) {
    cageLog.persist();
    return CageLog.findById(cageLog.getId());
  }

  @Transactional
  public Optional<Cage> findByName(String name) {
    return Optional.of((Cage) Cage.find("FROM Cage c where c.name = ?1", name)
        .singleResultOptional().orElseThrow(() -> new IllegalArgumentException(
            String.format("unable to find cage with name %s", name))));
  }

  @Transactional
  public List<CageLog> getLogs(UUID cageId, Integer numberOfWeek) {
    return CageLog.find("FROM CageLog c where c.cage.id = ?1 and c.createdDate >= ?2", cageId,
        LocalDateTime.now().minusDays(numberOfWeek)).list();
  }

}
