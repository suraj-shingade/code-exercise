package com.suraj.service.impl;

import com.suraj.domain.Employee;
import com.suraj.repository.EmployeeRepository;
import com.suraj.service.EmployeeService;
import com.suraj.utils.CustomCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        log.debug("Request to save Employee : {}", employee);
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> findAll(Pageable pageable) {
        log.debug("Request to get all Employees");
        return employeeRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findOne(Long id) {
        log.debug("Request to get Employee : {}", id);
        return employeeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Employee : {}", id);
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByPlaceContainingIgnoreCase(String place) {
        return employeeRepository.findByPlaceContainingIgnoreCase(place);
    }

    @Override
    public List<Employee> findByBusinessUnitContainingIgnoreCase(String businessUnit) {
        return employeeRepository.findByBusinessUnitContainingIgnoreCase(businessUnit);
    }

    @Override
    public List<Employee> findByTitleContainingIgnoreCase(String title) {
        return employeeRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public int saveAll(List<Employee> employeeList) {
        return employeeRepository.saveAll(employeeList).size();
    }

    @Override
    public List<Employee> findBySupervisorID(Employee supervisorID) {
        return employeeRepository.findBySupervisorID(supervisorID);
    }

    public long cacheAll(List<Employee> employees) {
        return CustomCache.refresh(employees);
    }
}
