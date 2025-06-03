package org.blackbird.requirefortesting.requirements;

import org.blackbird.requirefortesting.authentication.SecurityService;
import org.springframework.stereotype.Service;

@Service
class RequirementServiceImpl implements RequirmentService {

  private SecurityService securityService;

  @Override
  public Requirement createRequirement(String title, String description, Priority priority) {

    if (!securityService.isRequirementEngineer()) {
      throw new IllegalStateException("Only requirement engineers can create requirements");
    }
    String userId = securityService.getCurrentUserId();
    
    Requirement newRequirement = new Requirement();
    newRequirement.setTitle(title);
    newRequirement.setDescription(description);
    newRequirement.setPriority(priority);
    newRequirement.setCreatedBy(userId);

    return newRequirement;
  }
}
