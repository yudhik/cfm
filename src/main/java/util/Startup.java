package util;

import org.brainmaster.entity.Cage;
import org.brainmaster.entity.Member;
import org.brainmaster.service.AuthenticationService;
import org.brainmaster.service.CageService;
import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class Startup {

  @Inject
  AuthenticationService authService;

  @Inject
  CageService cageService;

  public void start(@Observes StartupEvent evt) {
    // in DEV mode we seed some data
    if (LaunchMode.current() == LaunchMode.DEVELOPMENT) {
      Member member = new Member();
      member.setUsername("admin");
      member.setPassword("admin");
      member.setRole("ADMIN");
      authService.createAuthentication(member);

      for (int i = 0; i < 3; i++) {
        cageService.createCage(new Cage(String.format("Kandang %d", i + 1), "system"));
      }

    }
  }
}
