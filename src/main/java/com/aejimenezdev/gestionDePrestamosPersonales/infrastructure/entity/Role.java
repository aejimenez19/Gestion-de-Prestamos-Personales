package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity;

import com.aejimenezdev.gestionDePrestamosPersonales.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
    @ManyToMany(mappedBy = "roles")
    private Set<ClientEntity> User;
}
