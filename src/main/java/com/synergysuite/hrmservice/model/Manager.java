package com.synergysuite.hrmservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = Manager.GET_ALL_MANAGERS, query = "select m from Manager m"),
        @NamedQuery(name = Manager.GET_BY_ID, query = "Select m from Manager m where id = ?1"),
        @NamedQuery(name = Manager.GET_BY_MAIL, query = "select m from Manager m where email = ?1")

})
@Table(name = "managers")
public class Manager {

    public static final String GET_ALL_MANAGERS = "GetAllManagers";
    public static final String GET_BY_ID = "GetManagerById";
    public static final String GET_BY_MAIL = "GetManagersByMail";

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String last_name;
    private String email;
    private String password;
    private Long fk_branch_id;
    private boolean active;

    public Manager() {
    }

    public Manager(Long id, String name, String last_name, String email, String password, Long fk_branch_id, boolean active) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.fk_branch_id = fk_branch_id;
        this.active = active;
    }
    public Manager(String name, String last_name, String email, String password, Long fk_branch_id, boolean active) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.fk_branch_id = fk_branch_id;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public Long getFk_branch_id() {
        return fk_branch_id;
    }

    public void setFk_branch_id(Long fk_branch_id) {
        this.fk_branch_id = fk_branch_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return id.equals(manager.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fk_branch_id=" + fk_branch_id +
                ", active=" + active +
                '}';
    }
}