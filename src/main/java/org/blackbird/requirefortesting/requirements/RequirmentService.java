package org.blackbird.requirefortesting.requirements;


interface RequirmentService {

  Requirement createRequirement(String title, String description, Priority priority);
  void completeRequirement(Long requirementId);
}
