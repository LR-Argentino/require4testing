package org.blackbird.requirefortesting.requirement.service;

import org.blackbird.requirefortesting.requirement.model.Priority;
import org.blackbird.requirefortesting.requirement.model.Requirement;
import org.blackbird.requirefortesting.requirement.model.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequirementServiceTests {
    @Mock
    private RequirementService requirementService;

    @Test
    void test_init_shouldCreateRequirement_whenTitleIsValid() {
        String title = "Requirement title";
        Requirement requirement = new Requirement();
        requirement.setTitle(title);

        Requirement response = new Requirement(1L, title, null, Priority.LOW, Status.OPEN, null);
        when(requirementService.createRequirement(requirement)).thenReturn(response);

        Requirement result = requirementService.createRequirement(requirement);

        assertEquals(requirement.getStatus(), result.getStatus());
    }
}
