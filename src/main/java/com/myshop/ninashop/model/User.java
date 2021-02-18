package com.myshop.ninashop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shop_users", uniqueConstraints = @UniqueConstraint(columnNames = "u_email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "u_id")
    private Long id;

    @Column(name = "u_firstname")
    @NotEmpty(message = "Firstname is mandatory!")
    private String firstname;

    @Column(name = "u_lastname")
    @NotEmpty(message = "Lastname is mandatory!")
    private String lastname;

    @Column(name = "u_email")
    @NotEmpty(message = "Email is mandatory!")
    @Email(message = "Please enter a valid email address!")
    private String email;

    @Column(name = "u_password")
    @NotEmpty(message = "Password is mandatory!")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "shop_users_role",
                joinColumns = @JoinColumn(name = "u_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Order> userOrders = new HashSet<>();



    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() { return roles; }

    public void setRoles(Collection<Role> roles) { this.roles = roles; }

    public Set<Order> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(Set<Order> userOrders) {
        this.userOrders = userOrders;
    }
}
