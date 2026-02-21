package org.brainmaster.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CageDTO(UUID id, String name, LocalDateTime createdDate, String createdBy) {
}
