package org.blackbird.require4testing.requirement.repositories;

import org.blackbird.require4testing.requirement.models.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {}
