package org.brainmaster.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.github.f4b6a3.uuid.UuidCreator;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cage")
public class Cage extends PanacheEntityBase {

  @Id
  private UUID id;
  
  @Column
  private String name;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @Column(name = "created_by")
  private String createdBy;
  
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cage", orphanRemoval = true)
  List<CageLog> logs;

  public Cage(){}

  public Cage(String name, String createdBy) {
    this.id = UuidCreator.getTimeOrderedEpoch();
    this.name = name;
    this.createdDate = LocalDateTime.now();
    this.createdBy = createdBy;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public List<CageLog> getLogs() {
    return logs;
  }

  public void setLogs(List<CageLog> logs) {
    this.logs = logs;
  }

  
}
