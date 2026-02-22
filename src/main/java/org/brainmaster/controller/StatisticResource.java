package org.brainmaster.controller;

import io.quarkus.security.Authenticated;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/farm-statistics")
public class StatisticResource {

  // @POST
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Authenticated
  public String getDailyStatistics(@PathParam("id") String cageId) {
    return String.format("{\"field\":\"%s\"}", cageId);
  }
}
