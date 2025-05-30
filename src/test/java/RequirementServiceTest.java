import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import org.blackbird.require4testing.requirement.models.DTOs.CreateRequirementRequest;
import org.blackbird.require4testing.requirement.models.Priority;
import org.blackbird.require4testing.requirement.models.Requirement;
import org.blackbird.require4testing.requirement.models.Status;
import org.blackbird.require4testing.requirement.repositories.RequirementRepository;
import org.blackbird.require4testing.requirement.services.RequirementServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RequirementServiceTest {

  @Mock
  private RequirementRepository requirementRepository;

  @InjectMocks
  private RequirementServiceImpl requirementService;

  @Test
  void test_create_shouldSaveRequriementToRepository() {
    // Arrange
    String title = "Title";
    String description = "Description of new requirement";
    Priority priority = Priority.MEDIUM;

    CreateRequirementRequest requirementToCreate =
        new CreateRequirementRequest(title, description, priority);

    Requirement expectedRequirement = new Requirement();
    expectedRequirement.setId(1L);
    expectedRequirement.setTitle(title);
    expectedRequirement.setDescription(description);
    expectedRequirement.setPriority(priority);
    expectedRequirement.setCreatedOn(new Timestamp(System.currentTimeMillis()));
    expectedRequirement.setStatus(Status.OPEN);

    when(requirementRepository.save(any(Requirement.class))).thenReturn(expectedRequirement);

    // Act
    Requirement createdRequirement = requirementService.create(requirementToCreate);

    // Assert
    verify(requirementRepository).save(any(Requirement.class));
    assertEquals(1L, createdRequirement.getId());
    assertEquals(title, createdRequirement.getTitle());
    assertEquals(description, createdRequirement.getDescription());
  }


  @Test
  void test_create_shouldNotSaveRequriementToRepository_WhenTitleIsBlank() {
    // Arrange
    String title = "  ";
    String description = "Description of new requirement";
    Priority priority = Priority.MEDIUM;

    CreateRequirementRequest requirementToCreate =
        new CreateRequirementRequest(title, description, priority);

    assertThrows(IllegalArgumentException.class, () -> {
      requirementService.create(requirementToCreate);
    });

    verify(requirementRepository, never()).save(any(Requirement.class));
  }
}
