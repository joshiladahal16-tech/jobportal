package com.project.jobportal5.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public Role(String resolvedRoleName) {

    }

    public String getName() {
        return null;
    }

    public class role {
        @Id
        private Long id;

        @Column(nullable = false, unique = true)
        private String name;   // ✅ now you get getName()

        // getters/setters or Lombok @Data
    }

}
