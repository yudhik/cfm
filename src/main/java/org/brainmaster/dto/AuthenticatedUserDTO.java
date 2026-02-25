package org.brainmaster.dto;

import java.util.Set;

public record AuthenticatedUserDTO (String userId, Set<String> roles) {

}
