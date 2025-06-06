package org.blackbird.requirefortesting.requirement.service;

import org.blackbird.requirefortesting.requirement.model.Requirement;
import org.springframework.stereotype.Service;

@Service
class RequirementServiceImpl implements RequirementService {
    @Override
    public Requirement createRequirement(Requirement requirement) {
        if (requirement == null) {
            throw new IllegalArgumentException("Requirement cannot be null");
        }

        if (requirement.getTitle() == null || requirement.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        return null;
    }
}
