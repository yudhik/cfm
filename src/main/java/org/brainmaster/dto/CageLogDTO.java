package org.brainmaster.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CageLogDTO(UUID cageId, LocalDateTime createdDate, Integer observedPopulation,
      Integer deadCount, Integer killedCount, Integer eggCount, Integer weightCount,
      Integer feedIntake) {
}
