package com.synergysuite.hrmservice.JPARepository;

import com.synergysuite.hrmservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJPARepository extends JpaRepository<Employee, Integer> {
}
