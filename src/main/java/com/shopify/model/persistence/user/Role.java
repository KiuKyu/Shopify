package com.shopify.model.persistence.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private long createDate;

    @NotNull
    @Column(name = "modify_date", nullable = false)
    private long modifyDate = 0L;

    public Role(String name) {
        this.name = name;
        this.createDate = System.currentTimeMillis();
    }

    public Role(String name, Long createDate) {
        this.name = name;
        this.createDate = createDate;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
