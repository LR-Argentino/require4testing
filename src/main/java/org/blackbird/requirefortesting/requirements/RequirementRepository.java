package org.blackbird.requirefortesting.requirements;

import java.util.Optional;

interface RequirementRepository {

  Optional<Requirement> findById(Long id);
}
