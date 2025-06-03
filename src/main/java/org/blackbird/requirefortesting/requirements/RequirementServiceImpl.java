package org.blackbird.requirefortesting.requirements;

import org.blackbird.requirefortesting.authentication.SecurityService;
import org.springframework.stereotype.Service;

@Service
class RequirementServiceImpl implements RequirmentService {

  private SecurityService securityService;
  private RequirementRepository requirementRepository;

  @Override
  public Requirement createRequirement(String title, String description, Priority priority) {

    if (!securityService.isUserRequirementEngineer()) {
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


  @Override
  public void completeRequirement(Long requirementId) {
    // 1) Fetch the requirement by ID
    Requirement fetchedRequirement = requirementRepository.findById(requirementId)
        .orElseThrow(() -> new IllegalStateException("Requirement not found"));
    // 2) Check if the current user is Tester
    if (!securityService.isUserTester()) {
      throw new IllegalStateException(
          "Only tester can change the Status to complete on requirements");
    }
    // INFO: Es liegt beim Tester, ob er die Anforderung als abgeschlossen markiert oder nicht.
    // 3) Check if the requirement is in Status OPEN
    if (fetchedRequirement.getStatus().isOpen()) {
      throw new IllegalStateException(
          "Requirement is still open, cannot mark it as completed");
    } else if (fetchedRequirement.getStatus().isClosed()) {
      return;
    } else if (fetchedRequirement.getStatus().isInProgress()) {
      fetchedRequirement.setStatus(Status.CLOSED);
    }
  }
}
