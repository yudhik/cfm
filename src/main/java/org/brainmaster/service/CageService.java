package org.brainmaster.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.brainmaster.dto.CageLogProjection;
import org.brainmaster.entity.Cage;
import org.brainmaster.entity.CageLog;
import org.jspecify.annotations.NonNull;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CageService {

  private static final String QUERY_CAGE_NAME_STATEMENT = "FROM Cage c where c.name = ?1";

  private static final String QUERY_PROJECTION_NUMBER_OF_WEEK_QUERY =
      """
          SELECT id, cageId, createdDate, observedPopulation, deadCount, killedCount, population, eggCount, weightCount, eggPopulationRatio, eggWeightRatio, feedIntake, feedCount, feedConvertionRatio,
          WEEK(:end_date) - WEEK(createdDate) AS weeksBetween
          FROM CageLog c where c.cage_id = :id
          """;

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
        .orElseThrow(() -> new IllegalArgumentException(String.format("unable to find cage with name %s", name))));
  }

  @Transactional
  public List<Cage> getCages(Integer pageIndex, Integer pageSize) {
    return Cage.findAll().page(pageIndex, pageSize).list();
  }

  @Transactional
  public List<@NonNull CageLogProjection> getLogs(UUID cageId, Integer numberOfWeek) {
    return CageLog
        .find(QUERY_PROJECTION_NUMBER_OF_WEEK_QUERY, Parameters.with("id", cageId).and("end_date", LocalDateTime.now()))
        .project(CageLogProjection.class).page(0, 7).list();
    // return CageLog.find("FROM CageLog c where c.cage.id = ?1 and c.createdDate >= ?2", cageId,
    // LocalDateTime.now().minusDays(numberOfWeek)).list();
  }


}
