package org.brainmaster.controller;

import java.util.List;
import java.util.UUID;
import org.brainmaster.dto.AuthenticatedUserDTO;
import org.brainmaster.dto.CageDTO;
import org.brainmaster.dto.CageLogDTO;
import org.brainmaster.service.CageService;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;
import org.jspecify.annotations.NonNull;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

public class CageManagement extends Controller {

  @Inject
  SecurityIdentity securityIdentity;

  @Inject
  CageService cageService;

  @Inject
  Logger log;

  @CheckedTemplate(requireTypeSafeExpressions = false)
  public static class Templates {
    public static native TemplateInstance index(AuthenticatedUserDTO authenticatedUser);

    public static native TemplateInstance cage(AuthenticatedUserDTO authenticatedUser,
        List<@NonNull CageDTO> cages, Integer currentPage, Integer pageSize, Integer totalPages);

    public static native TemplateInstance operation(AuthenticatedUserDTO authenticatedUser,
        List<@NonNull CageDTO> cages);

    public static native TemplateInstance cagedetail(AuthenticatedUserDTO authenticatedUser,
        List<@NonNull CageLogDTO> cageLogs);
  }

  @Path("/dashboard")
  @Authenticated
  public TemplateInstance index() {
    return Templates.index(getAuthenticatedUser());
  }

  @Path("/cage")
  @Authenticated
  public TemplateInstance cage(@RestQuery("page") @DefaultValue("0") Integer page,
      @RestQuery("size") @DefaultValue("1") Integer size) {
    List<@NonNull CageDTO> cages =
        cageService.getCages(page, size).stream().map(cage -> new CageDTO(cage.getId(),
            cage.getName(), cage.getCreatedDate(), cage.getCreatedBy())).toList();
    long totalCages = cageService.countCages();
    int totalPages = (int) Math.ceil((double) totalCages / size);
    return Templates.cage(getAuthenticatedUser(), cages, page, size, totalPages);
  }

  @Path("/cage/{id}")
  @Authenticated
  public TemplateInstance cageDetail(@RestPath("id") UUID id) {
    List<@NonNull CageLogDTO> cages = cageService.getLogs(id, 8).stream()
        .map(log -> new CageLogDTO(log.getCage().getId(), log.getCreatedDate(),
            log.getObservedPopulation(), log.getDeadCount(), log.getKilledCount(),
            log.getEggCount(), log.getWeightCount(), log.getFeedIntake(),
            log.getEggPopulationRatio(), log.getEggWeightRatio(), log.getFeedConvertionRatio(),
            log.getFeedCount(), log.getPopulation()))
        .toList();
    return Templates.cagedetail(getAuthenticatedUser(), cages);
  }

  @Path("/operation")
  @Authenticated
  public TemplateInstance operation() {
    List<@NonNull CageDTO> cages =
        cageService.getCages(0, 10).stream().map(cage -> new CageDTO(cage.getId(), cage.getName(),
            cage.getCreatedDate(), cage.getCreatedBy())).toList();
    return Templates.operation(getAuthenticatedUser(), cages);
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/operation/append")
  @Authenticated
  public void appendOperation(@RestForm String cageId, @RestForm Integer observedPopulation,
      @RestForm Integer deadCount, @RestForm Integer killedCount, @RestForm Integer eggCount,
      @RestForm Integer weightCount, @RestForm Integer feedIntake) {
    // TODO: save to database later
    log.info(String.format(
        "cage id : %s, observerdPopulation : %d, deadCount : %d, killedCount : %d, eggCount : %d, weightCount : %d, feedIntake : %d",
        cageId, observedPopulation, deadCount, killedCount, eggCount, weightCount, feedIntake));
    cage(0, 10);
  }

  private AuthenticatedUserDTO getAuthenticatedUser() {
    return new AuthenticatedUserDTO(securityIdentity.getPrincipal().getName(),
        securityIdentity.getRoles());
  }

}
