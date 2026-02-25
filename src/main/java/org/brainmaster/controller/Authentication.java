package org.brainmaster.controller;

import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.Path;

public class Authentication extends Controller {
  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance login();
  }

  @Path("/login")
  public TemplateInstance login() {
    return Templates.login();
  }

  @Path("/unauthorized")
  public TemplateInstance unauthorize() {
    return Templates.login();
  }

}
