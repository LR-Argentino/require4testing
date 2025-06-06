package org.blackbird.requirefortesting.requirement.service;

import org.blackbird.requirefortesting.requirement.model.Priority;
import org.blackbird.requirefortesting.requirement.model.Requirement;
import org.blackbird.requirefortesting.requirement.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@DisplayName("RequirementService Unit Tests")
class RequirementServiceTests {

    @InjectMocks
    private RequirementServiceImpl requirementService;


    private Requirement validRequirement;
    private Requirement savedRequirement;

    @BeforeEach
    void setUp() {
        validRequirement = new Requirement();
        validRequirement.setTitle("Valid Requirement Title");
        validRequirement.setDescription("Valid description");
        validRequirement.setPriority(Priority.MEDIUM);

        savedRequirement = new Requirement();
        savedRequirement.setId(1L);
        savedRequirement.setTitle("Valid Requirement Title");
        savedRequirement.setDescription("Valid description");
        savedRequirement.setPriority(Priority.MEDIUM);
        savedRequirement.setStatus(Status.OPEN); // Service setzt Default Status
    }

    @Test
    void shouldCreateRequirement_whenTitleIsValid() {
        Requirement result = requirementService.createRequirement(validRequirement);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Valid Requirement Title");
        assertThat(result.getStatus()).isEqualTo(Status.OPEN);

        // ✅ Verify Repository Interaction
//        verify(requirementRepository).save(any(Requirement.class));
    }

    @Test
    void test_init_shouldThrowException_whenTitleIsBlank() {
        String title = "  ";
        Requirement requirement = new Requirement();
        requirement.setTitle(title);

        assertThrows(IllegalArgumentException.class, () -> {
            requirementService.createRequirement(requirement);
        });
    }
}
