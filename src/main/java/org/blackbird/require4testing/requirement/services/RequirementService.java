package org.blackbird.require4testing.requirement.services;

import org.blackbird.require4testing.requirement.models.DTOs.CreateRequirementRequest;
import org.blackbird.require4testing.requirement.models.Requirement;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface RequirementService {

  Iterable<Requirement> findAllRequirements();

  Requirement findById(Long id) throws NotFoundException;

  Requirement create(CreateRequirementRequest requirement);

  Requirement update(Long id, Requirement requirement) throws NotFoundException;

  Requirement patchUpdate(Long id, Requirement requirement) throws NotFoundException;
}
