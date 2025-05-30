package org.blackbird.require4testing.requirement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "requirements")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Requirement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false)
  @NotBlank
  @NotNull
  @Size(max = 100)
  private String title;

  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @NotNull
  private Priority priority;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @NotNull
  private Status status;

  /** TODO: Change it to ManyToOne relationship with User entity. */
  @Column(name = "created_by", nullable = false)
  @NotNull
  private Long createdBy;

  @Column(name = "created_on", nullable = false)
  @NotNull
  private Timestamp createdOn;

  @PrePersist
  protected void onCreate() {
    createdOn = new Timestamp(System.currentTimeMillis());
    status = Status.OPEN;
  }
}
