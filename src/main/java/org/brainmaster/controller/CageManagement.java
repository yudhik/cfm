package org.brainmaster.controller;

import java.util.List;
import java.util.UUID;
import org.brainmaster.dto.CageDTO;
import org.brainmaster.dto.CageLogDTO;
import org.brainmaster.service.CageService;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

public class CageManagement extends Controller {

  @Inject
  CageService cageService;

  @Inject
  Logger log;

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance index(Boolean dashboard);

    public static native TemplateInstance cage(Boolean dashboard, List<CageDTO> cages);

    public static native TemplateInstance operation(Boolean dashboard, List<CageDTO> cages);

    public static native TemplateInstance cagedetail(Boolean dashboard, List<CageLogDTO> cageLogs);
  }

  @Path("/dashboard")
  public TemplateInstance index() {
    return Templates.index(true);
  }

  @Path("/cage")
  public TemplateInstance cage() {
    List<CageDTO> cages = cageService.getCages(0, 10).stream().map(cage -> new CageDTO(cage.getId(),
        cage.getName(), cage.getCreatedDate(), cage.getCreatedBy())).toList();
    return Templates.cage(false, cages);
  }

  @Path("/cage/{id}")
  public TemplateInstance cageDetail(@RestPath("id") UUID id) {
    List<CageLogDTO> cages = cageService.getLogs(id, 8).stream()
        .map(log -> new CageLogDTO(log.getCage().getId(), log.getCreatedDate(),
            log.getObservedPopulation(), log.getDeadCount(), log.getKilledCount(),
            log.getEggCount(), log.getWeightCount(), log.getFeedIntake(),
            log.getEggPopulationRatio(), log.getEggWeightRatio(), log.getFeedConvertionRatio(),
            log.getFeedCount(), log.getPopulation()))
        .toList();
    return Templates.cagedetail(false, cages);
  }

  @Path("/operation")
  public TemplateInstance operation() {
    List<CageDTO> cages = cageService.getCages(0, 10).stream().map(cage -> new CageDTO(cage.getId(),
        cage.getName(), cage.getCreatedDate(), cage.getCreatedBy())).toList();
    return Templates.operation(false, cages);
  }

  @POST
  @Path("/operation/append")
  public void appendOperation(@RestForm String cageId, @RestForm Integer observedPopulation,
      @RestForm Integer deadCount, @RestForm Integer killedCount, @RestForm Integer eggCount,
      @RestForm Integer weightCount, @RestForm Integer feedIntake) {

    log.info(String.format(
        "cage id : %s, observerdPopulation : %d, deadCount : %d, killedCount : %d, eggCount : %d, weightCount : %d, feedIntake : %d",
        cageId, observedPopulation, deadCount, killedCount, eggCount, weightCount, feedIntake));
    cage();
  }

}
