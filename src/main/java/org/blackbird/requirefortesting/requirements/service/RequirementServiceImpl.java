package org.blackbird.requirefortesting.requirements.service;

import org.blackbird.requirefortesting.authentication.SecurityService;
import org.blackbird.requirefortesting.requirements.domain.Priority;
import org.blackbird.requirefortesting.requirements.domain.Requirement;
import org.blackbird.requirefortesting.requirements.domain.Status;
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
    public void updateRequirement(Long requirementId, Requirement newRequirement) {

    }


    @Override
    public void completeRequirement(Long requirementId) {
    /*
        TODO: Since we work with events, the logic whether the user is a tester or not is
         obsolete because the logic is then implemented in the test execution context.
         This prevents an unnecessary event notification + database query from being executed
     */
        Requirement fetchedRequirement = requirementRepository.findById(requirementId)
                .orElseThrow(() -> new IllegalStateException("Requirement not found"));
        if (!securityService.isUserTester()) {
            throw new IllegalStateException(
                    "Only tester can change the Status to complete on requirements");
        }

        if (fetchedRequirement.getStatus().isOpen()) {
            throw new IllegalStateException(
                    "Requirement is still open, cannot mark it as completed");
        } else if (fetchedRequirement.getStatus().isInProgress()) {
            fetchedRequirement.setStatus(Status.CLOSED);
        } else {
            throw new IllegalStateException(
                    "Requirement is already closed, cannot mark it as completed");
        }
    }
}
