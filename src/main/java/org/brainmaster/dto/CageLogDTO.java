package org.brainmaster.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CageLogDTO(UUID cageId, LocalDateTime createdDate, Integer observedPopulation, Integer deadCount,
    Integer killedCount, Integer eggCount, BigDecimal weightCount, Integer feedIntake, BigDecimal eggPopulationRatio,
    BigDecimal eggWeightRatio, BigDecimal feedConvertionRatio, Integer feedCount, Integer population,
    Long weeksBetween) {
}
