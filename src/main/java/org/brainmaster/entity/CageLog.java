package org.brainmaster.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import com.github.f4b6a3.uuid.UuidCreator;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cage_log", indexes = {@Index(name = "idx_cage_log_created_date", columnList = "created_date")})
public class CageLog extends PanacheEntityBase {

  @Id
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cage_id")
  private Cage cage;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @Column(name = "observed_population")
  private Integer observedPopulation;

  @Column(name = "dead_count")
  private Integer deadCount;

  @Column(name = "killed_count")
  private Integer killedCount;

  @Column
  private Integer population;

  @Column(name = "egg_count")
  private Integer eggCount;

  @Column(name = "weight_count")
  private BigDecimal weightCount;

  @Column(name = "egg_population_ratio")
  private BigDecimal eggPopulationRatio;

  @Column(name = "egg_weight_ratio")
  private BigDecimal eggWeightRatio;

  /*
   * Ga bisa ketahuan kecuali udah di input
   *
   * @Column(name = "egg_accumulation_counter") private Integer eggAccumulationCounter;
   *
   * @Column(name = "weight_accumulation_counter") private Integer weightAccumulationCounter;
   *
   * lebih baik nanti di dashboard aja di tampilkan
   */
  @Column(name = "feed_intake")
  private Integer feedIntake;

  @Column(name = "feed_count")
  private Integer feedCount;

  @Column(name = "feed_convertion_ratio")
  private BigDecimal feedConvertionRatio;

  public CageLog() {}

  public CageLog(Cage cage, LocalDateTime createdDate, Integer observedPopulation, Integer deadCount,
      Integer killedCount, Integer eggCount, BigDecimal weightCount, Integer feedIntake) {
    this.id = UuidCreator.getTimeOrderedEpoch();
    this.cage = cage;
    if (Objects.isNull(createdDate)) {
      this.createdDate = LocalDateTime.now();
    } else {
      this.createdDate = createdDate;
    }
    this.observedPopulation = observedPopulation;
    this.deadCount = deadCount;
    this.killedCount = killedCount;
    this.population = this.observedPopulation - this.deadCount - this.killedCount;
    this.eggCount = eggCount;
    this.weightCount = weightCount;
    this.eggPopulationRatio = BigDecimal.valueOf(this.eggCount.longValue())
        .divide(BigDecimal.valueOf(this.observedPopulation.longValue()), 4, RoundingMode.HALF_UP)
        .multiply(BigDecimal.valueOf(100L));
    this.eggWeightRatio = weightCount.divide(new BigDecimal(eggCount.longValue()), 7, RoundingMode.HALF_UP)
        .multiply(BigDecimal.valueOf(1000L));
    this.feedIntake = feedIntake;
    this.feedCount = new BigDecimal(this.population.longValue()).multiply(new BigDecimal(this.feedIntake.longValue()))
        .divide(new BigDecimal(1000L), 0, RoundingMode.HALF_UP).intValue();
    this.feedConvertionRatio =
        BigDecimal.valueOf(this.feedCount.longValue()).divide(weightCount, 1, RoundingMode.HALF_UP);
  }

  public Cage getCage() {
    return cage;
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

  public BigDecimal getWeightCount() {
    return weightCount;
  }

  public void setCage(Cage cage) {
    this.cage = cage;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public void setDeadCount(Integer deadCount) {
    this.deadCount = deadCount;
  }

  public void setEggCount(Integer eggCount) {
    this.eggCount = eggCount;
  }

  public void setEggPopulationRatio(BigDecimal eggPopulationRatio) {
    this.eggPopulationRatio = eggPopulationRatio;
  }

  public void setEggWeightRatio(BigDecimal eggWeightRatio) {
    this.eggWeightRatio = eggWeightRatio;
  }

  public void setFeedConvertionRatio(BigDecimal fitConvertionRatio) {
    this.feedConvertionRatio = fitConvertionRatio;
  }

  public void setFeedCount(Integer feedCount) {
    this.feedCount = feedCount;
  }

  public void setFeedIntake(Integer feedIntake) {
    this.feedIntake = feedIntake;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setKilledCount(Integer killedCount) {
    this.killedCount = killedCount;
  }

  public void setObservedPopulation(Integer observedPopulation) {
    this.observedPopulation = observedPopulation;
  }

  public void setPopulation(Integer population) {
    this.population = population;
  }

  public void setWeightCount(BigDecimal weightCount) {
    this.weightCount = weightCount;
  }

}
