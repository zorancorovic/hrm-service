package com.synergysuite.hrmservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = Admin.GET_ALL, query = "SELECT a FROM Admin a"),
        @NamedQuery(name = Admin.GET_BY_ID, query = "SELECT a FROM Admin a WHERE a.id = ?1"),
        @NamedQuery(name = Admin.GET_BY_EMAIL, query = "SELECT  a FROM Admin a WHERE  a.email = ?1")
})
@Table(name = "admin")
public class Admin {

    public static final String GET_ALL = "Admin.getAll";
    public static final String GET_BY_ID = "Admin.getById";
    public static final String GET_BY_EMAIL = "Admin.getByEmail";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    public Admin(){

    }

    public Admin(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id) &&
                Objects.equals(email, admin.email) &&
                Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
