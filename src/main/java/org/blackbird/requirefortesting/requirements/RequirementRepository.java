package org.blackbird.requirefortesting.requirements;

import java.util.Optional;
import org.blackbird.requirefortesting.requirements.domain.Requirement;

interface RequirementRepository {

  Optional<Requirement> findById(Long id);
}
