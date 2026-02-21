package org.brainmaster.entity;

import java.math.BigDecimal;
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
@Table(name = "cage_log", indexes = {
  @Index(name = "idx_cage_log_created_date", columnList = "created_date")
})
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

  @Column(name = "fit_convertion_ratio")
  private BigDecimal fitConvertionRatio;

  public CageLog() {}

  public CageLog(Cage cage, LocalDateTime createdDate, Integer observedPopulation,
      Integer deadCount, Integer killedCount, Integer eggCount, BigDecimal weightCount,
      Integer feedIntake) {
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
        .divide(BigDecimal.valueOf(this.population.longValue())).multiply(BigDecimal.valueOf(100L));
    this.eggWeightRatio = BigDecimal.valueOf(this.eggCount.longValue()).divide(weightCount).multiply(BigDecimal.valueOf(100L));
    this.feedIntake = feedIntake;
    this.feedCount = this.population * this.feedIntake;
    this.fitConvertionRatio = BigDecimal.valueOf(this.feedCount.longValue())
        .divide(BigDecimal.valueOf(this.weightCount.longValue()))
        .multiply(BigDecimal.valueOf(100L));
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Cage getCage() {
    return cage;
  }

  public void setCage(Cage cage) {
    this.cage = cage;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public Integer getObservedPopulation() {
    return observedPopulation;
  }

  public void setObservedPopulation(Integer observedPopulation) {
    this.observedPopulation = observedPopulation;
  }

  public Integer getDeadCount() {
    return deadCount;
  }

  public void setDeadCount(Integer deadCount) {
    this.deadCount = deadCount;
  }

  public Integer getKilledCount() {
    return killedCount;
  }

  public void setKilledCount(Integer killedCount) {
    this.killedCount = killedCount;
  }

  public Integer getPopulation() {
    return population;
  }

  public void setPopulation(Integer population) {
    this.population = population;
  }

  public Integer getEggCount() {
    return eggCount;
  }

  public void setEggCount(Integer eggCount) {
    this.eggCount = eggCount;
  }

  public BigDecimal getWeightCount() {
    return weightCount;
  }

  public void setWeightCount(BigDecimal weightCount) {
    this.weightCount = weightCount;
  }

  public BigDecimal getEggPopulationRatio() {
    return eggPopulationRatio;
  }

  public void setEggPopulationRatio(BigDecimal eggPopulationRatio) {
    this.eggPopulationRatio = eggPopulationRatio;
  }

  public BigDecimal getEggWeightRatio() {
    return eggWeightRatio;
  }

  public void setEggWeightRatio(BigDecimal eggWeightRatio) {
    this.eggWeightRatio = eggWeightRatio;
  }

  public Integer getFeedIntake() {
    return feedIntake;
  }

  public void setFeedIntake(Integer feedIntake) {
    this.feedIntake = feedIntake;
  }

  public Integer getFeedCount() {
    return feedCount;
  }

  public void setFeedCount(Integer feedCount) {
    this.feedCount = feedCount;
  }

  public BigDecimal getFitConvertionRatio() {
    return fitConvertionRatio;
  }

  public void setFitConvertionRatio(BigDecimal fitConvertionRatio) {
    this.fitConvertionRatio = fitConvertionRatio;
  }


}
