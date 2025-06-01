package org.blackbird.requirefortesting.requirements.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "requirements")
class Requirement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Priority priority = Priority.LOW;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status = Status.OPEN;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @CreationTimestamp
  private Timestamp createdAt;

  public Requirement(String title, String description, Priority priority, Status status,
      String createBy) {
    setTitle(title);
    setDescription(description);
    setPriority(priority);
    setStatus(status);
    setCreatedBy(createBy);
  }

  protected Requirement() {

  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Priority getPriority() {
    return priority;
  }

  public Status getStatus() {
    return status;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setTitle(String title) {
    if (title == null || title.isEmpty() || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be null or empty");
    }

    if (getStatus().isInProgress() || getStatus().isClosed()) {
      throw new IllegalStateException("Cannot change title of an open requirement");
    }

    this.title = title;
  }

  public void setDescription(String description) {
    if (getStatus().isInProgress() || getStatus().isClosed()) {
      throw new IllegalStateException("Cannot change description of an open requirement");
    }
    this.description = description;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }
}
