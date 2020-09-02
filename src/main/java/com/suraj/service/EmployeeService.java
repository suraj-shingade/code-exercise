package com.suraj.service;

import com.suraj.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee save(Employee employee);

    Page<Employee> findAll(Pageable pageable);

    Optional<Employee> findOne(Long id);

    void delete(Long id);

    int saveAll(List<Employee> employeeList);

    List<Employee> findBySupervisorID(Employee supervisorID);

    List<Employee> findByPlaceContainingIgnoreCase(String place);

    List<Employee> findByBusinessUnitContainingIgnoreCase(String businessUnit);

    List<Employee> findByTitleContainingIgnoreCase(String title);
}
