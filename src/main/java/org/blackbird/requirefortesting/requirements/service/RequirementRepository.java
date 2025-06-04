package org.blackbird.requirefortesting.requirements.service;

import java.util.Optional;
import org.blackbird.requirefortesting.requirements.domain.Requirement;

interface RequirementRepository {

  Optional<Requirement> findById(Long id);
}
