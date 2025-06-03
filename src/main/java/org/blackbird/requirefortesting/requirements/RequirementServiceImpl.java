package org.blackbird.requirefortesting.requirements;

import org.blackbird.requirefortesting.authentication.SecurityService;
import org.springframework.stereotype.Service;

@Service
class RequirementServiceImpl implements RequirmentService {

  private SecurityService securityService;

  @Override
  public Requirement createRequirement(String title, String description, Priority priority) {
    String userId = securityService.getCurrentUserId();

    if (userId == null) {
      throw new IllegalStateException("User is not authenticated");
    }

    if (!securityService.isRequirementEngineer()) {
      throw new IllegalStateException("Only requirement engineers can create requirements");
    }

    Requirement newRequirement = new Requirement();
    newRequirement.setTitle(title);
    newRequirement.setDescription(description);
    newRequirement.setPriority(priority);

    return newRequirement;
  }
}
