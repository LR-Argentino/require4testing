package org.blackbird.requirefortesting.requirements;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "requirements")
class Requirement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String description;

  private Priority priority;

  private Status status;

  private String createBy;

  private Timestamp createdAt;

  public Requirement(Long id) {
    this.id = id;
  }

  public Requirement() {

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

  public String getCreateBy() {
    return createBy;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setTitle(String title) {
    if (title == null || title.isEmpty() || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be null or empty");
    }
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
