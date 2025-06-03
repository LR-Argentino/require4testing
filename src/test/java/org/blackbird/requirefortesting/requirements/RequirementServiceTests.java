package org.blackbird.requirefortesting.requirements;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.blackbird.requirefortesting.authentication.SecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RequirementServiceTests {

  @Mock
  private SecurityService securityService;

  @Mock
  private RequirementRepository requirementRepository;

  @InjectMocks
  private RequirementServiceImpl sut;

  @Test
  void test_createRequirement_shouldReturnCreatedRequirementByRoleRequirementEngineer() {
    String title = "New Requirement";
    String description = "Requirement description";
    Priority priority = Priority.MEDIUM;
    when(securityService.isUserRequirementEngineer()).thenReturn(Boolean.TRUE);
    when(securityService.getCurrentUserId()).thenReturn(UUID.randomUUID().toString());

    Requirement creaedRequirement = sut.createRequirement(title,
        description, priority);

    assertThat(creaedRequirement).isNotNull();
    assertThat(creaedRequirement.getTitle()).isEqualTo(title);
    assertThat(creaedRequirement.getDescription()).isEqualTo(description);
    assertThat(creaedRequirement.getPriority()).isEqualTo(priority);
  }

  @Test
  void test_createRequirement_shouldThrowExceptionWhenUserIsNotRequirementEngineer() {
    String title = "New Requirement";
    String description = "Requirement description";
    Priority priority = Priority.MEDIUM;

    when(securityService.isUserRequirementEngineer()).thenReturn(false);

    assertThrows(IllegalStateException.class,
        () -> sut.createRequirement(title, description, priority));
  }

  @Test
  void test_createRequirement_shouldHaveStatusOPENAfterCreation() {
    String title = "New Requirement";
    String description = "Requirement description";
    Priority priority = Priority.MEDIUM;

    when(securityService.isUserRequirementEngineer()).thenReturn(Boolean.TRUE);
    when(securityService.getCurrentUserId()).thenReturn(UUID.randomUUID().toString());

    Requirement createdRequirement = sut.createRequirement(title, description, priority);

    assertThat(createdRequirement.getStatus()).isEqualTo(Status.OPEN);
  }

  @Test
  void test_completeRequirement_shouldThrowErrorWhenRequirementIsOnStatusOPEN() {
    String title = "New Requirement";
    String description = "Requirement description";
    Priority priority = Priority.MEDIUM;
    Long requirementId = 1L;

    Requirement requirement = new Requirement();
    requirement.setTitle(title);
    requirement.setDescription(description);
    requirement.setPriority(priority);
    requirement.setStatus(Status.OPEN);

    Optional<Requirement> fetchedRequirement = Optional.of(requirement);

    when(requirementRepository.findById(requirementId)).thenReturn(fetchedRequirement);
    when(securityService.isUserTester()).thenReturn(Boolean.TRUE);

    assertThrows(IllegalStateException.class, () -> sut.completeRequirement(requirementId));
  }


  @Test
  void test_completeRequirement_shouldChangeStatusSuccessfullyWhenRequirementIsInProgress() {
    String title = "New Requirement";
    String description = "Requirement description";
    Priority priority = Priority.MEDIUM;
    Long requirementId = 1L;

    Requirement requirement = new Requirement();
    requirement.setTitle(title);
    requirement.setDescription(description);
    requirement.setPriority(priority);
    requirement.setStatus(Status.IN_PROGRESS);

    Optional<Requirement> fetchedRequirement = Optional.of(requirement);

    when(requirementRepository.findById(requirementId)).thenReturn(fetchedRequirement);
    when(securityService.isUserTester()).thenReturn(Boolean.TRUE);

    assertDoesNotThrow(() -> sut.completeRequirement(requirementId));
  }
}
