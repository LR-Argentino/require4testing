package org.blackbird.require4testing.requirement.models.DTOs;

import org.blackbird.require4testing.requirement.models.Priority;

public record CreateRequirementRequest(
    String title,
    String description,
    Priority priority) {

}
