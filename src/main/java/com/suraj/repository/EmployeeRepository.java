package com.suraj.repository;

import com.suraj.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByPlaceContainingIgnoreCase(String place);

    List<Employee> findBySupervisorID(Employee supervisorID);

    List<Employee> findByBusinessUnitContainingIgnoreCase(String businessUnit);

    List<Employee> findByTitleContainingIgnoreCase(String title);
}
