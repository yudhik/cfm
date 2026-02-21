package util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.brainmaster.entity.Cage;
import org.brainmaster.entity.CageLog;
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

      Cage cage = cageService.findByName("Kandang 1").get();
      CageLog cageLog1 = new CageLog(cage, LocalDateTime.now().minusDays(7), 21936, 3, 0, 4063, BigDecimal.valueOf(197.4), 102);
      cageService.appendLog(cageLog1);

      CageLog cageLog2 = new CageLog(cage, LocalDateTime.now().minusDays(6), 21933, 3, 0, 4394, BigDecimal.valueOf(216), 104);
      cageService.appendLog(cageLog2);

      CageLog cageLog3 = new CageLog(cage, LocalDateTime.now().minusDays(5), 21930, 3, 0, 4818, BigDecimal.valueOf(239.9), 104);
      cageService.appendLog(cageLog3);

      CageLog cageLog4 = new CageLog(cage, LocalDateTime.now().minusDays(4), 21927, 3, 0, 5297, BigDecimal.valueOf(265), 104);
      cageService.appendLog(cageLog4);

      CageLog cageLog5 = new CageLog(cage, LocalDateTime.now().minusDays(3), 21924, 3, 0, 5730, BigDecimal.valueOf(288.4), 104);
      cageService.appendLog(cageLog5);

      CageLog cageLog6 = new CageLog(cage, LocalDateTime.now().minusDays(2), 21921, 3, 0, 6192, BigDecimal.valueOf(312.8), 104);
      cageService.appendLog(cageLog6);

      CageLog cageLog7 = new CageLog(cage, LocalDateTime.now().minusDays(1), 21918, 3, 0, 6616, BigDecimal.valueOf(337.2), 104);
      cageService.appendLog(cageLog7);
    }
  }
}
