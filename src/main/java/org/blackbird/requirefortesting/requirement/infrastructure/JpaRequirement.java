package org.blackbird.requirefortesting.requirement.infrastructure;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.blackbird.requirefortesting.requirement.model.Priority;
import org.blackbird.requirefortesting.requirement.model.Status;

import java.util.List;

@Entity
@Table(name = "requirements")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JpaRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Priority priority;

    @Column(nullable = false)
    private Status status;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "requirement",
            orphanRemoval = true
    )
    private List<JpaTestCase> testCases;
}
