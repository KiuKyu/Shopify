package com.shopify.model.persistence.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(columnDefinition = "VARCHAR(60)", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role")
    private List<Role> roles;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private long createDate;

    @NotNull
    @Column(name = "modify_date", nullable = false)
    private long modifyDate = 0L;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.createDate = System.currentTimeMillis();
    }
}
