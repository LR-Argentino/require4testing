package org.blackbird.requirefortesting.requirement.model;

import java.util.List;

public class Requirement {
    private Long id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private List<RequirementTestCase> requirements;

    public Requirement() {
        this.priority = Priority.LOW;
        this.status = Status.OPEN;
    }

    public Requirement(Long id, String title, String description, Priority priority, Status status, List<RequirementTestCase> requirements) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.requirements = requirements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<RequirementTestCase> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<RequirementTestCase> requirements) {
        this.requirements = requirements;
    }
}
