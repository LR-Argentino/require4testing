package org.blackbird.require4testing.requirement.models.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.blackbird.require4testing.requirement.models.Priority;

public record CreateRequirementRequest(
    @NotNull @NotBlank String title, String description, @NotNull @NotBlank Priority priority) {}
