package org.brainmaster.controller;

import java.util.List;
import org.brainmaster.dto.CageDTO;
import org.brainmaster.service.CageService;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestForm;
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
    public static native TemplateInstance index();

    public static native TemplateInstance cage(List<CageDTO> cages);

    public static native TemplateInstance operation(List<CageDTO> cages);
  }

  @Path("/dashboard")
  public TemplateInstance index() {
    return Templates.index();
  }

  @Path("/cage")
  public TemplateInstance cage() {
    List<CageDTO> cages = cageService.getCages(0, 10).stream().map(cage -> new CageDTO(cage.getId(),
        cage.getName(), cage.getCreatedDate(), cage.getCreatedBy())).toList();
    return Templates.cage(cages);
  }

  @Path("/operation")
  public TemplateInstance operation() {
    List<CageDTO> cages = cageService.getCages(0, 10).stream().map(cage -> new CageDTO(cage.getId(),
        cage.getName(), cage.getCreatedDate(), cage.getCreatedBy())).toList();
    return Templates.operation(cages);
  }

  @POST
  @Path("/operation/append")
  public void appendOperation(@RestForm String cageId, @RestForm Integer observedPopulation,
      @RestForm Integer deadCount, @RestForm Integer killedCount, @RestForm Integer eggCount,
      @RestForm Integer weightCount, @RestForm Integer feedIntake) {
    
    log.info(String.format("cage id : %s, observerdPopulation : %d, deadCount : %d, killedCount : %d, eggCount : %d, weightCount : %d, feedIntake : %d",
      cageId, observedPopulation, deadCount, killedCount, eggCount, weightCount, feedIntake));
    cage();
  }

}
