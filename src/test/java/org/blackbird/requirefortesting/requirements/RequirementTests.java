package org.blackbird.requirefortesting.requirements;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;

@ApplicationModuleTest
class RequirementTests {


  @Test
  void test_create_shouldThrowException_whenTitleIsBlank() {
    String title = "  ";

    Requirement requirement = new Requirement(1L);

    assertThrows(IllegalArgumentException.class, () -> requirement.setTitle(title));
  }
}
