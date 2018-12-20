package com.synergysuite.hrmservice.model;

import com.synergysuite.hrmservice.service.EmployeeService;
import jdk.jfr.Name;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = Employee.GET_ALL, query = "Select e from Employee e")
})

public class  Employee {

    public static final String GET_ALL = "Employee.getAll";

    //Query

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "fk_branch_id")
    private Integer fk_branch_id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private Integer active;


    public Employee() {
    }

    public Employee(Integer id, Integer fk_branch_id, String firstName, String lastName, String email, Integer active) {
        this.id = id;
        this.fk_branch_id = fk_branch_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFk_branch_id() {
        return fk_branch_id;
    }

    public void setFk_branch_id(Integer fk_branch_id) {
        this.fk_branch_id = fk_branch_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fk_branch_id=" + fk_branch_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
