package org.brainmaster.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CageLogProjection {
  private final UUID id;
  private final UUID cageId;
  private final LocalDateTime createdDate;
  private final Integer observedPopulation;
  private final Integer deadCount;
  private final Integer killedCount;
  private final Integer eggCount;
  private final BigDecimal weightCount;
  private final Integer feedIntake;
  private final BigDecimal eggPopulationRatio;
  private final BigDecimal eggWeightRatio;
  private final BigDecimal feedConvertionRatio;
  private final Integer feedCount;
  private final Integer population;
  private final Long weeksBetween;

  public CageLogProjection(UUID id, UUID cageId, LocalDateTime createdDate, Integer observedPopulation,
      Integer deadCount, Integer killedCount, Integer eggCount, BigDecimal weightCount, Integer feedIntake,
      BigDecimal eggPopulationRatio, BigDecimal eggWeightRatio, BigDecimal feedConvertionRatio, Integer feedCount,
      Integer population, Long weeksBetween) {
    this.id = id;
    this.cageId = cageId;
    this.createdDate = createdDate;
    this.observedPopulation = observedPopulation;
    this.deadCount = deadCount;
    this.killedCount = killedCount;
    this.eggCount = eggCount;
    this.weightCount = weightCount;
    this.feedIntake = feedIntake;
    this.eggPopulationRatio = eggPopulationRatio;
    this.eggWeightRatio = eggWeightRatio;
    this.feedConvertionRatio = feedConvertionRatio;
    this.feedCount = feedCount;
    this.population = population;
    this.weeksBetween = weeksBetween;
  }

  public UUID getCageId() {
    return cageId;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public Integer getDeadCount() {
    return deadCount;
  }

  public Integer getEggCount() {
    return eggCount;
  }

  public BigDecimal getEggPopulationRatio() {
    return eggPopulationRatio;
  }

  public BigDecimal getEggWeightRatio() {
    return eggWeightRatio;
  }

  public BigDecimal getFeedConvertionRatio() {
    return feedConvertionRatio;
  }

  public Integer getFeedCount() {
    return feedCount;
  }

  public Integer getFeedIntake() {
    return feedIntake;
  }

  public UUID getId() {
    return id;
  }

  public Integer getKilledCount() {
    return killedCount;
  }

  public Integer getObservedPopulation() {
    return observedPopulation;
  }

  public Integer getPopulation() {
    return population;
  }

  public Long getWeeksBetween() {
    return weeksBetween;
  }

  public BigDecimal getWeightCount() {
    return weightCount;
  }

}
