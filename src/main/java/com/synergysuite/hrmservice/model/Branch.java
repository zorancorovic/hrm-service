package com.synergysuite.hrmservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = Branch.GET_ALL, query ="select b from Branch b"),
        @NamedQuery(name = Branch.GET_BY_ID, query = "select b from Branch b where id = ?1")
})
@Table(name = "branches")
public class Branch {
    public static final String GET_ALL = "Branch.getAll";
    public static final String GET_BY_ID = "Branch.getById";

    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private String city;
    private String email;
    private String name;
    private String password;
    private boolean active;

    public Branch(){
        super();

    }    public Branch(Long id, String address, String city, String email, String name, String password, boolean active) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.email = email;
        this.name = name;
        this.password = password;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        Branch branch = (Branch) o;
        return id.equals(branch.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", addres='" + address + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                '}';
    }
}
