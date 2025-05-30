package org.blackbird.require4testing.requirement.services;

import org.blackbird.require4testing.requirement.models.DTOs.CreateRequirementRequest;
import org.blackbird.require4testing.requirement.models.Requirement;
import org.blackbird.require4testing.requirement.repositories.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RequirementServiceImpl implements RequirementService {

  private final RequirementRepository requirementRepository;

  @Autowired
  public RequirementServiceImpl(RequirementRepository requirementRepository) {
    this.requirementRepository = requirementRepository;
  }

  @Override
  public Iterable<Requirement> findAllRequirements() {
    return null;
  }

  @Override
  public Requirement findById(Long id) throws NotFoundException {
    return null;
  }

  @Override
  public Requirement create(CreateRequirementRequest requirement) {

    if (requirement.title() == null || requirement.title().isBlank()) {
      throw new IllegalArgumentException("Title cannot be null or blank");
    }

    if (requirement.priority() == null) {
      throw new IllegalArgumentException("Priority cannot be null");
    }

    Requirement newRequirement = new Requirement();
    newRequirement.setTitle(requirement.title());
    newRequirement.setDescription(requirement.description());
    newRequirement.setPriority(requirement.priority());

    return requirementRepository.save(newRequirement);
  }

  @Override
  public Requirement update(Long id, Requirement requirement) throws NotFoundException {
    return null;
  }

  @Override
  public Requirement patchUpdate(Long id, Requirement requirement) throws NotFoundException {
    return null;
  }
}
