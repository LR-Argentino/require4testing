package org.blackbird.requirefortesting.requirements.repository;

import org.blackbird.requirefortesting.requirements.domain.Requirement;

import java.util.Optional;

interface RequirementRepository {

    Optional<Requirement> findById(Long id);
}
