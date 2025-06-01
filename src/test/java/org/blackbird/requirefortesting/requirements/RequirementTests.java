package org.blackbird.requirefortesting.requirements;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;

@ApplicationModuleTest
class RequirementTests {


  @Test
  void test_create_shouldThrowException_whenTitleIsBlank() {
    Requirement requirement = new Requirement();
    assertThrows(IllegalArgumentException.class, () -> requirement.setTitle(" "));
  }

  @Test
  void test_create_shouldThrowException_whenTitleIsNull() {
    Requirement requirement = new Requirement();
    assertThrows(IllegalArgumentException.class, () -> requirement.setTitle(null));
  }

  @Test
  void test_create_shouldThrowException_whenTitleIsEmpty() {
    Requirement requirement = new Requirement();
    assertThrows(IllegalArgumentException.class, () -> requirement.setTitle(""));
  }

  @Test
  void test_create_shouldHaveStatusOpen_whenCreated() {
    Requirement requirement = new Requirement();

    requirement.setTitle("Test Requirement");

    assertThat(requirement.getStatus()).isEqualTo(Status.OPEN);
  }

  @Test
  void test_create_shouldHavePriorityLow_whenCreatedAndPriorityIsNotSet() {
    Requirement requirement = new Requirement();
    String title = "Test Requirement";

    requirement.setTitle(title);

    assertThat(requirement.getPriority()).isEqualTo(Priority.LOW);
  }

  @Test
  void test_create_shouldThrowException_whenUpdatingRequirmentWhenStatusIsInProgress() {
    Requirement requirement = new Requirement();
    requirement.setTitle("Test Requirement");
    requirement.setStatus(Status.IN_PROGRESS);

    assertThrows(IllegalStateException.class, () -> requirement.setTitle("Updated Title"));
    assertThrows(IllegalStateException.class,
        () -> requirement.setDescription("Updated Description"));
  }
}
