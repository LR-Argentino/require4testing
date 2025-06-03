package org.blackbird.requirefortesting.requirements;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

  @InjectMocks
  private RequirementServiceImpl sut;

  @Test
  void test_createRequirement_shouldReturnCreatedRequirementByRoleRequirementEngineer() {
    String title = "New Requirement";
    String description = "Requirement description";
    Priority priority = Priority.MEDIUM;
    when(securityService.isRequirementEngineer()).thenReturn(true);
    when(securityService.getCurrentUserId()).thenReturn(UUID.randomUUID().toString());

    Requirement creaedRequirement = sut.createRequirement(title,
        description, priority);

    assertThat(creaedRequirement).isNotNull();
    assertThat(creaedRequirement.getTitle()).isEqualTo(title);
    assertThat(creaedRequirement.getDescription()).isEqualTo(description);
    assertThat(creaedRequirement.getPriority()).isEqualTo(priority);
  }
}
