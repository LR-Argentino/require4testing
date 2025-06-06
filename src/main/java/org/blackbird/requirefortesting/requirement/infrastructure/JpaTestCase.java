package org.blackbird.requirefortesting.requirement.infrastructure;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.blackbird.requirefortesting.requirement.model.Result;
import org.blackbird.requirefortesting.requirement.model.Status;

import java.time.LocalDateTime;

@Entity
@Table(name = "testcases")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JpaTestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private JpaRequirement requirement;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Result result;

    // TODO: private User user;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreate() {
        updatedAt = LocalDateTime.now();
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
