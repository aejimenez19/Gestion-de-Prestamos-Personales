package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity;

import com.aejimenezdev.gestionDePrestamosPersonales.enums.StateEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification_number", length = 50, nullable = false, unique = true)
    private String identifier;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 50, nullable = false)
    private StateEnum state;

    @Column(name = "phone", length = 15)
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<LoanEntity> clientLoans = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "providerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<LoanEntity> providerLoans = new java.util.ArrayList<>();
}
