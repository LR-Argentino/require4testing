package org.blackbird.requirefortesting.requirements;


import org.blackbird.requirefortesting.requirements.domain.Priority;
import org.blackbird.requirefortesting.requirements.domain.Requirement;

interface RequirmentService {

  Requirement createRequirement(String title, String description, Priority priority);

  void updateRequirement(Long requirementId, Requirement newRequirement);

  void completeRequirement(Long requirementId);
}
