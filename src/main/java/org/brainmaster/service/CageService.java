package org.brainmaster.service;

import java.util.List;
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
  public CageLog appendLog(CageLog cageLog) {
    cageLog.persist();
    return CageLog.findById(cageLog.getId());
  }

}
