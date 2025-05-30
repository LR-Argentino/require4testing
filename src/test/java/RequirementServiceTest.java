import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Set;
import org.blackbird.require4testing.requirement.models.DTOs.CreateRequirementRequest;
import org.blackbird.require4testing.requirement.models.Priority;
import org.blackbird.require4testing.requirement.models.Requirement;
import org.blackbird.require4testing.requirement.models.Status;
import org.blackbird.require4testing.requirement.repositories.RequirementRepository;
import org.blackbird.require4testing.requirement.services.RequirementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void test_create_shouldSaveRequriementToRepository() {
    // Arrange
    String title = "  ";
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
  void test_requirement_validation_shouldFailForBlankTitle() {
    // Arrange
    Requirement requirement = new Requirement();
    requirement.setTitle("   "); // nur Whitespaces
    requirement.setDescription("Valid description");
    requirement.setPriority(Priority.HIGH);
    requirement.setStatus(Status.OPEN);
    requirement.setCreatedBy(1L);
    requirement.setCreatedOn(new Timestamp(System.currentTimeMillis()));

    // Act
    Set<ConstraintViolation<Requirement>> violations = validator.validate(requirement);

    // Debug output
    System.out.println("Number of violations: " + violations.size());
    violations.forEach(
        v -> System.out.println("Violation: " + v.getPropertyPath() + " - " + v.getMessage()));

    // Assert
    assertFalse(violations.isEmpty(), "Validation should fail for blank title");

    // Überprüfe, dass es ein @NotBlank Verstoß für das title Feld gibt
    boolean hasNotBlankViolation =
        violations.stream()
            .anyMatch(
                violation ->
                    violation.getPropertyPath().toString().equals("title")
                        && violation.getConstraintDescriptor()
                        .getAnnotation() instanceof NotBlank);
    
    assertTrue(hasNotBlankViolation, "Should have NotBlank violation for title field");
  }
}
